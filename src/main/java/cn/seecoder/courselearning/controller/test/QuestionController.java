package cn.seecoder.courselearning.controller.test;

import cn.seecoder.courselearning.service.test.QuestionService;
import cn.seecoder.courselearning.serviceimpl.test.QuestionServiceImpl;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionservice;

    /**
     * 教师创建题目
     * @param question 题目VO
     */
    @PostMapping("/create")
    public ResultVO<QuestionVO> createQuestion(@RequestBody QuestionVO question) {
        return questionservice.createQuestion(question);
    }
    /**
     * 获取课程全部question
     * @param cid 课程id
     */
    @GetMapping("/get_course/{cid}")
    public List<QuestionVO> getAllQuestionByCourse(@PathVariable Integer cid){
        return questionservice.getCourseQuestions(cid);
    }

    /**
     * 按测试id获取全部question
     * @param tid 测试id
     */
    @GetMapping("/get_test/{tid}")
    public List<QuestionVO> getAllQuestionByTestId(@PathVariable Integer tid){
        return questionservice.getAllQuestionsByTestId(tid);
    }
    /**
     * 按问题id获取question
     * @param qid 问题id
     */
    @GetMapping("/get/{qid}")
    public QuestionVO getQuestionById(@PathVariable Integer qid){
        return questionservice.getQuestion(qid);
    }

}

