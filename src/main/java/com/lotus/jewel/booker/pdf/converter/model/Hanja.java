package com.lotus.jewel.booker.pdf.converter.model;

public class Hanja {
	
	private int number;
	
	private Character character;
	
	private String mean;

	private String pronunciation;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hanja [number=");
		builder.append(number);
		builder.append(", character=");
		builder.append(character);
		builder.append(", mean=");
		builder.append(mean);
		builder.append(", pronunciation=");
		builder.append(pronunciation);
		builder.append("]");
		return builder.toString();
	}
	
}
