package cn.seecoder.courselearning.vo.test;
import cn.seecoder.courselearning.po.test.Qustion;

import lombok.NonNull;

public class QuestionVO {
    private Integer id;
    private Integer teacherId;
    private Integer courseId;
    private String content;
    private String answer;
    private String analysis;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public QuestionVO(){

    }

    public QuestionVO(@NonNull Qustion qustion){
        id=qustion.getId();
        teacherId=qustion.getTeacherId();
        courseId=qustion.getCourseId();
        content=qustion.getContent();
        answer=qustion.getAnswer();
        analysis=qustion.getAnalysis();
        type= qustion.getType();
    }
}
