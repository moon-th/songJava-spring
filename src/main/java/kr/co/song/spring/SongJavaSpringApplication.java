package kr.co.song.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SongJavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongJavaSpringApplication.class, args);
    }

}
