package com.lotus.jewel.booker.storage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.storage.service.StorageService;


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
	StorageService fileService;
	
	@GetMapping("/file")
	public ModelAndView filePage() {
		ModelAndView mav = new ModelAndView(SUB_PATH + "file");
		
		List<String> fileList = fileService.getResourceList(null);
		
		mav.addObject("fileList", fileList);
		
		return mav;
	}
	
	@RequestMapping("/put")
	public void put(@RequestParam("file") MultipartFile file, HttpServletRequest reqest, HttpServletResponse response) throws IOException {
		
		fileService.saveResource(file);
		
		// save to db
		
		
		
		
		response.sendRedirect("/storage/file");
	}
	
	@GetMapping("/delete/{fileName}")
	public void fileDelete(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
		fileService.deleteResource(fileName);
		
		response.sendRedirect("/storage/file");
	}

	@GetMapping("/down/{fileName}")
	public ResponseEntity<Resource> fileDownload(@PathVariable("fileName") String fileName) throws IOException {
		Resource resource = fileService.getResource(fileName);
	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	            .body(resource);
	}

	
}
