package com.lotus.jewel.booker.word.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotus.jewel.booker.word.mapper.WorkbookMapper;
import com.lotus.jewel.booker.word.model.WordVto;
import com.lotus.jewel.booker.word.model.WorkbookVto;


@Service
public class WorkbookService {

	private final static Logger logger = LoggerFactory.getLogger(WorkbookService.class);
	
	@Autowired
	private WorkbookMapper workbookMapper;
	
	@Autowired
	private WordService wordService;
	
//	workbook ------------------------------------------------------------
	
	public WorkbookVto getWorkbook(WorkbookVto workbook) {
		return workbookMapper.selectWorkbook(workbook);
	}
	
	public List<WorkbookVto> getWorkbookListForPage(WorkbookVto workbook) throws Exception {
		List<WorkbookVto> resultList = workbookMapper.selectWorkbookListForPage(workbook);
		return resultList;
	}
	
	public int countWorkbook() {
		return workbookMapper.countWorkbook();
	}
	
	public int addWorkbook(WorkbookVto workbook) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		workbook.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		WorkbookVto savedWorkbook = getWorkbook(workbook);
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		if(savedWorkbook == null) {
			workbook.setRegistDatetime(datetime);
			
			return workbookMapper.insertWorkbook(workbook);
		}
		return 0;
	}

	public int removeWorkbook(WorkbookVto workbook) {
		return workbookMapper.deleteWorkbook(workbook);
	}
	
//	workpage ------------------------------------------------------------
	
	public WorkbookVto getWorkpage(WorkbookVto workbook) {
		return workbookMapper.selectWorkpage(workbook);
	}
	
	public List<WorkbookVto> getWorkpageListForPage(WorkbookVto workbook) throws Exception {
		List<WorkbookVto> resultList = workbookMapper.selectWorkpageListForPage(workbook);
		return resultList;
	}
	
	public int countWorkpage(WorkbookVto workbook) {
		return workbookMapper.countWorkpage(workbook);
	}
	
	public int addWorkpage(WorkbookVto workbook) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		workbook.setModifyDatetime(datetime);
		
		long start = System.currentTimeMillis();
		
		WorkbookVto savedWorkpage = getWorkpage(workbook);
		
		logger.debug("duration " + (System.currentTimeMillis() - start));
		if(savedWorkpage == null) {
			workbook.setRegistDatetime(datetime);
			
			return workbookMapper.insertWorkpage(workbook);
		}
		return 0;
	}
	
	public int addWorkpageWords(WorkbookVto workbook) {
		
		int max = 5;
		int count = 0;
		String workDay = workbook.getWorkDay();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(cal.getTime());
		
		try {
			WordVto selectWord = new WordVto();
			selectWord.setLank(2);
			
			int total = wordService.getCountWord(selectWord);
			selectWord.setTotal(total);
			selectWord.calcPage();
			
			int lastPage = selectWord.getLastPage();

			WorkbookVto newWorkbook = new WorkbookVto();
			newWorkbook.setUserId("user");
			
			for (int page = 1 ; page < lastPage ; page++ ) {
				List<WordVto> wordList = wordService.getWordListForLank(selectWord);
				for(int idx = 0; idx < wordList.size() ; idx++) {
					WordVto w = wordList.get(idx);
					newWorkbook.setWord(w.getWord());
					
					WorkbookVto wb = workbookMapper.selectWorkpage(newWorkbook);
					if(wb == null) {
						addDefaultWorkPage(workDay, w, datetime);
						if(count++ >= max) {
							return count;
						}
					}
				}
				
				selectWord.calcStartEnd(page, 10);
			} //end
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private void addDefaultWorkPage(String workDay, WordVto word, String datetime) {
		WorkbookVto newWorkbook = new WorkbookVto();
		newWorkbook.setWorkDay(workDay);
		newWorkbook.setUserId("user");
		newWorkbook.setWord(word.getWord());
		newWorkbook.setRegistDatetime(datetime);
		newWorkbook.setModifyDatetime(datetime);
		
		addWorkpage(newWorkbook);
	}
	
	public int removeWorkpage(WorkbookVto workbook) {
		return workbookMapper.deleteWorkpage(workbook);
	}
}
