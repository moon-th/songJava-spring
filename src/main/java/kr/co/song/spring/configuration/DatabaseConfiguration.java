package kr.co.song.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import javax.sql.DataSource;

/**
 * 이 어노테이션을 구현함으로써 클래스가 하나 이상의 @Bean 메소드를 제공하고
 * 스프링 컨테이가 Bean 정의를 생성하고 런타임시 그 Bean 들이 요청들을 처리할 것을 선언하게 된다.
 */
@Configuration
public class DatabaseConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") // 해당 Bean 이 생성 되면서 해당 프로퍼티에 대한 값을 가져와 사용한다.
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

}
