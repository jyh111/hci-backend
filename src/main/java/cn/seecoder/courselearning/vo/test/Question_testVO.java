package cn.seecoder.courselearning.vo.test;
import cn.seecoder.courselearning.po.test.Question_test;
import lombok.NonNull;

public class Question_testVO {
    private Integer id;
    private Integer testId;
    private Integer questionId;

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

    public Question_testVO(){

    }

    public Question_testVO(@NonNull Question_test question_test){
        id=question_test.getId();
        testId=question_test.getTestId();
        questionId=question_test.getQuestionId();
    }
}
