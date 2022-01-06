package cn.seecoder.courselearning.service.test;

import cn.seecoder.courselearning.po.test.Score;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.test.ScoreVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;

public interface ScoreService {
    //创建一个score
    ResultVO<ScoreVO> createScore(ScoreVO scoreVO);
    //通过studentid,testid,questionid查询一个score
    ScoreVO getScore(Integer questionId,Integer studentId,Integer testId);
}
