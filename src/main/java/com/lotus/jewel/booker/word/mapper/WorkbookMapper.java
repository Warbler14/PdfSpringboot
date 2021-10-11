package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.Workbook;

@Mapper
public interface WorkbookMapper {

//	workbook ------------------------------------------------------------
	
	public List<Workbook> selectWorkbookListForPage(Workbook workbook);
	
	public int countWorkbook();
	
	public Workbook selectWorkbook(Workbook workbook);
	
	public int insertWorkbook(Workbook workbook);
	
	public int deleteWorkbook(Workbook workbook);
	
//	workpage ------------------------------------------------------------
	
	public List<Workbook> selectWorkpageListForPage(Workbook workbook);
	
	public int countWorkpage(Workbook workbook);
	
	public Workbook selectWorkpage(Workbook workbook);
	
	public int insertWorkpage(Workbook workbook);
	
	public int updateWorkpage(Workbook workbook);
	
	public int deleteWorkpage(Workbook workbook);
}
