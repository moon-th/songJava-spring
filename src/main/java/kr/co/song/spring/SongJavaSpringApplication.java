package kr.co.song.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 스케줄러 사용을 위해 선언
public class SongJavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongJavaSpringApplication.class, args);
    }

}
