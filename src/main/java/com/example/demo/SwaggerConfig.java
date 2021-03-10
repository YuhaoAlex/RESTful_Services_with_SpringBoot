package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.HashMap;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    public static final Contact DEFAULT_CONTACT = new Contact("Yuhao", "github.com/YuhaoAlex", "julyzyh@gmail.com");

//    public static final HashMap<String, String> contact = new HashMap<String, String>(){
//        {
//            put("name", "Yuhao Alex");
//            put("Email", "julyzyh@gmail.com");
//        }
//    };

//    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Swagger API INFO from Yuhao", "Swagger API Tittle",
//                                                            "1.0","Terms Of Service Url", DEFAULT_CONTACT, "Apace 2.0",
//                                                            "http://www.apache.org/licenses/LICENSE-2.0");


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo());
//                        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                        .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Yuhao", "github.com/YuhaoAlex", "julyzyh@gmail.com");
        return new ApiInfoBuilder()
                .title("Swagger API doc By Yuhao")
                .description("Description of Demo")
                .contact(contact)
                .version("7.7")
                .build();
    }
}
