package com.disk.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.disk.domain.File;
import com.disk.exceptions.FileNotFoundException;

public interface FileService {
	
	//Uploading a file to disk and file details to DB
	public File uploadFile(MultipartFile file);
	
	//Fetching a single file
	public Resource loadFileAsResource(String fileName) throws FileNotFoundException;

	//Fetching details of all files
	public List<File> getAllFileDetails();

}
