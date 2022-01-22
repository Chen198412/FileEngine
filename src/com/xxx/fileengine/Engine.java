package com.xxx.fileengine;

import java.io.File;

public interface Engine {
	
	void close();
	
	void delFile(File delfile) throws NoEnableException;
	
	void shutdown() throws NoEnableException ;
	
	void newfile(String File, String path) throws NoEnableException;

	boolean isClose();
}
