package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.WordGroupVto;

@Mapper
public interface WordGroupMapper {
	
	public List<WordGroupVto> selectWordGroupListForPage(WordGroupVto wordGroup);
	
	public int countWordGroup(WordGroupVto wordGroup);
	
	public List<WordGroupVto> selectWordGroupList(WordGroupVto wordGroup);
	
	public WordGroupVto selectWordGroup(WordGroupVto wordGroup);
	
	public int insertWordGroup(WordGroupVto wordGroup);
	
	public int updateWordGroup(WordGroupVto wordGroup);
	
	public int deleteWordGroup(WordGroupVto wordGroup);
}
