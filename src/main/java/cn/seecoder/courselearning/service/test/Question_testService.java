package cn.seecoder.courselearning.service.test;
import cn.seecoder.courselearning.po.test.Question_test;
import cn.seecoder.courselearning.vo.test.Question_testVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;

public interface Question_testService {
    //获取测试所有题目
    List<Question_testVO> getTestQuestions(Integer testId);
    //加入题目到测试中
    ResultVO<Question_testVO> addQuestionToTest(Question_testVO question_testVO);

    Question_test getByPrimaryKey(Integer question_testId);
}
