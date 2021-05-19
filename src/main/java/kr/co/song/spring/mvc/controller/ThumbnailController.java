package kr.co.song.spring.mvc.controller;

import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponseCode;
import kr.co.song.spring.mvc.domain.ThumbnailType;
import kr.co.song.spring.mvc.domain.UploadFile;
import kr.co.song.spring.mvc.service.UploadFileService;
import lombok.extern.flogger.Flogger;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("thumbnail")
public class ThumbnailController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UploadFileService fileService;


    @GetMapping("/make/{uploadFileSeq}/{thumbnailType}")
    public void make(@PathVariable  int uploadFileSeq,
                     @PathVariable ThumbnailType thumbnailType,
                     HttpServletResponse response){
        UploadFile uploadFile = fileService.get(uploadFileSeq);
        if(uploadFile == null){
             throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
        }
        String pathname = uploadFile.getPathname();
        File file = new File(pathname);
        if(!file.isFile()){
            throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
        }
        try {
            String thumbnailPathname = uploadFile.getPathname().replace(".", "_"+thumbnailType.width() +"-"+thumbnailType.height()+".");
            File thumbnailFile = new File(thumbnailPathname);
            if(!thumbnailFile.isFile()){
                Thumbnails.of("original.jpg")
                        .size(thumbnailType.width(), thumbnailType.height())
                        .toFile(thumbnailPathname);
                logger.info(" thumbnailPathname : {} ", thumbnailPathname);
            }
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            FileCopyUtils.copy(new FileInputStream(thumbnailFile), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
