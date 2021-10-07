package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.Word;

@Mapper
public interface WordMapper {

	public List<Word> selectAll();
	
	public List<Word> selectWordListForPage(Word word);
	
	public int countWord();
	
	public Word selectWord(String word);
	
	public int insertWord(Word word);
	
	public int updateWord(Word word);
	
	public int deleteWord(Word word);
}
