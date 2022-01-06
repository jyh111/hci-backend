package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.test.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {

    int insert(Score score);

    Score selectByAllId(@Param("questionId") Integer questionId, @Param("studentId") Integer studentId, @Param("testId") Integer testId);
}
