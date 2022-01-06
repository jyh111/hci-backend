package cn.seecoder.courselearning.mapperservice.test;


import cn.seecoder.courselearning.po.test.Qustion;
import cn.seecoder.courselearning.po.test.Test;

import java.util.List;

public interface QuestionMapper {
    int insert(Qustion qustion);

    int deleteByPrimaryKey(Integer questionId);

    int updateByPrimaryKey(Qustion record);

    List<Qustion> selectAll(Integer uid);

    List<Qustion> selectByCourseId(Integer courseId);

    List<Qustion> selectByTeacherId(Integer teacherId);

    List<Qustion> selectByType(String type);

    Qustion selectByPrimaryKey(Integer questionId);

    List<Qustion> selectAllQuestionByTestId(Integer tid);
}
