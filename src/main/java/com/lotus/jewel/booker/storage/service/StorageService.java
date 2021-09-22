package com.lotus.jewel.booker.storage.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.service.WordService;


@Service
public class StorageService {

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(StorageService.class);

	public final String fileSeparator = FileSystems.getDefault().getSeparator();

	private final String savePath = System.getProperty("user.dir") + fileSeparator + "storage";

	@Autowired
	private WordService wordService;
	
	@PostConstruct
	public void init() {
		if (!new File(savePath).exists()) {
			try {
				new File(savePath).mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
	
	// 데이터를 db 에 저장하면, 로컬에 저장한 파일과 불일치 발생가능
	// - 모든 데이터를 db 에 저장, db 파일이 커지므로 안하는게 좋겠
	// - 데이터를 db 에 저장하지 않고 임시 저장후 삭
	
	public List<String> getResourceList(String extention) {
		
		List<Word> wordList = null;
		try {
			wordList = wordService.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Word word : wordList) {
			logger.debug(word.toString());
		}
		
		
		
		File fileStore = new File(savePath);
		
		String [] storedFileNames = {};
		
		if(!StringUtils.hasLength(extention)) {
			storedFileNames = fileStore.list();
		} else {
			FilenameFilter filenameFilter = new FilenameFilter() {
				@Override 
				public boolean accept(File dir, String name) { 
					return name.endsWith(extention); 
				}
			};
			
			storedFileNames = fileStore.list(filenameFilter);
		}
		
		return new ArrayList<String>(Arrays.asList(storedFileNames));
	}

	public File saveResource(MultipartFile file) {
		String origFilename = file.getOriginalFilename();
		
		if(!StringUtils.hasLength(origFilename)) {
			logger.warn("Upload file not found");
			return null;
		}
		
        String filePath = savePath  + fileSeparator + origFilename;
        
        File saveFile = new File(filePath);
        try {
			file.transferTo(saveFile);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
        
        return saveFile;
	}
	
	public Resource getResource(String fileName) throws IOException {
		String filePath = savePath  + fileSeparator + fileName;
		
		Path path = Paths.get(filePath);
		
		return new InputStreamResource(Files.newInputStream(path));
	}
	
	public void deleteResource(String fileName) {
		String filePath = savePath  + fileSeparator + fileName;
		
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
	}
}
