package com.lotus.jewel.booker.storage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.response.Result;
import com.lotus.jewel.booker.storage.service.StorageService;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/storage")
public class StorageController {
	
	private final static Logger logger = LoggerFactory.getLogger(StorageController.class);
	
	private final static String SUB_PATH = "storage/";
	
	/*
	 * 자료 업로드
	 * 임시 파일로 저장
	 * 단어 추출
	 * db 에 일괄 저장
	 * 페이징
	 * 검색
	 * 
	 * */
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	WordService wordService;
	
	
	@GetMapping("/file")
	public ModelAndView filePage() {
		ModelAndView mav = new ModelAndView(SUB_PATH + "file");
		
		List<String> fileList = storageService.getResourceList(null);
		
		mav.addObject("fileList", fileList);
		
		return mav;
	}
	
	@RequestMapping("/put")
	public void put(@RequestParam("file") MultipartFile file, HttpServletRequest reqest, HttpServletResponse response) throws IOException {
		
		storageService.saveResource(file);
		
		// save to db
		
		
		response.sendRedirect("/storage/file");
	}
	
	@GetMapping("/delete/{fileName}")
	public void fileDelete(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
		storageService.deleteResource(fileName);
		
		response.sendRedirect("/storage/file");
	}

	@GetMapping("/down/{fileName}")
	public ResponseEntity<Resource> fileDownload(@PathVariable("fileName") String fileName) throws IOException {
		Resource resource = storageService.getResource(fileName);
	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	            .body(resource);
	}

	
	@GetMapping("/data")
	public @ResponseBody Result<Map<String, String>> data(HttpServletRequest reqest) throws IOException {
		
		String fileName = reqest.getParameter("fileName");
		
		storageService.loadDataFromFile(fileName);
		
		Map<String, String> test = new HashMap<>();
		test.put("message", new Date().toString());
		test.put("fileName",fileName);
		
		return new Result<Map<String, String>>(test);
	}
	
	@GetMapping("/putWord")
	public @ResponseBody Result<Map<String, String>> putWord(HttpServletRequest reqest) throws IOException {
		
		String fileId = reqest.getParameter("fileId");
		String text = reqest.getParameter("text");
		
		Word word = new Word();
		word.setFileId(fileId);
		word.setWord(text);
		
		wordService.putWords(word);
		
		Map<String, String> test = new HashMap<>();
		test.put("wod", word.toString());
		
		return new Result<Map<String, String>>(test);
	}
}
