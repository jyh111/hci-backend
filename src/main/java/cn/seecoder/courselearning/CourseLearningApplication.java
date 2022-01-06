package cn.seecoder.courselearning;
import cn.seecoder.courselearning.controller.course.CourseController;
import cn.seecoder.courselearning.controller.test.QuestionController;
import cn.seecoder.courselearning.vo.test.QuestionVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
@SpringBootApplication
public class CourseLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseLearningApplication.class, args);
        //QuestionController q=new QuestionController();
        //List<QuestionVO> list=q.getAllTest(1);
        //System.out.println(list);
    }
}
