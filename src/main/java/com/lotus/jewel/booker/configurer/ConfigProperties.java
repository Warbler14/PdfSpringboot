package com.lotus.jewel.booker.configurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

	private FileConfig fileConfig = new FileConfig();

	public FileConfig getFileConfig() {
		return fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	public static class FileConfig {
		
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

}
