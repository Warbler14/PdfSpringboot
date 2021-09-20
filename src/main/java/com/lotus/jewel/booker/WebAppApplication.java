package com.lotus.jewel.booker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.lotus.jewel.booker.configurer.FileProperties;


@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class })
@SpringBootApplication
//@EnableConfigurationProperties(FileProperties.class)
public class WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

}