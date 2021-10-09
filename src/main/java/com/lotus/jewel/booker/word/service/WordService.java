package com.lotus.jewel.booker.word.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lotus.jewel.booker.word.mapper.WordMapper;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.model.WordDetail;


@Service
public class WordService {

	private final static Logger logger = LoggerFactory.getLogger(WordService.class);
	
	@Autowired
	private WordMapper wordMapper;
	
	public List<Word> getAll() throws Exception {
		List<Word> resultList = wordMapper.selectAll();
		return resultList;
	}
	
	@Cacheable(value = "wordCache", key = "#text")
	public Word getWord(String text) {
		return wordMapper.selectWord(text);
	}
	
	public List<Word> getWordListForPage(Word word) throws Exception {
		List<Word> resultList = wordMapper.selectWordListForPage(word);
		return resultList;
	}
	
	public int getCountWord() {
		return wordMapper.countWord();
	}
	
	public WordDetail getWordDetail(Word word) {
		
		WordDetail wordDetail = new WordDetail();
		wordDetail.setWord(word);
		
		wordDetail.setDescription("text");
		
		
		return wordDetail;
	}
	
	
	
	public int addWord(Word word) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		word.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		Word savedWord = getWord(word.getWord());
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		
		
		if(savedWord == null) {
			word.setRegistDatetime(datetime);
			word.setLank(1);
			word.setDifficulty(1);
			
			return wordMapper.insertWord(word);
		}
		return 0;
	}
	
	public int putWord(Word word) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		word.setModifyDatetime(datetime);
		
		Word savedWord = getWord(word.getWord());
		if(savedWord == null) {
			word.setRegistDatetime(datetime);
			word.setLank(1);
			word.setDifficulty(1);
			
			return wordMapper.insertWord(word);
		}
		
		word.setModifyDatetime(datetime);
		return wordMapper.updateWord(word);
	}
	
	public int removeWord(Word word) {
		return wordMapper.deleteWord(word);
	}
	
}
