package kr.co.song.spring.mvc.repository;

import kr.co.song.spring.mvc.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {

    void save(UploadFileParameter parameter);

}
