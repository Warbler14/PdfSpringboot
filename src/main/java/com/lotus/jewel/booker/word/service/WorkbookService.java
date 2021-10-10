package com.lotus.jewel.booker.word.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotus.jewel.booker.word.mapper.WorkbookMapper;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.model.Workbook;


@Service
public class WorkbookService {

	private final static Logger logger = LoggerFactory.getLogger(WorkbookService.class);
	
	@Autowired
	private WorkbookMapper workbookMapper;
	
	@Autowired
	private WordService wordService;
	
//	workbook ------------------------------------------------------------
	
	public Workbook getWorkbook(Workbook workbook) {
		return workbookMapper.selectWorkbook(workbook);
	}
	
	public List<Workbook> getWorkbookListForPage(Workbook workbook) throws Exception {
		List<Workbook> resultList = workbookMapper.selectWorkbookListForPage(workbook);
		return resultList;
	}
	
	public int countWorkbook() {
		return workbookMapper.countWorkbook();
	}
	
	public int addWorkbook(Workbook workbook) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		workbook.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		Workbook savedWorkbook = getWorkbook(workbook);
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		if(savedWorkbook == null) {
			workbook.setRegistDatetime(datetime);
			
			return workbookMapper.insertWorkbook(workbook);
		}
		return 0;
	}

	public int removeWorkbook(Workbook workbook) {
		return workbookMapper.deleteWorkbook(workbook);
	}
	
//	workpage ------------------------------------------------------------
	
	public Workbook getWorkpage(Workbook workbook) {
		return workbookMapper.selectWorkpage(workbook);
	}
	
	public List<Workbook> getWorkpageListForPage(Workbook workbook) throws Exception {
		List<Workbook> resultList = workbookMapper.selectWorkpageListForPage(workbook);
		return resultList;
	}
	
	public int countWorkpage(Workbook workbook) {
		return workbookMapper.countWorkpage(workbook);
	}
	
	public int addWorkpage(Workbook workbook) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		workbook.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		Workbook savedWorkpage = getWorkpage(workbook);
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		if(savedWorkpage == null) {
			workbook.setRegistDatetime(datetime);
			
			return workbookMapper.insertWorkpage(workbook);
		}
		return 0;
	}
	
	public void addWorkpageWords(Workbook workbook) {
		
		String workDay = workbook.getWorkDay();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		try {
			Word word = new Word();
			int total = wordService.getCountWord();
			word.setTotal(total);
			word.calcPage();
			
			List<Word> wordList = wordService.getWordListForPage(word);
			for (int idx = 0 ; idx < 5; idx++) {
				Word w = wordList.get(idx);
				Workbook newWorkbook = new Workbook();
				newWorkbook.setWorkDay(workDay);
				newWorkbook.setUserId("user");
				newWorkbook.setWord(w.getWord());
				newWorkbook.setRegistDatetime(datetime);
				newWorkbook.setModifyDatetime(datetime);
				
				addWorkpage(newWorkbook);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int removeWorkpage(Workbook workbook) {
		return workbookMapper.deleteWorkpage(workbook);
	}
}
