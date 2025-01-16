package com.lotus.jewel.booker.pdf.converter.util;

public class CharacterUtil {

	public enum CharacterType {
		HangulJamo,								//한글 자모 (Hangul Jamo)
		HangulCompatibilityJamo,				//호환용 한글 자모 (Hangul Compatibility Jamo)
		HangulJamoExtendedA,					//한글 자모 확장 A (Hangul Jamo Extended A)
		HangulSyllables,						//한글 소리 마디 (Hangul Syllables)
		HangulJamoExtendedB,					//한글 자모 확장 B (Hangul Jamo Extended B)
		CJKUnifiedIdeographs,					//한중일 통합 한자 (CJK Unified Ideographs)
		CJKUnifiedIdeographsExtensionA,			//한중일 통합 한자 확장 - A (CJK Unified Ideographs Extension A)
		CJKUnifiedIdeographsExtensionB,			//한중일 통합 한자 확장 - B (CJK Unified Ideographs Extension B)
		CJKUnifiedIdeographsExtensionC,			//한중일 통합 한자 확장 - C (CJK Unified Ideographs Extension C)
		CJKUnifiedIdeographsExtensionD,			//한중일 통합 한자 확장 - D (CJK Unified Ideographs Extension D)
		CJKUnifiedIdeographsExtensionE,			//한중일 통합 한자 확장 - E (CJK Unified Ideographs Extension E)
		CJKUnifiedIdeographsExtensionF,			//한중일 통합 한자 확장 - F (CJK Unified Ideographs Extension F)
		CJKRadicalsSupplement,					//한중일 부수 보충 (CJK Radicals Supplement)
		CJKCompatibilityIdeographs,				//한중일 호환용 한자 (CJK Compatibility Ideographs)
		CJKCompatibilityIdeographsSupplement	//한중일 호환용 한자 보충 (CJK Compatibility Ideographs Supplement)
	}
	
	public static CharacterType getLanguageType(Character character) {
		
		int ch = character;
		if (ch >= 0x1100 && ch <= 0x11FF) { 
			//한글 자모 (Hangul Jamo)
			return CharacterType.HangulJamo;
		} else if(ch >= 0x3130 && ch <= 0x318F) {
			//호환용 한글 자모 (Hangul Compatibility Jamo)
			return CharacterType.HangulCompatibilityJamo;
		} else if(ch >= 0xA960 && ch <= 0xA97F) {
			//한글 자모 확장 A (Hangul Jamo Extended A)
			return CharacterType.HangulJamoExtendedA;
		}  else if(ch >= 0xAC00 && ch <= 0xD7AF) {
			//한글 소리 마디 (Hangul Syllables)
			return CharacterType.HangulSyllables;
		}  else if(ch >= 0xD7B0 && ch <= 0xD7FF) {
			//한글 자모 확장 B (Hangul Jamo Extended B)
			return CharacterType.HangulJamoExtendedB;
		}
		
		if (ch >= 0x4E00 && ch <= 0x9FCF) { 
			//한중일 통합 한자 (CJK Unified Ideographs)
			return CharacterType.CJKUnifiedIdeographs;
		} else if (ch >= 0x3400 && ch <= 0x4DBF) { 
			//한중일 통합 한자 확장 - A (CJK Unified Ideographs Extension A)
			return CharacterType.CJKUnifiedIdeographsExtensionA;
		} else if (ch >= 0x20000 && ch <= 0x2A6DF) {
			//한중일 통합 한자 확장 - B (CJK Unified Ideographs Extension B)
			return CharacterType.CJKUnifiedIdeographsExtensionB;
		} else if (ch >= 0x2A700 && ch <= 0x2B73F) {
			//한중일 통합 한자 확장 - C (CJK Unified Ideographs Extension C)
			return CharacterType.CJKUnifiedIdeographsExtensionC;
		} else if (ch >= 0x2B740 && ch <= 0x2B81F) {
			//한중일 통합 한자 확장 - D (CJK Unified Ideographs Extension D)
			return CharacterType.CJKUnifiedIdeographsExtensionD;
		} else if (ch >= 0x2B820 && ch <= 0x2CEAF) {
			//한중일 통합 한자 확장 - E (CJK Unified Ideographs Extension E)
			return CharacterType.CJKUnifiedIdeographsExtensionE;
		} else if (ch >= 0x2CEB0 && ch <= 0x2EBEF) {
			//한중일 통합 한자 확장 - F (CJK Unified Ideographs Extension F)
			return CharacterType.CJKUnifiedIdeographsExtensionF;
		} else if (ch >= 0x2E80 && ch <= 0x2EFF) {
			//한중일 부수 보충 (CJK Radicals Supplement)
			return CharacterType.CJKRadicalsSupplement;
		} else if (ch >= 0xF900 && ch <= 0xFAFF) {
			//한중일 호환용 한자 (CJK Compatibility Ideographs)
			return CharacterType.CJKCompatibilityIdeographs;
		} else if (ch >= 0x2F800 && ch <= 0x2FA1F) {
			//한중일 호환용 한자 보충 (CJK Compatibility Ideographs Supplement)
			return CharacterType.CJKCompatibilityIdeographsSupplement;
		}


		return null;
	}
	
//	https://zetawiki.com/wiki/유니코드_한자
}
