package kr.co.song.spring.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import kr.co.song.spring.configuration.servlet.handler.BaseHandlerInterceptor;
import kr.co.song.spring.framwork.data.web.MySQLPageRequestHandleMethodArgumentResolver;
import kr.co.song.spring.mvc.domain.BaseCodeLabelEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
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
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView(){
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
        jsonView.setObjectMapper(objectMapper());
        return jsonView;
    }

    @Bean
    public GlobalConfig config() {
        return new GlobalConfig();
    }

    @Bean
    public BaseHandlerInterceptor baseHandlerInterceptor(){
        return new BaseHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(baseHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //페이지 리졸버 등록
        resolvers.add(new MySQLPageRequestHandleMethodArgumentResolver());

    }
}
