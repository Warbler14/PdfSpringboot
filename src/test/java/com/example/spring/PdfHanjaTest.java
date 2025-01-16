package com.example.spring;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.lotus.jewel.booker.pdf.converter.Hanja3ConvertStore;
import com.lotus.jewel.booker.pdf.converter.data.DataStore;
import com.lotus.jewel.booker.pdf.converter.data.HanjaDataStore;
import com.lotus.jewel.booker.pdf.converter.model.Hanja;
import com.lotus.jewel.booker.pdf.model.Pdf;
import com.lotus.jewel.booker.pdf.service.PdfService;

public class PdfHanjaTest {
	
	public static final String RESOURCE_PATH = "/src/main/resources";

	public static void main(String[] args) {
		File readableFile = getResourceFile("/pdf/hanja3.pdf");
		
		if(readableFile.exists()) {
			String fileApsolutePath = readableFile.getAbsolutePath();
			
			PdfService pdfService = new PdfService();
			Pdf<Character, Hanja> pdf = new Pdf<Character, Hanja>();
			pdf.setFilePath(fileApsolutePath);
			pdf.setConvertedStore(new Hanja3ConvertStore());
			DataStore<?, ?> dataStore = pdfService.readPdf(pdf);
			if(dataStore instanceof HanjaDataStore) {
				HanjaDataStore hanjaConvertStore = (HanjaDataStore)dataStore;
				
			}
			
		}
		
		
	}
	
	public static File getResourceFile(String filePath) {
		StringBuffer path = new StringBuffer(getAbsolutePath());
		path.append(RESOURCE_PATH).append(filePath);
		File readableFile = new File(path.toString());
		return readableFile;
	}
	
	public static String getAbsoluteRootPath() {
		File file = new File("");
		String rootPath = file.getAbsolutePath();
		return rootPath;
	}
	
	public static String getAbsolutePath() {
		Path path = Paths.get("");
		String directoryName = path.toAbsolutePath().toString();
		return directoryName;
	}
}
