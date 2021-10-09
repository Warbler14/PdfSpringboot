package com.lotus.jewel.booker.word.model;


public class WordDetail extends Word {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4015965176813793612L;


	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWord(Word word) {
		if(word == null) {
			return;
		}
		setWord(word.getWord());
		setWordType(word.getWordType());
		setLank(word.getLank());
		setDifficulty(word.getDifficulty());
		setRegistDatetime(word.getRegistDatetime());
		setModifyDatetime(word.getModifyDatetime());
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordDetail [description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	
}
