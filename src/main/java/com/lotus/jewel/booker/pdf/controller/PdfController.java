package com.lotus.jewel.booker.pdf.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.pdf.converter.PrintConvertStore;
import com.lotus.jewel.booker.pdf.model.Pdf;
import com.lotus.jewel.booker.pdf.service.PdfService;
import com.lotus.jewel.booker.word.model.WordVto;
import com.lotus.jewel.booker.word.service.WordService;



@RestController
@RequestMapping("/pdf")
public class PdfController {
	
	private final static Logger logger = LoggerFactory.getLogger(PdfController.class);
	
	private final static String SUB_PATH = "pdf/";
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private WordService wordService;
	
	@RequestMapping("/test01")
	public ModelAndView test01( Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "test01");
		
		String filePath = "/Users/kook/Documents/it books/pythonbasics.pdf";
		System.out.println("filePath : " + filePath);
		
		Pdf<Object, Object> pdf = new Pdf<Object, Object>();
		pdf.setFilePath(filePath);
		pdf.setConvertedStore(new PrintConvertStore());
		
		pdfService.readPdf(pdf);
		
		
		return mav;
	}
	
	@RequestMapping("/test02")
	public ModelAndView test02( Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "test02");
		
		List<WordVto> wordList = null;
		try {
			wordList = wordService.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("wordList", wordList);
		
		return mav;
	}

}
