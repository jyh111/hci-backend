package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.vo.test.TestVO;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public class Test {

    private Integer id;
    private Integer courseId;
    private Integer teacherId;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Test(){

    }

    public Test(@NonNull TestVO testVO){
        id=testVO.getId();
        teacherId=testVO.getTeacherId();
        courseId=testVO.getCourseId();
        startTime=testVO.getStartTime();
        endTime=testVO.getEndTime();
        testName=testVO.getTestName();
        courseName=testVO.getCourseName();
        length=testVO.getLength();
    }
}
