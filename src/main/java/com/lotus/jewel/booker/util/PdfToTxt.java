package com.lotus.jewel.booker.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToTxt {

	public static void main(String[] args) {
		
		String path = "/Users/kook/Documents/it books/java/java8inaction.pdf";
		
		File file = new File(path);
		
		
		PDDocument document = null;
		
		try {
			
			File storeDirectory = addDirectory(file.getParentFile(), file.getName().substring(0, file.getName().lastIndexOf(".")));
			
			document = PDDocument.load(file);
			
			PDPageTree pageTree = document.getPages();
			int endPage = pageTree.getCount();
			
			int offset = 10;
			int index = 1;
			
			do {
				int offsetIndex = index + offset - 1;
				if(offsetIndex > endPage) {
					offsetIndex = endPage;
				}
				
				String pageFileName 
					= getPageName(storeDirectory, file.getName() + index + "-" +  offsetIndex + ".txt");
				
				String[] lines = getLines(document, index, offsetIndex);
				
				writeFile(pageFileName, lines);
				
				index = offsetIndex + 1;
			} while(index < endPage);
			
			
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
	
	public static File addDirectory(File directory, String directoryName) throws IOException {
		StringBuilder pathBuilder
			= new StringBuilder(directory.getCanonicalPath()).append(File.separator).append(directoryName);
		String path = pathBuilder.toString();
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		return file;
	}
	
	public static String getPageName(File directory, String fileName) throws IOException {
		StringBuilder pathBuilder
			= new StringBuilder(directory.getCanonicalPath()).append(File.separator).append(fileName);
		return pathBuilder.toString();
	}
	
	public static String[] getLines(PDDocument document, int startPage, int endPage) throws IOException {
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(startPage);
		pdfStripper.setEndPage(endPage);
  

		
		String ln = "log4j.logger.org.apache.pdfbox.io.ScratchFileBuffer";
		java.util.logging.Logger.getLogger(ln).setLevel(java.util.logging.Level.WARNING);

//		  // Try Log4J as backend
//		  org.apache.log4j.Logger.getLogger(ln).setLevel(org.apache.log4j.Level.WARN);
//
//		  // Try another backend
//		  Log4JLoggerFactory.getInstance().getLogger(ln).setLevel(java.util.logging.Level.WARNING);
		  
//		LogManager.getLogger(Class.forName("log4j.logger.org.apache.pdfbox.io.ScratchFileBuffer")).setLevel(Level.FATAL);
		
		
//		import org.apache.commons.logging.Log;
//		import org.apache.commons.logging.LogFactory;
		//log4j.logger.org.apache.pdfbox.io.ScratchFileBuffer=WARN
		
		
		String pdfFileInText = pdfStripper.getText(document);
		
		String[] lines = pdfFileInText.split("\\r\\n");
		return lines;
	}
	
	public static void writeFile(String fileName, String[] lines) {
		File file = new File(fileName);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (String line : lines) {
				writer.write(line);
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
