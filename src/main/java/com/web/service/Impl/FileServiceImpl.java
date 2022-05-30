package com.web.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.service.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		//file Name
		String name=multipartFile.getOriginalFilename();
		//random name generate
		String randomId=UUID.randomUUID().toString();
		String fileName=randomId.concat(name.substring(name.lastIndexOf(".")));
		//full Path
		String filePath=path + File.separator + fileName;
		//create folder if not created
		File file=new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		//file copy
		Files.copy(multipartFile.getInputStream(),Paths.get(filePath) );
		
		return name;
	}

	@Override
	public InputStream getResouce(String path, String fileName) throws FileNotFoundException {
		String fullPath=path + File.separator + fileName;
		InputStream iStream=new FileInputStream(fullPath);
		
		return iStream;
	}

}
