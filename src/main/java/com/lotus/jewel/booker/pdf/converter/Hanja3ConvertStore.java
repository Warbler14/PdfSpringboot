package com.lotus.jewel.booker.pdf.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lotus.jewel.booker.pdf.converter.data.HanjaDataStore;
import com.lotus.jewel.booker.pdf.converter.data.HanjaDataStore.Chapter;
import com.lotus.jewel.booker.pdf.converter.model.Hanja;
import com.lotus.jewel.booker.pdf.converter.util.CharacterUtil;

public class Hanja3ConvertStore implements ConvertStore<Character, Hanja> {
	
	private HanjaDataStore dataStore;
	
	private String hanjaBuffer;
	
	public Hanja3ConvertStore() {
		super();
		
		List<Chapter> chapterList = new ArrayList<Chapter>();
		Chapter nine = new Chapter();	//50
		nine.setChapterName("nine");
		nine.setStart(1);
		nine.setEnd(50);
		chapterList.add(nine);
		Chapter eight = new Chapter();	//100
		eight.setChapterName("eight");
		eight.setStart(51);
		eight.setEnd(150);
		chapterList.add(eight);
		Chapter seven = new Chapter();	//150
		seven.setChapterName("seven");
		seven.setStart(151);
		seven.setEnd(300);
		chapterList.add(seven);
		Chapter six = new Chapter();	//150
		six.setChapterName("six");
		six.setStart(301);
		six.setEnd(450);
		chapterList.add(six);
		Chapter five = new Chapter();	//150
		five.setChapterName("five");
		five.setStart(451);
		five.setEnd(600);
		chapterList.add(five);
		Chapter four = new Chapter();	//300
		four.setChapterName("four");
		four.setStart(601);
		four.setEnd(900);
		chapterList.add(four);
		Chapter three = new Chapter();	//900
		three.setChapterName("three");
		three.setStart(901);
		three.setEnd(1800);
		chapterList.add(three);
		
		dataStore = new HanjaDataStore(chapterList);
		
	}

	@Override
	public boolean convertLineAndStore(String str) {
		if(!StringUtils.hasLength(str)) {
			return false;
		}
		
		System.out.println(str);
		
		Character firstCharacter = str.charAt(0);
		
		CharacterUtil.CharacterType type = CharacterUtil.getLanguageType(firstCharacter);
		
		if(type == null) {
			return false;
		}
		
		boolean status = true;
		try {
			switch(type) {
				case CJKUnifiedIdeographs:
				case CJKUnifiedIdeographsExtensionA:
				case CJKUnifiedIdeographsExtensionB:
				case CJKUnifiedIdeographsExtensionC:
				case CJKUnifiedIdeographsExtensionD:
				case CJKUnifiedIdeographsExtensionE:
				case CJKUnifiedIdeographsExtensionF:
				case CJKRadicalsSupplement:
				case CJKCompatibilityIdeographs:
				case CJKCompatibilityIdeographsSupplement:
					hanjaBuffer = str;
				default:
					status = false;
			}
			
			switch(type) {
				case HangulJamo:
				case HangulCompatibilityJamo:
				case HangulJamoExtendedA:
				case HangulSyllables:
				case HangulJamoExtendedB:
					if(hanjaBuffer == null) {
						break;
					}
					
					hangulJamoExtendB(str);
					
					hanjaBuffer = null;
					break;
				default:
					status = false;
			}
			
		} catch (Exception e) {
			status = false;
		}
		
		return status;
	}

	private void hangulJamoExtendB(final String str) {
		String [] hanjaCharacterArray = hanjaBuffer.split(" ");
		String [] hangulCharacterArray = str.split(" ");
		
		for(int idx=0, idy = 0; idx < hanjaCharacterArray.length; idx++, idy += 2) {
			Character ch = hanjaCharacterArray[idx].charAt(0);
			String mean = hangulCharacterArray[idy];
			String pronunciation = hangulCharacterArray[idy+1];
			
			int number = dataStore.incrementAndGet();
			
			Hanja hanja = new Hanja();
			hanja.setCharacter(ch);					//'大'
			hanja.setMean(mean);					//"큰"
			hanja.setPronunciation(pronunciation);	//""
			hanja.setNumber(number);
			
			dataStore.add(ch, hanja);
		}
	}
	
	@Override
	public HanjaDataStore getDataStore() {
		return dataStore;
	}
	
	

}
