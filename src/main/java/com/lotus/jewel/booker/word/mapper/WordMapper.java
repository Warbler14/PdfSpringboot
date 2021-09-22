package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.Word;



@Mapper
public interface WordMapper {

	public List<Word> selectAll();
}
