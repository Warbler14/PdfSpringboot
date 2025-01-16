package com.lotus.jewel.booker.pdf.model;

import java.io.Serializable;

import com.lotus.jewel.booker.pdf.converter.ConvertStore;

public class Pdf<K, V> implements Serializable{

	private static final long serialVersionUID = 5895298764634239595L;
	
	private String filePath;
	
	private int startPage = 1;
	
	private int endPage = 1;
	
	private ConvertStore<K, V> convertedStore;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public ConvertStore<K, V> getConvertedStore() {
		return convertedStore;
	}

	public void setConvertedStore(ConvertStore<K, V> convertedStore) {
		this.convertedStore = convertedStore;
	}
	
	
	
	
}
