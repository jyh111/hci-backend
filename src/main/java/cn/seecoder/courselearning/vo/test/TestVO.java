package cn.seecoder.courselearning.vo.test;
import cn.seecoder.courselearning.po.test.Test;
import cn.seecoder.courselearning.po.test.Qustion;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public class TestVO {
    private Integer id;
    private Integer teacherId;
    private Integer courseId;
    private Date startTime;
    private Date endTime;
    private String testName;
    private String courseName;
    private Integer length;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public TestVO(){

    }

    public TestVO(@NonNull Test test){
        id=test.getId();
        teacherId=test.getTeacherId();
        courseId=test.getCourseId();
        startTime=test.getStartTime();
        endTime=test.getEndTime();
        testName=test.getTestName();
        courseName=test.getCourseName();
        length=test.getLength();
    }
}
