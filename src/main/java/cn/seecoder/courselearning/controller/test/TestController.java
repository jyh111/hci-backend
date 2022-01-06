package cn.seecoder.courselearning.controller.test;

import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import cn.seecoder.courselearning.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testservice;

    /**
     * 教师创建题目
     * @param test 题目VO
     */
    @PostMapping("/create")
    public ResultVO<TestVO> createTest(@RequestBody TestVO test) {
        return testservice.createTest(test);
    }

    /**
     * 按教师id获取全部test
     * @param uid 教师id
     */
    @GetMapping("/get_teacher/{uid}")
    public List<TestVO> getAllTestByTeacherId(@PathVariable Integer uid){
        return testservice.getAllTestsByTeacherId(uid);
    }
    /**
     * 按课程id获取全部test
     * @param cid 课程id
     */
    @GetMapping("/get_course/{cid}")
    public List<TestVO> getAllTestByCourseId(@PathVariable Integer cid){
        return testservice.getCourseTests(cid);
    }
    /**
     * 按测试id获取test
     * @param tid 测试id
     */
    @GetMapping("/get/{tid}")
    public TestVO getTestById(@PathVariable Integer tid){
        return testservice.getTest(tid);
    }
}
