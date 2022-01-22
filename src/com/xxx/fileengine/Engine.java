package com.xxx.fileengine;

import java.io.File;
import java.io.IOException;

/**
 * 实现功能的引擎。
 * 
 * @author CKF
 *
 */
public interface Engine {

	void close() throws ClosedExcepion;

	void delFile(File delfile) throws NoEnableException;

	void shutdown() throws NoEnableException;

	void newfile(String File, String path) throws NoEnableException;

	boolean isClose();

	Process executeCommand(String cmd) throws IOException;

	void executeEXEFile(File file) throws IOException;
}
