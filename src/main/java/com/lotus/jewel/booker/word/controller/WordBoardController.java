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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.http.Result;
import com.lotus.jewel.booker.word.model.WordVto;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/wordBoard")
public class WordBoardController {

	private final static Logger logger = LoggerFactory.getLogger(WordBoardController.class);
	
	private final static String SUB_PATH = "word/board/";
	
	@Autowired
	WordService wordService;
	
	@GetMapping("view")
	public ModelAndView view(WordVto word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "view");
		return mav;
	}
	
	@GetMapping("wordList")
	public ModelAndView wordList(WordVto word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "wordList");
		
		int total = wordService.getCountWord(word);
		word.setTotal(total);
		word.calcPage();
		
		
		model.addAttribute("paging", word.getPaging());
		
		try {
			List<WordVto> wordList = wordService.getWordListForPage(word);
			model.addAttribute("wordList", wordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@GetMapping("wordGroupList")
	public ModelAndView wordGroupList(WordVto word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "wordGroupList");
		
		int total = wordService.getCountWord(word);
		word.setTotal(total);
		word.calcPage();
		
		
		model.addAttribute("paging", word.getPaging());
		
		try {
			List<WordVto> wordList = wordService.getWordListForPage(word);
			model.addAttribute("wordList", wordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@GetMapping("wordInfo")
	public ModelAndView wordInfo(WordVto word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "wordInfo");
		
		String keyWord = word.getWord();
		logger.info("keyWord : " + keyWord);
		
		WordVto wordDetail = null;
		
		if(StringUtils.hasLength(keyWord)) {
			String searchWord = keyWord.split(" ")[0].trim();
			wordDetail = wordService.getWord(searchWord);			
		} else {
			wordDetail = new WordVto();
			wordDetail.setLank(2);
			wordDetail.setDifficulty(1);
		}
		
		model.addAttribute("wordDetail", wordDetail);
		
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Result<WordVto> post(HttpServletRequest reqest,
			@ModelAttribute WordVto word
//			@RequestParam Map<String,String> map
			) throws IOException {
		
		if(!StringUtils.hasLength(word.getWord())) {
			Result<WordVto> resultValue = new Result<WordVto>(null);
			resultValue.fail();
			resultValue.setMessage("word is empty");
			
			return resultValue;
		}

		String formMethod = reqest.getParameter("formMethod");
		logger.info("formMethod : " +  formMethod);
		
		WordVto result = null;
		if("PUT".equals(formMethod)) {
			int putResult = wordService.putWord(word);
			if(putResult == 1) {
				WordVto savedWord = wordService.getWord(word.getWord());
				result = savedWord;
			}
			
		} else if("DELETE".equals(formMethod)) {
			wordService.removeWord(word);
		}
		
		return new Result<WordVto>(result);
	}
	
	@GetMapping("/inputWord")
	public @ResponseBody Result<Map<String, Object>> inputWord(WordVto word) throws IOException {
		
		logger.info("inputWord");
		int resultCount = 0;
		
		try {
			resultCount = wordService.addWord(word);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultCount", resultCount);
		
		return new Result<Map<String, Object>>(result);
	}
	
}
