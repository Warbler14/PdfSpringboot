package com.lotus.jewel.booker.pdf.model;

import java.io.Serializable;

public class Word implements Serializable{

	private static final long serialVersionUID = -6360119559242183023L;
	
	private String fileId;
	
	private String word;
	
	private Integer referanceCount;
	
	private String registDatetime;
	
	private String modifyDatetime;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getReferanceCount() {
		return referanceCount;
	}

	public void setReferanceCount(Integer referanceCount) {
		this.referanceCount = referanceCount;
	}

	public String getRegistDatetime() {
		return registDatetime;
	}

	public void setRegistDatetime(String registDatetime) {
		this.registDatetime = registDatetime;
	}

	public String getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word [fileId=");
		builder.append(fileId);
		builder.append(", word=");
		builder.append(word);
		builder.append(", referanceCount=");
		builder.append(referanceCount);
		builder.append(", registDatetime=");
		builder.append(registDatetime);
		builder.append(", modifyDatetime=");
		builder.append(modifyDatetime);
		builder.append("]");
		return builder.toString();
	}

}
