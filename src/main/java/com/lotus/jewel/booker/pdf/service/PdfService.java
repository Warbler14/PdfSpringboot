package com.lotus.jewel.booker.pdf.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lotus.jewel.booker.pdf.converter.ConvertStore;
import com.lotus.jewel.booker.pdf.converter.data.DataStore;
import com.lotus.jewel.booker.pdf.model.Pdf;



@Service
public class PdfService {

	
	public DataStore<?, ?> readPdf(Pdf<?, ?> pdf){
		if(pdf == null) {
			return null;
		}
		
		String filePath = pdf.getFilePath();
		if(! StringUtils.hasLength(filePath)) {
			return null;
		}
		
		ConvertStore<?, ?> convertedStore = pdf.getConvertedStore();
		if(convertedStore == null) {
			return null;
		}
		
		int startPage = pdf.getStartPage();
		int endPAge = pdf.getEndPage();
		
		File file = new File(filePath);
		
		PDDocument document = null;
		
		try {
			document = PDDocument.load(file);
			
			PDPageTree pageTree = document.getPages();
//			int count = pageTree.getCount();
			
			PDFTextStripper pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(startPage);
			pdfStripper.setEndPage(endPAge);
        
			String pdfFileInText = pdfStripper.getText(document);
			pdfFileInText = pdfFileInText.replaceAll("\\r\\n", "\\n");
			
			String[] lines = pdfFileInText.split("\\n");
			
			for (String line : lines) {
				convertedStore.convertLineAndStore(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return convertedStore.getDataStore();
	}
	
}
