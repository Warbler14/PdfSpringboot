package com.lotus.jewel.booker.word.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotus.jewel.booker.word.mapper.WordMapper;
import com.lotus.jewel.booker.word.model.Word;

@Service
public class WordService {

	@Autowired
	private WordMapper wordMapper;
	
	public List<Word> getAll() throws Exception {
		List<Word> resultList = wordMapper.selectAll();
		return resultList;
	}
	
	public List<Word> getWordListForPage(Word word) throws Exception {
		List<Word> resultList = wordMapper.selectWordListForPage(word);
		return resultList;
	}
	
	public int getCountWord() {
		return wordMapper.countWord();
	}
	
	public int addWords(Word word) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		word.setModifyDatetime(datetime);
		
		Word savedWord = wordMapper.selectWord(word);
		if(savedWord == null) {
			word.setRegistDatetime(datetime);
			word.setLank(1);
			word.setDifficulty(1);
			
			return wordMapper.insertWord(word);
		}
		return 0;
	}
	
	public int putWords(Word word) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		word.setModifyDatetime(datetime);
		
		Word savedWord = wordMapper.selectWord(word);
		if(savedWord == null) {
			word.setRegistDatetime(datetime);
			word.setLank(1);
			word.setDifficulty(1);
			
			return wordMapper.insertWord(word);
		}
		
		word.setModifyDatetime(datetime);
		return wordMapper.updateWord(word);
	}
	
}
