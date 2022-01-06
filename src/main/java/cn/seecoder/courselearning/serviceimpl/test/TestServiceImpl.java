package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.mapperservice.test.TestMapper;
import cn.seecoder.courselearning.po.test.Test;
import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;
    @Override
    public List<TestVO> getAllTestsByTeacherId(Integer uid) {
        List<TestVO> ret =new ArrayList<>();
        List<Test> testList = testMapper.selectByTeacherId(uid);
        for(Test test:testList){
            ret.add(new TestVO(test));
        }
        return ret;
    }

    @Override
    public TestVO getTest(Integer testId) {
        return new TestVO(testMapper.selectByPrimaryKey(testId));
    }

    @Override
    public List<TestVO> getCourseTests(Integer courseId) {
        List<TestVO> ret =new ArrayList<>();
        List<Test> testList = testMapper.selectByCourseId(courseId);
        for(Test test:testList){
            ret.add(new TestVO(test));
        }
        return ret;
    }

    @Override
    public ResultVO<TestVO> createTest(TestVO testVO) {
        Test test = new Test(testVO);
        if(testMapper.insert(test)>0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "测试创建成功。",new TestVO(test));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public Test getByPrimaryKey(Integer testId) {
        return testMapper.selectByPrimaryKey(testId);
    }
    @Override
    public void addLengthByTestId(Integer testId){
        testMapper.addLengthByTest(testId);
    }
}
