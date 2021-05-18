package kr.co.song.spring.mvc.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.song.spring.configuration.GlobalConfig;
import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponse;
import kr.co.song.spring.configuration.http.BaseResponseCode;
import kr.co.song.spring.mvc.parameter.UploadFileParameter;
import kr.co.song.spring.mvc.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@RestController
@Api(tags = "파일 API")
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalConfig config;

    @Autowired
    private UploadFileService fileService;

    /**
     * 업로드 리턴
     */
    @PostMapping("/save")
    @ApiOperation(value="업로드",notes = "")
    public BaseResponse<Boolean>save(@RequestParam("uploadFile") MultipartFile multipartFile){
        if(multipartFile == null || multipartFile.isEmpty()){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }
        //날짜폴더 추가
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        logger.debug("config : {}", config);
        String uploadFilePath = config.getUploadFilePath()+ currentDate + "/";
        logger.debug("uploadFilePath : {}",uploadFilePath);

        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") +1, multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID().toString()+"."+ prefix;
        String pathname = uploadFilePath + filename;
        String resourcePathname = config.getUploadResourcePath() + currentDate + "/" + filename;

        File folder = new File(uploadFilePath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        File dest = new File(pathname);
        try {
            multipartFile.transferTo(dest);
            //파일업로드 된 후 DB에 저장
            UploadFileParameter parameter = new UploadFileParameter();
            //컨텐츠 종류
            parameter.setContentType(multipartFile.getContentType());
            //원본 파일명
            parameter.setOriginalFilename(multipartFile.getOriginalFilename());
            //저장 파일명
            parameter.setFilename(filename);
            //실제파일 저장경로
            parameter.setPathname(pathname);
            //파일크기
            parameter.setSize((int)multipartFile.getSize());
            // static resource 접근경로
            parameter.setResourcePathname(resourcePathname);

            fileService.save(parameter);
        } catch ( IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return new BaseResponse<Boolean>(true);
    }
}
