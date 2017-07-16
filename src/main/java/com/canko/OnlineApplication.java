package com.canko;

import com.canko.common.xss.XssStringJsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class OnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineApplication.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
		//解析器
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		//注册xss解析器
		SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
		xssModule.addSerializer(new XssStringJsonSerializer());
		objectMapper.registerModule(xssModule);
		//返回
		return objectMapper;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

			container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
			container.addErrorPages(new ErrorPage(Throwable.class,"/error/500"));
			container.addErrorPages(error403Page, error404Page, error500Page);
		});
	}

}
