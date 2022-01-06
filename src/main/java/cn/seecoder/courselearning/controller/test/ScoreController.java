package cn.seecoder.courselearning.controller.test;

import cn.seecoder.courselearning.service.test.QuestionService;
import cn.seecoder.courselearning.service.test.ScoreService;
import cn.seecoder.courselearning.serviceimpl.test.QuestionServiceImpl;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.test.ScoreVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    /**
     * 创建题目作答信息
     * @param scoreVO 作答VO
     */
    @PostMapping("/create")
    public ResultVO<ScoreVO> createScore(@RequestBody ScoreVO scoreVO) {
        return scoreService.createScore(scoreVO);
    }
    /**
     * 按学生id，测试id，题目id获取题目作答信息
     * @param sid 学生id
     */
    @GetMapping("/get/{sid}")
    public ScoreVO getScoreByAllId(@PathVariable Integer sid,@RequestParam(required = false, defaultValue = "") Integer testId,@RequestParam(required = false, defaultValue = "") Integer questionId) {
        return scoreService.getScore(questionId,sid,testId);
    }
}

