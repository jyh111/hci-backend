package cn.seecoder.courselearning.serviceimpl.file;

import cn.seecoder.courselearning.exception.MyException;
import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.FileHelper;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${web.file-upload-path}")
    private String path;

//    private final static String  fileDir="files";
//    private  final static String rootPath = System.getProperty("user.home")+File.separator+fileDir+File.separator;

    @Override
    public ResultVO<FileInfoVO> uploadFile(MultipartFile multipartFile) {
        try {
            FileInfoVO fileInfoVO = FileHelper.save(path, multipartFile);
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "文件上传成功", fileInfoVO);
        } catch (MyException myException){
            logger.error("用于存放上传文件的文件夹不存在或创建失败，请检查路径是否有效", myException);
        } catch (IOException ioException){
            logger.error("文件复制时出错", ioException);
        }
        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
//        File fileDir = new File(rootPath);
//        if (!fileDir.exists() && !fileDir.isDirectory()) {
//            fileDir.mkdirs();
//        }
//        try {
//            if (multipartFile != null) {
//                    try {
//                        //以原来的名称命名,覆盖掉旧的
//                        String storagePath = rootPath+multipartFile.getOriginalFilename();
//                        logger.info("上传的文件：" + multipartFile.getOriginalFilename() + "," + multipartFile.getContentType() + "," + multipartFile.getOriginalFilename()
//                                +"，保存的路径为：" + storagePath);
//                        Streams.copy(multipartFile.getInputStream(), new FileOutputStream(storagePath), true);
//                        String type = "unknown";
//                        String size = String.format("%.2f", (multipartFile.getSize() * 1.0 / 1024 / 1024)) + " MB";
//                        return new ResultVO<>(Constant.REQUEST_SUCCESS, "文件上传成功", new FileInfoVO(multipartFile.getOriginalFilename(), type, size));
//                        //或者下面的
//                        // Path path = Paths.get(storagePath);
//                        //Files.write(path,multipartFiles[i].getBytes());
//                    } catch (IOException e) {
//                        logger.error(e.getMessage());
//                    }
//
//            }
//
//        } catch (Exception e) {
//            return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
//        }
//        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
    }

    @Override
    public ResultVO<Object> downloadFile(String fileName, HttpServletResponse response, HttpServletRequest request) {

        // 获取文件路径前缀
        String filePrefix = path;
        // 文件下载
        try{
            FileHelper.downloadUtil(response, path+fileName, fileName);
        }catch (IOException exception){
            logger.error(exception.getMessage());
        }
        return null;
//        OutputStream os = null;
//        InputStream is= null;
//        try {
//            // 取得输出流
//            os = response.getOutputStream();
//            // 清空输出流
////            fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
//            response.reset();
////            response.setHeader("content-type", "application/octet-stream");
//            response.setContentType("application/force-download");
////            response.setContentType("application/x-download;charset=UTF-8");
//            String realname = fileName.substring(fileName.indexOf("_")+1);
//            response.addHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("iso8859-1"),"UTF-8"));
//            //读取流
//            File f = new File(path+fileName);
//            logger.info("读取{}", path + fileName);
//            is = new FileInputStream(f);
//            if (is == null) {
//                logger.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
//                return null;
//            }
//            //创建输出流
//            OutputStream out = response.getOutputStream();
//                    //创建缓冲区
//                    byte buffer[] = new byte[1024];
//                    int len = 0;
//                   //循环将输入流中的内容读取到缓冲区当中
//                    while((len=is.read(buffer))>0){
//                           //输出缓冲区的内容到浏览器，实现文件下载
//                           out.write(buffer, 0, len);
//                       }
//                     //关闭文件输入流
//                     is.close();
//                     //关闭输出流
//                     out.close();
//            response.getOutputStream().flush();
//            return null;
//        } catch (IOException e) {
//            return new ResultVO<>(-1,"下载附件失败,error:"+e.getMessage());
//        }
//        //文件的关闭放在finally中
//        finally
//        {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                logger.error(e.getMessage());
//            }
//            try {
//                if (os != null) {
//                    os.close();
//                }
//            } catch (IOException e) {
//                logger.error(e.getMessage());
//            }
//        }
    }
}
