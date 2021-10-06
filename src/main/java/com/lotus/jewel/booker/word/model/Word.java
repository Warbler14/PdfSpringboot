package com.lotus.jewel.booker.word.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.lotus.jewel.booker.model.PagingForSqlite;

public class Word extends PagingForSqlite implements Serializable{

	private static final long serialVersionUID = -6360119559242183023L;

	private String word;
	
	private Character header;
	
	private Integer lank;
	
	private Integer difficulty;
	
	private String registDatetime;
	
	private String modifyDatetime;

	public Word() {
		super();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
		setHeader();
	}

	public Character getHeader() {
		return header;
	}

	public void setHeader(Character header) {
		this.header = header;
	}
	
	public void setHeader() {
		if(StringUtils.hasLength(word)) {
			header = Character.toUpperCase(word.charAt(0));
		}
	}

	public Integer getLank() {
		return lank;
	}

	public void setLank(Integer lank) {
		this.lank = lank;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
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
		builder.append("Word [word=");
		builder.append(word);
		builder.append(", header=");
		builder.append(header);
		builder.append(", lank=");
		builder.append(lank);
		builder.append(", difficulty=");
		builder.append(difficulty);
		builder.append(", registDatetime=");
		builder.append(registDatetime);
		builder.append(", modifyDatetime=");
		builder.append(modifyDatetime);
		builder.append("]");
		return builder.toString();
	}
	
}
