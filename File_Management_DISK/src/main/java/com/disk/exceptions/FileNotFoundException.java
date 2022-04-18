package com.disk.exceptions;

public class FileNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileNotFoundException() {
		super();
	}
	
	public FileNotFoundException(String errors) {
		super(errors);
	}
}