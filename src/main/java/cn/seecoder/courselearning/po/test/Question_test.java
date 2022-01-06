package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.vo.test.Question_testVO;
import lombok.NonNull;

public class Question_test {
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

    public Question_test(){

    }

    public Question_test(@NonNull Question_testVO question_test){
        id=question_test.getId();
        testId=question_test.getTestId();
        questionId=question_test.getQuestionId();
    }
}
