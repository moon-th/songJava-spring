package kr.co.song.spring.configuration;

import kr.co.song.spring.configuration.servlet.handler.BaseHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource(); // 스프링 에서 제공하는 메시지소스
        source.setBasename("classpath:/messages/message"); //해당 메시지를 어느 파일에서 가져 오는지 정의
        source.setDefaultEncoding("UTF-8"); //기본 인코딩 타입
        source.setDefaultLocale(Locale.KOREAN); //기본 지역
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public BaseHandlerInterceptor baseHandlerInterceptor(){
        return new BaseHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(baseHandlerInterceptor());
    }

}
