package com.xxx.test;

import java.io.File;

import com.xxx.fileengine.*;

public class Test {

	public static void main(String[] args) throws NoEnableException {
		EngineBuilder eb = new EngineBuilder(true, true, true);
		Engine e = eb.build();
		System.out.println(e.isClose());
	}

}
