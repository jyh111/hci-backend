package cn.seecoder.courselearning.controller.test;

import cn.seecoder.courselearning.service.test.Question_testService;
import cn.seecoder.courselearning.vo.test.Question_testVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/question_test")
public class Question_testController {
    @Resource
    private Question_testService question_testservice;

    /**
     * 教师加入题目到测试中
     * @param question_test 题目_测试VO
     */
    @PostMapping("/add")
    public ResultVO<Question_testVO> createCourse(@RequestBody Question_testVO question_test) {
        return question_testservice.addQuestionToTest(question_test);
    }
}
