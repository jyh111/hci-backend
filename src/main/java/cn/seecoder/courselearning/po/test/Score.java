package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.vo.test.ScoreVO;
import lombok.NonNull;
public class Score {
    private Integer id;
    private Integer testId;
    private Integer questionId;
    private Integer studentId;
    private Integer score;
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info= info;
    }

    public Score(){

    }

    public Score(@NonNull ScoreVO scoreVO){
        id=scoreVO.getId();
        questionId=scoreVO.getQuestionId();
        testId=scoreVO.getTestId();
        studentId=scoreVO.getStudentId();
        score=scoreVO.getScore();
        info=scoreVO.getInfo();
    }
}
