package kr.co.song.spring.mvc.service;

import kr.co.song.spring.mvc.domain.UploadFile;
import kr.co.song.spring.mvc.parameter.UploadFileParameter;
import kr.co.song.spring.mvc.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService {

    @Autowired
    private UploadFileRepository fileRepository;

    public void save(UploadFileParameter parameter) {
        fileRepository.save(parameter);
    }

    public UploadFile get(int uploadFileSeq) {
       return fileRepository.get(uploadFileSeq);
    }
}
