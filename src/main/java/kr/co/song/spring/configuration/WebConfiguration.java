package kr.co.song.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class WebConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource(); // 스프링 에서 제공하는 메시지소스
        source.setBasename("classpath:/messages/message"); //해당 메시지를 어느 파일에서 가져 오는지 정의
        source.setDefaultEncoding("UTF-8"); //기본 인코딩 타입
        source.setDefaultLocale(Locale.KOREAN); //기본 지역
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
