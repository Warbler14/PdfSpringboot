package com.lotus.jewel.booker.word.model;

import java.io.Serializable;


import com.lotus.jewel.booker.model.PagingForSqlite;

public class WorkbookVto extends PagingForSqlite implements Serializable{

	private static final long serialVersionUID = 6633050871064556064L;

	private String workDay;
	
	private String userId = "user";
	
	private String memo;
	
	private String word;
	
	private String updateWord;
	
	private Integer status = 1;
	
	private String registDatetime;
	
	private String modifyDatetime;

	public String getWorkDay() {
		return workDay;
	}

	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public String getUpdateWord() {
		return updateWord;
	}

	public void setUpdateWord(String updateWord) {
		this.updateWord = updateWord;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workbook [workDay=");
		builder.append(workDay);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", word=");
		builder.append(word);
		builder.append(", updateWord=");
		builder.append(updateWord);
		builder.append(", status=");
		builder.append(status);
		builder.append(", registDatetime=");
		builder.append(registDatetime);
		builder.append(", modifyDatetime=");
		builder.append(modifyDatetime);
		builder.append("]");
		return builder.toString();
	}

}
