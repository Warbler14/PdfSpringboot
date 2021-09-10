package com.example.spring.pdf.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.pdf.model.Pdf;
import com.example.spring.pdf.service.PdfService;


@RestController
@RequestMapping("/pdf")
public class PdfController {
	
	private final static Logger logger = LoggerFactory.getLogger(PdfController.class);
	
	private final static String SUB_PATH = "pdf/";
	
	@Autowired
	private PdfService pdfService;
	
	@RequestMapping("/test01")
	public ModelAndView test01( Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "test01");
		
		String filePath = "/Users/kook/Documents/it books/pythonbasics.pdf";
		System.out.println("filePath : " + filePath);
		
		pdfService.readPdf(filePath);
		
		
		return mav;
	}

}
