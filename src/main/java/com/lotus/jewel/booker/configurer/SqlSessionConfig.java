package com.lotus.jewel.booker.configurer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.annotation.PostConstruct;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;

import com.lotus.jewel.booker.pdf.controller.PdfController;
import com.lotus.jewel.booker.util.Util;

@Configuration
@Lazy
@MapperScan(basePackages = {"com.lotus.jewel.booker"})
public class SqlSessionConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(SqlSessionConfig.class);
	
	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@PostConstruct
	private void database() throws IOException {
		String dbBackupPath = configProperties.getFileConfig().getDbBackupPath();
		String dbFilePath = configProperties.getFileConfig().getDbPath();
		
		File dbFile = new File(dbFilePath);
		
		if(dbFile.exists()) {
			return;
		}
		
		logger.info("Generate db file (" + dbFilePath + ")");
		
		try (InputStream io = resourceLoader.getResource(dbBackupPath).getInputStream()){
			Util.copyInputStreamToFile(io, dbFile);
			
		} catch (FileNotFoundException e) {
	        throw e;
	    } catch (IOException e) {
	    	throw e;
	    }
	
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		
		Reader reader = null;
		try {
			String configuration = "mybatis/configuration.xml";
			reader = Resources.getResourceAsReader(configuration);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);
		return factory;
	}
	
	@Bean
	public SqlSession sqliteSession() {
		return sqlSessionFactory().openSession(true);
	}
	
}
