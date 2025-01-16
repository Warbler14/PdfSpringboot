package com.lotus.jewel.booker.word.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lotus.jewel.booker.word.model.WorkbookVto;

@Mapper
public interface WorkbookMapper {

//	workbook ------------------------------------------------------------
	
	public List<WorkbookVto> selectWorkbookListForPage(WorkbookVto workbook);
	
	public int countWorkbook();
	
	public WorkbookVto selectWorkbook(WorkbookVto workbook);
	
	public int insertWorkbook(WorkbookVto workbook);
	
	public int deleteWorkbook(WorkbookVto workbook);
	
//	workpage ------------------------------------------------------------
	
	public List<WorkbookVto> selectWorkpageListForPage(WorkbookVto workbook);
	
	public int countWorkpage(WorkbookVto workbook);
	
	public WorkbookVto selectWorkpage(WorkbookVto workbook);
	
	public int insertWorkpage(WorkbookVto workbook);
	
	public int updateWorkpage(WorkbookVto workbook);
	
	public int deleteWorkpage(WorkbookVto workbook);
}
