package com.example.spring.pdf.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

	public void readPdf(String path) {
		
		File file = new File(path);
		
		PDDocument document = null;
		
		try {
			document = PDDocument.load(file);
			
			PDFTextStripper pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(2);
        
			String pdfFileInText = pdfStripper.getText(document);
			
			String[] lines = pdfFileInText.split("\\r\\n");
			
			for (String line : lines) {
				System.out.println(line);
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
		
	}
}
