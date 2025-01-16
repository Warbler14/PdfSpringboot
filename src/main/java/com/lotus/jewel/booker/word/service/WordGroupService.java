package com.lotus.jewel.booker.word.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotus.jewel.booker.word.mapper.WordGroupMapper;
import com.lotus.jewel.booker.word.model.WordGroupVto;


@Service
public class WordGroupService {

	private final static Logger logger = LoggerFactory.getLogger(WordGroupService.class);
	
	@Autowired
	private WordGroupMapper wordGroupMapper;
	
	
	public List<WordGroupVto> getWordGroupListForPage(WordGroupVto wordGroup) throws Exception {
		List<WordGroupVto> resultList = wordGroupMapper.selectWordGroupListForPage(wordGroup);
		return resultList;
	}
	
	public int getCountWordGroup(WordGroupVto wordGroup) {
		return wordGroupMapper.countWordGroup(wordGroup);
	}
	
	public WordGroupVto getWordGroup(WordGroupVto wordGroup) {
		return wordGroupMapper.selectWordGroup(wordGroup);
	}

	public int addWordGroup(WordGroupVto wordGroup) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		wordGroup.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		WordGroupVto savedWordGroup = getWordGroup(wordGroup);
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		
		if(savedWordGroup == null) {
			wordGroup.setRegistDatetime(datetime);
			
			return wordGroupMapper.insertWordGroup(wordGroup);
		}
		return 0;
	}
	
	public int putWordGroup(WordGroupVto wordGroup) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		wordGroup.setModifyDatetime(datetime);
		
		WordGroupVto savedWordGroup = getWordGroup(wordGroup);
		if(savedWordGroup == null) {
			wordGroup.setRegistDatetime(datetime);
			
			return wordGroupMapper.insertWordGroup(wordGroup);
		}
		
		wordGroup.setModifyDatetime(datetime);
		
		int resultCount = wordGroupMapper.updateWordGroup(wordGroup);
		return resultCount;
	}
	
	public int removeWordGroup(WordGroupVto wordGroup) {
		return wordGroupMapper.deleteWordGroup(wordGroup);
	}
	
}
