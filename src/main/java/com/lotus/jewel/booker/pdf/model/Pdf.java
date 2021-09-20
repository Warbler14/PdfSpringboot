package com.lotus.jewel.booker.pdf.model;

import java.io.Serializable;

public class Pdf implements Serializable{

	private static final long serialVersionUID = 5895298764634239595L;
	
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
