package com.disk.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_details")
public class File {
	
	  @Id
	  @Column(name="file_name")
	  private String fileName;
	
	  @Column(name="file_type")
	  private String fileType;
	  
	  @Column(name="file_size_in_bytes")
	  private long fileSizeInBytes;
	  
	  @Column(name="creation_time")
	  private Timestamp creationTime;
	  
	  @Column(name="storage_path")
	  private String storagePath;
	
	  public File() {
		super();
		// TODO Auto-generated constructor stub
	  }
	  

	public File(String fileName, String fileType, long fileSizeInBytes, Timestamp creationTime, String storagePath) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSizeInBytes = fileSizeInBytes;
		this.creationTime = creationTime;
		this.storagePath = storagePath;
	}


	public String getFileName() {
	    return fileName;
	  }
	
	  public void setFileName(String name) {
	    this.fileName = name;
	  }
	
	  public String getFileType() {
	    return fileType;
	  }
	
	  public void setFileType(String fileType) {
	    this.fileType = fileType;
	  }

	public long getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	public void setFileSizeInBytes(long fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	

}