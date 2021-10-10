package com.lotus.jewel.booker.word.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.http.Result;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.model.Workbook;
import com.lotus.jewel.booker.word.service.WordService;
import com.lotus.jewel.booker.word.service.WorkbookService;


@Controller
@RequestMapping("/workbook")
public class WorkbookController {

	private final static Logger logger = LoggerFactory.getLogger(WorkbookController.class);
	
	private final static String SUB_PATH = "word/workbook/";
	
	@Autowired
	WordService wordService;
	
	@Autowired
	private WorkbookService workbookService;
	
	@GetMapping("manage")
	public ModelAndView boardList(Workbook workbook, Model model) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "manage");
		
		int total = workbookService.countWorkbook();
		workbook.setTotal(total);
		workbook.calcPage();
		
		
		model.addAttribute("paging", workbook.getPaging());
		
		try {
			List<Workbook> workbookList = workbookService.getWorkbookListForPage(workbook);
			model.addAttribute("workbookList", workbookList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@GetMapping("workpage")
	public ModelAndView workpage(@ModelAttribute Workbook workbook, Model model) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "workpage");
		
		int total = workbookService.countWorkpage(workbook);
		workbook.setTotal(total);
		workbook.calcPage();
		
		model.addAttribute("workDay", workbook.getWorkDay());
		model.addAttribute("paging", workbook.getPaging());
		
		try {
			List<Workbook> workpageList = workbookService.getWorkpageListForPage(workbook);
			model.addAttribute("workpageList", workpageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@GetMapping("section")
public ModelAndView section(Word word, Model model) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "section");
		
		String keyWord = word.getWord();
		logger.info("keyWord " + keyWord);
		
		Word wordDetail = wordService.getWord(word.getWord());
		
		model.addAttribute("wordDetail", wordDetail);
		
		return mav;
	}

	@GetMapping("/addWorkbook")
	public @ResponseBody Result<Map<String, String>> addWorkbook() throws IOException {
		
		logger.info("addDay");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datetime = sdf.format(cal.getTime());
		
		Workbook workbook = new Workbook();
		workbook.setWorkDay(datetime);
		workbook.setUserId("user");
		
		workbookService.addWorkbook(workbook);
		
		Map<String, String> test = new HashMap<>();
		test.put("message", new Date().toString());
		
		return new Result<Map<String, String>>(test);
	}
	
	@GetMapping("/getWords")
	public @ResponseBody Result<Map<String, String>> getWords(Workbook workbook) throws IOException {
		
		logger.info("getWords");
		
		try {
			workbookService.addWorkpageWords(workbook);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, String> test = new HashMap<>();
		test.put("message", new Date().toString());
		
		return new Result<Map<String, String>>(test);
	}
	
	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public @ResponseBody Result<Map<String, String>> post(HttpServletRequest reqest,
//			@ModelAttribute Workbook workbook
//			) throws IOException {
//		
//		String formMethod = reqest.getParameter("formMethod");
//		
//		logger.info("formMethod : " +  formMethod);
//		
//		if("DELETE".equals(formMethod)) {
//			workbookService.removeWorkbook(workbook);
//		}
//		
//		
//		Map<String, String> test = new HashMap<>();
//		test.put("message", new Date().toString());
//		
//		return new Result<Map<String, String>>(test);
//	}
	

}
