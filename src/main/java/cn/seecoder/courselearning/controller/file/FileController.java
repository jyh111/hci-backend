package cn.seecoder.courselearning.controller.file;

import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public ResultVO<FileInfoVO> upload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/download")
    public void download(@RequestParam("originName") String originName, HttpServletResponse response, HttpServletRequest request) {
        fileService.downloadFile(originName, response, request);
    }
}