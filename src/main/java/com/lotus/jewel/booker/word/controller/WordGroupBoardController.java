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
import com.lotus.jewel.booker.word.model.WordGroupVto;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/wordGroupBoard")
public class WordGroupBoardController {

	private final static Logger logger = LoggerFactory.getLogger(WordGroupBoardController.class);
	
	private final static String SUB_PATH = "wordGroup/board/";
	
	@Autowired
	WordService wordService;
	
	@GetMapping("manage")
	public ModelAndView manage(WordGroupVto wordGroup, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "manage");
		return mav;
	}
	
	@GetMapping("wordList")
	public ModelAndView wordList(WordGroupVto wordGroup, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "wordList");
		
//		int total = wordService.getCountWord(word);
//		word.setTotal(total);
//		word.calcPage();
//		
//		
//		model.addAttribute("paging", word.getPaging());
//		
//		try {
//			List<WordVto> wordList = wordService.getWordListForPage(word);
//			model.addAttribute("wordList", wordList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return mav;
	}
	
	@GetMapping("section")
	public ModelAndView section(WordGroupVto wordGroup, Model model) {
		ModelAndView mav = new ModelAndView(SUB_PATH + "section");
		
//		String keyWord = word.getWord();
//		logger.info("keyWord : " + keyWord);
//		
//		WordVto wordDetail = null;
//		
//		if(StringUtils.hasLength(keyWord)) {
//			String searchWord = keyWord.split(" ")[0].trim();
//			wordDetail = wordService.getWord(searchWord);			
//		} else {
//			wordDetail = new WordVto();
//			wordDetail.setLank(2);
//			wordDetail.setDifficulty(1);
//		}
//		
//		model.addAttribute("wordDetail", wordDetail);
		
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Result<WordGroupVto> post(HttpServletRequest reqest,
			@ModelAttribute WordGroupVto wordGroup
//			@RequestParam Map<String,String> map
			) throws IOException {
		
//		if(!StringUtils.hasLength(word.getWord())) {
//			Result<WordGroupVto> resultValue = new Result<WordGroupVto>(null);
//			resultValue.fail();
//			resultValue.setMessage("word is empty");
//			
//			return resultValue;
//		}

		String formMethod = reqest.getParameter("formMethod");
		logger.info("formMethod : " +  formMethod);
		
		WordGroupVto result = null;
//		if("PUT".equals(formMethod)) {
//			int putResult = wordService.putWord(word);
//			if(putResult == 1) {
//				WordVto savedWord = wordService.getWord(word.getWord());
//				result = savedWord;
//			}
//			
//		} else if("DELETE".equals(formMethod)) {
//			wordService.removeWord(word);
//		}
		
		return new Result<WordGroupVto>(result);
	}
	
	@GetMapping("/inputWordGroup")
	public @ResponseBody Result<Map<String, Object>> inputWordGroup(WordGroupVto wordGroup) throws IOException {
		
		logger.info("inputWordGroup");
		int resultCount = 0;
//		
//		try {
//			resultCount = wordService.addWord(word);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultCount", resultCount);
		
		return new Result<Map<String, Object>>(result);
	}
	
}
