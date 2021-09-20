package com.lotus.jewel.booker.configurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
//@ConfigurationProperties(prefix = "file")
public class FileProperties {

	private String dbBackupPath;
	
	private String dbPath;

	public String getDbBackupPath() {
		return dbBackupPath;
	}

	public void setDbBackupPath(String dbBackupPath) {
		this.dbBackupPath = dbBackupPath;
	}

	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}
	
	
}
