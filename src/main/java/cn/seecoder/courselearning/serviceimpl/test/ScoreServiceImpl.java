package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.mapperservice.test.ScoreMapper;
import cn.seecoder.courselearning.po.test.Qustion;
import cn.seecoder.courselearning.po.test.Score;
import cn.seecoder.courselearning.service.test.ScoreService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.test.ScoreVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Override
    public ResultVO<ScoreVO> createScore(ScoreVO scoreVO){
        Score score =new Score(scoreVO);
        if(scoreMapper.insert(score)>0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "题目创建成功。",new ScoreVO(score));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public ScoreVO getScore(Integer questionId,Integer studentId,Integer testId){
        return new ScoreVO(scoreMapper.selectByAllId(questionId,studentId,testId));
    }
}
