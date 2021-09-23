package com.lotus.jewel.booker.word.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.response.Result;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/word")
public class WordController {

	private final static Logger logger = LoggerFactory.getLogger(WordController.class);
	
	private final static String SUB_PATH = "word/";
	
	@Autowired
	WordService wordService;
	
	@GetMapping("boardList")
	public ModelAndView boardList(Word word, Model model
//			, @RequestParam(value="nowPage", required=false)String nowPage
//			, @RequestParam(value="cntPerPage", required=false)String cntPerPage
			) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "board");
		
		
		
		int total = wordService.getCountWord();
		word.setTotal(total);
		word.calcPage();
		
		
		model.addAttribute("paging", word.getPaging());
		
		try {
			List<Word> wordList = wordService.getWordListForPage(word);
			model.addAttribute("wordList", wordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
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
