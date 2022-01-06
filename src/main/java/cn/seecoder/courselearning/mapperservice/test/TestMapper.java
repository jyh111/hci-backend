package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.test.Test;

import java.util.List;

public interface TestMapper {

    int insert(Test test);

    int deleteByPrimaryKey(Integer testId);

    int updateByPrimaryKey(Integer id);

    List<Test> selectAll(Integer uid);

    List<Test> selectByCourseId(Integer courseId);

    List<Test> selectByTeacherId(Integer teacherId);

    Test selectByPrimaryKey(Integer testId);

    void addLengthByTest(Integer testId);
}
