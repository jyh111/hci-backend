package cn.seecoder.courselearning.mapperservice.test;
import cn.seecoder.courselearning.po.test.Question_test;
import cn.seecoder.courselearning.po.test.Qustion;

import java.util.List;

public interface Question_testMapper {

    int insert(Question_test question_test);

    int deleteByPrimaryKey(Integer id);

    List<Question_test> selectByTestId(Integer testId);

    Question_test selectByPrimaryKey(Integer question_testId);
}
