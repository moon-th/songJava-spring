package kr.co.song.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") // 해당 Bean 이 생성 되면서 해당 프로퍼티에 대한 값을 가져와 사용한다.
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

}
