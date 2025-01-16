package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.WordVto;

@Mapper
public interface WordMapper {

	public List<WordVto> selectAll();
	
	public List<WordVto> selectWordListForPage(WordVto word);
	
	public int countWord(WordVto word);
	
	public List<WordVto> selectWordListForLank(WordVto word);
	
	public WordVto selectWord(String word);
	
	public int insertWord(WordVto word);
	
	public int updateWord(WordVto word);
	
	public int deleteWord(WordVto word);
	
}
