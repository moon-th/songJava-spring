package kr.co.song.spring.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
/**
 * @Mapper(애너테이션)을 이용하여 Mapper Interface를 스프링 빈으로 주입받아 DB에 접근하는 방법
 */
@MapperScan(basePackages = "kr.co.song.spring.mvc.repository") //해당 패키지를 스캔
public class MybatisConfiguration {



    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(); //팩토리 빈 생성
        factoryBean.setDataSource(dataSource); //미리 만들어 놓은 DataSource 빈을 주입하여 넣어준다.
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/sql/*.xml")); //쿼리 실행을 위해 만들어 놓은 해당 위치의 xml 파일을 맵퍼로 설정
        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory); //sqlSessionTemplate 에 만들어 놓은 팩토리 주입
    }
}
