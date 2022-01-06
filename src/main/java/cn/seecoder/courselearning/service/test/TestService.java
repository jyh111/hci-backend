package cn.seecoder.courselearning.service.test;

import cn.seecoder.courselearning.po.test.Test;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;

public interface TestService {
    //获取所有测试
    List<TestVO> getAllTestsByTeacherId(Integer uid);
    //获取某个测试
    TestVO getTest(Integer testId);
    //获取课程所有测试
    List<TestVO> getCourseTests(Integer courseId);
    //创建测试

    ResultVO<TestVO> createTest(TestVO testVO);

    Test getByPrimaryKey(Integer testId);
    //增加题目数
    void addLengthByTestId(Integer testId);
 }
