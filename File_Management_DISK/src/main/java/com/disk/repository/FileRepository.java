package com.disk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.disk.domain.File;

public interface FileRepository extends JpaRepository<File, String> {
	
	//find storage path of the file using file name
	@Query(value= "SELECT storage_path FROM file_details WHERE file_name = ?1", nativeQuery = true)
	public String findStoragePath(String fileName);

}
