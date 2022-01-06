package cn.seecoder.courselearning.vo.test;
import cn.seecoder.courselearning.po.test.Score;
import cn.seecoder.courselearning.po.test.Test;
import lombok.NonNull;

public class ScoreVO {
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

    public ScoreVO(){

    }
    public ScoreVO(@NonNull Score sco){
        id=sco.getId();
        questionId=sco.getQuestionId();
        testId=sco.getTestId();
        studentId=sco.getStudentId();
        score=sco.getScore();
        info=sco.getInfo();
    }
}
