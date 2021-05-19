package kr.co.song.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger 설정
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        ApiInfoBuilder apiInfo = new ApiInfoBuilder();
        apiInfo.title("API 서버 문서"); //Swagger 문서의 타이틀
        apiInfo.description("API 서버 문서 입니다."); // Swagger 문서의 간략 설명

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo.build());                                                    // 문서로 만들 Controller 패키지 경로 설정
        ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("kr.co.song.spring.mvc.controller"));
        apis.paths(PathSelectors.ant("/**")); // Controller 에서 보여줄 맵핑값 설정
        return apis.build();
    }
}
