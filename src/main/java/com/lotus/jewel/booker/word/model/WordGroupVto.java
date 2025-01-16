package com.lotus.jewel.booker.word.model;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lotus.jewel.booker.model.PagingForSqlite;

public class WordGroupVto extends PagingForSqlite implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = -1411829976084291579L;
	
	private Integer groupId;
	
	private String description;
	
	private String registDatetime;
	
	private String modifyDatetime;

	public WordGroupVto() {
		super();
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		builder.append("WordGroup [groupId=");
		builder.append(groupId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", registDatetime=");
		builder.append(registDatetime);
		builder.append(", modifyDatetime=");
		builder.append(modifyDatetime);
		builder.append("]");
		return builder.toString();
	}

}
