package com.lotus.jewel.booker.pdf.converter.data;

public interface DataStore <K, V>{
	
	public void add(K key, V value);
	
	public void get(K key);
	
	public V set(K key, V value);
	
	public void remove(K key);

}
