package com.lotus.jewel.booker.word.service;

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
}
