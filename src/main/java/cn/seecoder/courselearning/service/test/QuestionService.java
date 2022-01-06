package cn.seecoder.courselearning.service.test;

import cn.seecoder.courselearning.po.test.Qustion;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;

public interface QuestionService {
    //获取用户所有题目
    List<QuestionVO> getAllQuestions(Integer teacherId);
    //获取课程所有题目
    List<QuestionVO> getCourseQuestions(Integer courseId);
    //获取某个题目
    QuestionVO getQuestion(Integer questionId);
    //创建题目
    ResultVO<QuestionVO> createQuestion(QuestionVO questionVO);

    Qustion getByPrimaryKey(Integer questionId);

    List<QuestionVO> getAllQuestionsByTestId(Integer testId);
}
