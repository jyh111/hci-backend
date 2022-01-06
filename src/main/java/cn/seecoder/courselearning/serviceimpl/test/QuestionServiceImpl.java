package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.mapperservice.test.QuestionMapper;
import cn.seecoder.courselearning.po.test.Qustion;
import cn.seecoder.courselearning.service.test.QuestionService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Override
    public List<QuestionVO> getAllQuestions(Integer teacherId) {
        List<QuestionVO> ret = new ArrayList<>();
        List<Qustion> questionList = questionMapper.selectAll(teacherId);
        for(Qustion question :questionList){
            ret.add(new QuestionVO(question));
        }
        return ret;
    }

    @Override
    public List<QuestionVO> getCourseQuestions(Integer courseId) {
        List<QuestionVO> ret = new ArrayList<>();
        List<Qustion> questionList = questionMapper.selectByCourseId(courseId);
        for(Qustion question :questionList){
            ret.add(new QuestionVO(question));
        }
        return ret;
    }

    @Override
    public QuestionVO getQuestion(Integer questionId) {
        return new QuestionVO(questionMapper.selectByPrimaryKey(questionId));
    }

    @Override
    public ResultVO<QuestionVO> createQuestion(QuestionVO questionVO) {
        Qustion qustion =new Qustion(questionVO);
        if(questionMapper.insert(qustion)>0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "题目创建成功。",new QuestionVO(qustion));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public Qustion getByPrimaryKey(Integer questionId) {
        return questionMapper.selectByPrimaryKey(questionId);
    }

    @Override
    public List<QuestionVO> getAllQuestionsByTestId(Integer testId) {
        List<QuestionVO> ret = new ArrayList<>();
        List<Qustion> questionList = questionMapper.selectAllQuestionByTestId(testId);
        for(Qustion question :questionList){
            ret.add(new QuestionVO(question));
        }
        return ret;
    }
}
