package com.lotus.jewel.booker.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 * 
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//classpath:/static
		//classpath:/public
		//classpath:/resources/
		//classpath:/META_INF/resources
		
		registry.addResourceHandler("/static/**")
			.addResourceLocations("classpath:/static/")	// 반드시 '/' 로 끝나야 
			.setCachePeriod(20);	//필수 아님, 캐시 지속시간 (초)
		
		
//		Resource path = new ClassPathResource("config/setting.yml");
		
		
	}
}