package com.taborda.productms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.taborda.productms"))
                .paths(PathSelectors.ant("/**"))
				.build().apiInfo( metaData() );

	}
	
	private ApiInfo metaData() { 
		
		return new ApiInfoBuilder()
				.title("MS-Product - Desafio Fast-Track API Documentation")
				.description(null)
				.version(null)
				.license(null)				
				.licenseUrl(null)				
				.contact(new Contact("", "", ""))
				.build();
  	 
    }
}
