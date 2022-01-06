package cn.seecoder.courselearning.service.file;

import cn.seecoder.courselearning.vo.file.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    ResultVO<FileInfoVO> uploadFile(MultipartFile file);
    ResultVO<Object> downloadFile(String originName, HttpServletResponse response, HttpServletRequest request);
}
