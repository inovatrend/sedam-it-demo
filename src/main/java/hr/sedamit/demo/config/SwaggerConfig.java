package hr.sedamit.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfo(
                                "Sedam IT demo API",
                                "Public API for accessing Sedam IT demo app data",
                                "0.0.1",
                                null, null, null, null
                        )
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/(user|author)/(.*)"))
//                .paths(PathSelectors.ant("/author/**"))
                .build();
    }
}
