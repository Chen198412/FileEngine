package com.xxx.test;

import java.io.File;

import com.xxx.fileengine.Engine;
import com.xxx.fileengine.EngineBuilder;

public class Test {

	public static void main(String[] args) throws Exception {
		EngineBuilder eb = new EngineBuilder();
		eb = eb.setEnableLogger(true);
		Engine e = eb.build();
		e.executeEXEFile(new File("D:/battoexe.exe"));
		e.close();
	}

}
