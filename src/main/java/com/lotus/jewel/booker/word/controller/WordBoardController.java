package com.lotus.jewel.booker.word.controller;

import java.io.IOException;
import java.util.Date;
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
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/wordBoard")
public class WordBoardController {

	private final static Logger logger = LoggerFactory.getLogger(WordBoardController.class);
	
	private final static String SUB_PATH = "word/board/";
	
	@Autowired
	WordService wordService;
	
	@GetMapping("manage")
	public ModelAndView manage(Word word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "manage");
		return mav;
	}
	
	@GetMapping("wordList")
	public ModelAndView wordList(Word word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "wordList");
		
		int total = wordService.getCountWord(word);
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
	
	@GetMapping("section")
	public ModelAndView section(Word word, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "section");
		
		String keyWord = word.getWord();
		logger.info("keyWord : " + keyWord);
		
		Word wordDetail = null;
		
		if(StringUtils.hasLength(keyWord)) {
			String searchWord = keyWord.split(" ")[0].trim();
			wordDetail = wordService.getWord(searchWord);			
		} else {
			wordDetail = new Word();
			wordDetail.setLank(2);
			wordDetail.setDifficulty(1);
		}
		
		model.addAttribute("wordDetail", wordDetail);
		
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Result<Word> post(HttpServletRequest reqest,
			@ModelAttribute Word word
//			@RequestParam Map<String,String> map
			) throws IOException {
		
		if(!StringUtils.hasLength(word.getWord())) {
			Result<Word> resultValue = new Result<Word>(null);
			resultValue.fail();
			resultValue.setMessage("word is empty");
			
			return resultValue;
		}

		String formMethod = reqest.getParameter("formMethod");
		logger.info("formMethod : " +  formMethod);
		
		Word result = null;
		if("PUT".equals(formMethod)) {
			int putResult = wordService.putWord(word);
			if(putResult == 1) {
				Word savedWord = wordService.getWord(word.getWord());
				result = savedWord;
			}
			
		} else if("DELETE".equals(formMethod)) {
			wordService.removeWord(word);
		}
		
		return new Result<Word>(result);
	}
	
	@GetMapping("/inputWord")
	public @ResponseBody Result<Map<String, Object>> inputWord(Word word) throws IOException {
		
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
