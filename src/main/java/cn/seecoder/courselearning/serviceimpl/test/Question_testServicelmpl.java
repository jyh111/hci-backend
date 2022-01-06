package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.mapperservice.test.Question_testMapper;
import cn.seecoder.courselearning.mapperservice.test.TestMapper;
import cn.seecoder.courselearning.po.test.Question_test;
import cn.seecoder.courselearning.service.test.Question_testService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.Question_testVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Question_testServicelmpl implements Question_testService {
    @Resource
    private Question_testMapper question_testMapper;

    @Override
    public List<Question_testVO> getTestQuestions(Integer testId){
        List<Question_testVO> ret = new ArrayList<>();
        List<Question_test> question_testList = question_testMapper.selectByTestId(testId);
        for(Question_test question_test :question_testList){
            ret.add(new Question_testVO(question_test));
        }
        return ret;
    };
    @Override
    public ResultVO<Question_testVO> addQuestionToTest(Question_testVO question_testVO){
        Question_test question_test =new Question_test(question_testVO);
        if(question_testMapper.insert(question_test)>0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "题目添加成功。",new Question_testVO(question_test));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    };
    @Override
    public Question_test getByPrimaryKey(Integer question_testId){
        return question_testMapper.selectByPrimaryKey(question_testId);
    };
}
