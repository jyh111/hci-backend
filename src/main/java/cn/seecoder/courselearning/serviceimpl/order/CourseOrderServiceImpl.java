package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.mapperservice.coupon.CourseOrderCouponMapper;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.coupon.CourseOrderCoupon;
import cn.seecoder.courselearning.po.coupon.UserCoupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.coupon.CouponService;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.service.order.CourseOrderService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.IntHolder;
import cn.seecoder.courselearning.vo.*;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseOrderServiceImpl implements CourseOrderService {
    @Resource
    CourseOrderMapper orderMapper;
    @Resource
    CourseOrderCouponMapper courseOrderCouponMapper;

    UserService userService;

    CouponService couponService;

    CourseService courseService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    @Override
    public ResultVO<CourseOrderVO> updateCourseOrder(Integer orderId, Integer orderStatus) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null) return new ResultVO<>(Constant.REQUEST_FAIL, "????????????????????????");
        if(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS))
            return new ResultVO<>(Constant.REQUEST_FAIL, "??????????????????????????????????????????????????????????????????", new CourseOrderVO(order));
        if((orderStatus.equals(Constant.ORDER_STATUS_SUCCESS)||orderStatus.equals(Constant.ORDER_STATUS_WAIT)) &&
                order.getStatus().equals(Constant.ORDER_STATUS_UNPAID)){
            // ????????????????????????????????? ??????????????????????????????????????????????????????
            UserVO user = userService.getUser(order.getUserId());
            if(user.getBalance()>=order.getCost()){
                userService.decreaseBalance(user.getId(), order.getCost());
                updateUserCoupon(orderId, user.getId());
            }else
                return new ResultVO<>(Constant.REQUEST_FAIL, "??????????????????");
        }
        order.setStatus(orderStatus);
        orderMapper.updateByPrimaryKey(order);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "??????????????????", new CourseOrderVO(order));
    }

    /**
     * ????????????????????????????????????
     * @param orderId
     * @param userId
     */
    public void updateUserCoupon(Integer orderId, Integer userId) {
        // ???????????????????????????
        List<Coupon> orderCoupons = couponService.getByOrderId(orderId);
        List<UserCoupon> userCouponList = couponService.getUserCouponsByUserId(userId);
        for (UserCoupon userCoupon : userCouponList) {
            if (orderCoupons.stream().anyMatch(coupon -> coupon.getId().equals(userCoupon.getCouponId()))) {
                if (userCoupon.getNums() == 1) {
                    couponService.deleteUserCoupon(userId, userCoupon.getCouponId());
                } else if (userCoupon.getNums() > 1) {
                    userCoupon.setNums(userCoupon.getNums() - 1);
                    couponService.updateUserCoupon(userCoupon);
                }
            }
        }
    }

    @Override
    public List<CourseOrderVO> getCourseOrders(Integer uid) {
        List<CourseOrderVO> ret = new ArrayList<>();
        List<CourseOrder> orderList = orderMapper.selectByUserId(uid);
        LocalDateTime now = LocalDateTime.now();
        for(CourseOrder order: orderList){
            // ??????????????????????????????
            List<Coupon> courseOrderCoupons = couponService.getByOrderId(order.getId());
            List<Coupon> coupons = new ArrayList<>();
            boolean updated = false;
            // ?????????????????????????????????????????????????????????????????????
            for (Coupon courseOrderCoupon : courseOrderCoupons) {
                if (!(courseOrderCoupon.isValid() && courseOrderCoupon.getStartTime().isBefore(now) && courseOrderCoupon.getEndTime().isAfter(now))) {
                    updated = true;
                    courseOrderCouponMapper.deleteByOrderIdAndCouponId(order.getId(), courseOrderCoupon.getId());
                } else {
                    coupons.add(courseOrderCoupon);
                }
            }
            if (updated) {
                updateBestCost(new CourseOrderVO(order));
                order = orderMapper.selectByPrimaryKey(order.getId());
            }
            CourseOrderVO orderVO = new CourseOrderVO(order);
            orderVO.setUsedCoupons(coupons.stream().map(CouponVO::new).collect(Collectors.toList()));
            ret.add(orderVO);
        }
        return ret;
    }

    /**
     * ??????????????????????????????
     * @param orderVO
     * @param couponId
     * @return
     */
    @Override
    public ResultVO<Boolean> useCoupon(CourseOrderVO orderVO, Integer couponId) {
        Coupon coupon = couponService.getByPrimaryKey(couponId);
        if (coupon == null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "??????????????????");
        }
        LocalDateTime now = LocalDateTime.now();
        if (!(coupon.isValid() && coupon.getStartTime().isBefore(now) && coupon.getEndTime().isAfter(now))) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "?????????????????????????????????");
        }
        // ?????????????????????????????????
        UserCoupon userCoupon = couponService.getUserCouponByCouponIdAndUserId(couponId, orderVO.getUserId());
        if (userCoupon == null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "????????????????????????");
        }
        // ??????????????????
        Course course = courseService.getByPrimaryKey(orderVO.getCourseId());
        if (!coupon.getScope().canUse(course, coupon)) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "?????????????????????????????????");
        }
        // ????????????????????????????????????
        if (!coupon.getType().getCouponStrategy().canUse(orderVO, coupon)) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "???????????????????????????");
        }
        // ?????????????????????????????????
        CourseOrderCoupon courseOrderCoupon = courseOrderCouponMapper.selectByOrderIdAndCouponId(orderVO.getId(), couponId);
        if (courseOrderCoupon != null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "???????????????????????????");
        }
        courseOrderCouponMapper.insert(new CourseOrderCoupon(orderVO.getId(), couponId));
        updateBestCost(orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "????????????");
    }

    @Override
    public ResultVO<Boolean> cancelCoupon(CourseOrderVO orderVO, Integer couponId) {
        courseOrderCouponMapper.deleteByOrderIdAndCouponId(orderVO.getId(), couponId);
        updateBestCost(orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "?????????????????????");
    }

    @Override
    public ResultVO<CourseOrderVO> getCourseOrderById(Integer orderId) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        CourseOrderVO orderVO = new CourseOrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"????????????????????????",orderVO);
    }

    @Override
    public ResultVO<Boolean> payOrder(Integer orderId) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        // ???????????????????????????????????????????????????????????????
        UserVO userVO = userService.getUser(order.getUserId());

        if(userVO.getBalance()>=order.getCost()){
            userService.decreaseBalance(userVO.getId(),order.getCost());
            // ???????????????????????????????????????
            ResultVO<CourseOrderVO> resultVO = updateCourseOrder(orderId,Constant.ORDER_STATUS_SUCCESS);
            if (resultVO.getCode().equals(Constant.REQUEST_SUCCESS)){
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"????????????");
            }else{
                return new ResultVO<>(Constant.REQUEST_FAIL,resultVO.getMsg());
            }
        }else{
            return new ResultVO<>(Constant.REQUEST_FAIL,"????????????,????????????");
        }

    }

    /**
     * ?????????????????????????????????
     * @param courseId
     * @param userId
     * @return
     * @author cyx
     */
    @Override
    public ResultVO<CourseOrderVO> createCourseOrder(Integer courseId, Integer userId) {
        List<CourseOrder> courseOrderList = orderMapper.selectByUserId(userId);

        for(CourseOrder order: courseOrderList){
            if(order.getCourseId().equals(courseId)){
                if(order.getStatus() == Constant.ORDER_STATUS_SUCCESS){
                    return new ResultVO<>(Constant.REQUEST_FAIL,"??????????????????");

                }
                List<Coupon> usedCoupons = couponService.getByOrderId(order.getId());
                List<CouponVO> couponVOS = new ArrayList<>();
                for(Coupon coupon:usedCoupons){
                    couponVOS.add(new CouponVO(coupon));
                }
                CourseOrderVO courseOrderVO = new CourseOrderVO(order);
                courseOrderVO.setUsedCoupons(couponVOS);
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"????????????????????????", courseOrderVO);
            }
        }
        CourseOrder order = new CourseOrder();

        order.setUserId(userId);
        order.setCourseId(courseId);
        CourseVO courseVO = courseService.getCourse(courseId,userId);
        order.setCost(courseVO.getCost());
        order.setStatus(Constant.ORDER_STATUS_UNPAID);
        order.setCreateTime(new Date());
        order.setCourseName(courseVO.getName());
        order.setOrigin(courseVO.getCost());

        if(orderMapper.insert(order) != 1){
            return new ResultVO<>(Constant.REQUEST_FAIL,"????????????",null);
        }

        CourseOrderVO courseOrderVO = new CourseOrderVO(order);

        return new ResultVO<>(Constant.REQUEST_SUCCESS,"????????????",courseOrderVO);
    }


    public void updateBestCost(CourseOrderVO orderVO) {
        List<Coupon> usedCoupons = couponService.getByOrderId(orderVO.getId());
        boolean[] used = new boolean[usedCoupons.size()];
        Arrays.fill(used, false);
        // ????????????dfs??????????????????????????????IntHolder????????????int????????????????????????
        // ??????Integer????????????????????????Immutable???????????????????????????????????????????????????????????????
        IntHolder minCost = new IntHolder(orderVO.getOrigin());
        orderVO.setCost(null);
        getBestCost(orderVO, usedCoupons, used, orderVO.getOrigin(), minCost);
        orderVO.setCost(minCost.value);
        // ??????
        CourseOrder courseOrder = orderMapper.selectByPrimaryKey(orderVO.getId());
        courseOrder.setCost(orderVO.getCost());
        orderMapper.updateByPrimaryKey(courseOrder);
    }

    /**
     * ???????????????cost
     * @param courseOrderVO
     * @param usedCoupons
     */
    public void getBestCost(CourseOrderVO courseOrderVO, List<Coupon> usedCoupons, boolean[] used, int currentCost, IntHolder minCost) {
        boolean allUsed = true;
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                allUsed = false;
                break;
            }
        }
        if (allUsed) {
            if ( minCost.value > currentCost) {
                minCost.value = currentCost;
            }
        } else {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    Integer oldCost = courseOrderVO.getCost();
                    int newCost = usedCoupons.get(i).getType().getCouponStrategy().useCoupon(courseOrderVO, usedCoupons.get(i));
                    courseOrderVO.setCost(newCost);
                    getBestCost(courseOrderVO, usedCoupons, used, newCost, minCost);
                    courseOrderVO.setCost(oldCost);
                    used[i] = false;
                }
            }
        }
    }
}
