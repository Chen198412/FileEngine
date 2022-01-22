package com.xxx.fileengine;

import java.io.File;
import java.io.IOException;

public class EngineBuilder {

	private boolean EnableShutdown;

	private boolean EnableDelFile;

	private boolean EnableEngine;
	
	private boolean isClose;

	public EngineBuilder(boolean enableShutdown, boolean enableDelFile, boolean enableEngine) {
		super();
		EnableShutdown = enableShutdown;
		EnableDelFile = enableDelFile;
		EnableEngine = enableEngine;
	}

	public EngineBuilder setEnableShutdown(boolean b) {
		this.EnableShutdown = b;
		return this;
	}

	public EngineBuilder setEnableDelFile(boolean b) {
		this.EnableDelFile = b;
		return this;
	}

	public EngineBuilder setEnableEngine(boolean b) {
		this.EnableEngine = b;
		return this;
	}
	
	public EngineBuilder() {
		this(true, true, true);
	}

	public Engine build() {
		return new Engine() {
			
			@Override
			public void shutdown() throws NoEnableException {
				// TODO 自动生成的方法存根
				if(EnableShutdown == true && EnableEngine == true && isClose == false) {
					try {
						Runtime.getRuntime().exec("shutdown /p");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else {
					throw new NoEnableException("NoEnableShutdown or NoEnableEngine");
				}
			}
			
			@Override
			public void newfile(String File, String path) throws NoEnableException {
				// TODO 自动生成的方法存根
				if(EnableEngine == true && isClose == false) {
					File f = new File(path + File);
					try {
						f.createNewFile();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else {
					throw new NoEnableException("NoEnableEngine");
				}
			}
			
			@Override
			public void delFile(File delfile) throws NoEnableException {
				// TODO 自动生成的方法存根
				if(EnableEngine == true && isClose == false) {
					delfile.delete();
				}else {
					throw new NoEnableException("NoEnableEngine");
				}
			}
			
			@Override
			public void close() {
				// TODO 自动生成的方法存根
				isClose = true;
			}

			@Override
			public boolean isClose() {
				// TODO 自动生成的方法存根
				return isClose;
			}
		};
	}
}
