package com.lotus.jewel.booker.pdf.converter.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.lotus.jewel.booker.pdf.converter.model.Hanja;

public class HanjaDataStore implements DataStore<Character, Hanja> {
	
	private ConcurrentHashMap<Character, Hanja> hanjaMap = new ConcurrentHashMap<Character, Hanja>();
	
	private Map<String, ArrayList<Character>> indexMap = new HashMap<String, ArrayList<Character>>();
	
	private List<Chapter> chapterList;
	
	private AtomicInteger index;
	
	public HanjaDataStore() {
		super();
	}
	
	public HanjaDataStore(List<Chapter> chapterList) {
		super();
		if(chapterList == null) {
			return;
		}
		this.chapterList = chapterList;
		index = new AtomicInteger();
	}
	
	@Override
	public void add(Character key, Hanja value) {
		hanjaMap.putIfAbsent(key, value);
		
		if(chapterList == null) {
			return;
		}
		
		int number = value.getNumber();
		for (Chapter chapter : chapterList) {
			int start = chapter.getStart();
			int end = chapter.getEnd();
			
			if(number >= start && number <= end) {
				String chapterName = chapter.getChapterName();
				
				if(! indexMap.containsKey(chapterName)) {
					indexMap.put(chapterName, new ArrayList<Character>());
				}
				
				ArrayList<Character> chapterList = indexMap.get(chapterName);
				chapterList.add(key);
				
				break;
			}
		}
	}

	@Override
	public void get(Character key) {
		hanjaMap.get(key);
	}

	@Override
	public Hanja set(Character key, Hanja value) {
		return hanjaMap.put(key, value);
	}

	@Override
	public void remove(Character key) {
		hanjaMap.remove(key);
	}
	
	public Map<String, ArrayList<Character>> getIndexMap() {
		return indexMap;
	}

	public void setIndexMap(Map<String, ArrayList<Character>> indexMap) {
		this.indexMap = indexMap;
	}

	public List<Chapter> getChapterList() {
		return chapterList;
	}

	public Integer incrementAndGet () {
		if(index == null) {
			return null;
		}
		
		return index.incrementAndGet();
	}

	public static class Chapter {
		
		private String chapterName;
		
		private int start;
		
		private int end;
		
		public String getChapterName() {
			return chapterName;
		}

		public void setChapterName(String chapterName) {
			this.chapterName = chapterName;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("IndexRange [chapterName=");
			builder.append(chapterName);
			builder.append(", start=");
			builder.append(start);
			builder.append(", end=");
			builder.append(end);
			builder.append("]");
			return builder.toString();
		}

	}

}
