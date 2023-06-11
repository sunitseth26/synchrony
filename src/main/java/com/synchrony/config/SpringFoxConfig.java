package com.synchrony.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2) 
          .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.synchrony.controller"))              
          .paths(PathSelectors.any())
          .build();                                           
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Syncrony Api", "API Documentation", "2.0", "TOC", "Sunit Seth", "", "");
	}
}
