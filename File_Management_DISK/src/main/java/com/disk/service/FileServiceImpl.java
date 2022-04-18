package com.disk.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.disk.domain.File;
import com.disk.exceptions.FileNotFoundException;
import com.disk.exceptions.StorageException;
import com.disk.repository.FileRepository;

import org.springframework.util.StringUtils;

@Service("fileService")
public class FileServiceImpl implements FileService{

	  @Autowired
	  private FileRepository fileRepository;
	  
	  private String path = "D:/Spring Folder/";
	  
//	  @Value("${file.path")
//	  private String path;
	  
	  //Uploading a file to disk and file details to DB
	  public File uploadFile(MultipartFile file) throws StorageException{
		  
	      if (file.isEmpty()) {
	          throw new StorageException("Failed to store empty file");
	      }
	
	      try {
	          var fileName = StringUtils.cleanPath(file.getOriginalFilename());            // Normalize file name
	          var is = file.getInputStream();
	
	          Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
	          
	  	      File file1 = new File(fileName, file.getContentType(), file.getSize(), new Timestamp(System.currentTimeMillis()), path + fileName);
	  	      return fileRepository.save(file1);
	  	    
	      } catch (IOException e) {
	
	          var msg = String.format("Failed to store file %f", file.getName(), file.getSize());
	          throw new StorageException(msg, e);
	      }
	  }
	  
	  //Fetching a single file
	  public Resource loadFileAsResource(String fileName) throws FileNotFoundException{
	        try {
	            Path filePath = Paths.get(fileRepository.findStoragePath(fileName));
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new FileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new FileNotFoundException("File not found " + fileName);
	        }
	    }
	  
	  //Fetching details of all files
	  public List<File> getAllFileDetails() {
	      return fileRepository.findAll();
	  }
}
