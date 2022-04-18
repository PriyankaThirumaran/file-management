package com.disk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disk.domain.File;
import com.disk.exceptions.FileNotFoundException;
import com.disk.message.MessageResponse;
import com.disk.service.FileServiceImpl;

@RestController
@RequestMapping("/file")
public class FileController {

	  @Autowired
	  private FileServiceImpl fileService;
	  
	  
	  //Uploading a single file to disk and file details to DB
	  @PostMapping("/upload")
	  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	      fileService.uploadFile(file);
	
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
	    }
	  }
	  
	 //Uploading multiple files to disk and file details to DB
	  @PostMapping("/upload/multiple")
	  public ResponseEntity<MessageResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
	    String message = "";
	    for(MultipartFile file:files) {
		    fileService.uploadFile(file);
		    System.out.println("1");
	    }
		message = "Uploaded the files successfully";
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	  }
	
	
	  //Downloading a single file
	  @GetMapping("/download/{fileName}")
	  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException{
	    
		  Resource resource = fileService.loadFileAsResource(fileName);
	    
	    // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.print("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
	    
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        }
	  
	  //Fetching details of all files
	  @GetMapping("/get/all")
	  public ResponseEntity<List<File>> getListFiles() {
	      List<File> files = fileService.getAllFileDetails();
	      return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
	  
	  
}
