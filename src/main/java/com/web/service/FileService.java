package com.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadImage(String path,MultipartFile multipartFile)throws IOException;
	
	InputStream getResouce(String path,String fileName) throws FileNotFoundException;
}
