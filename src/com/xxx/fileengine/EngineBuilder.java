package com.xxx.fileengine;

import java.io.File;
import java.io.IOException;

import com.xxx.fileengine.util.OSTool;

/**
 * 引擎的构造类
 * 
 * @author CKF
 *
 */
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

	public EngineBuilder(boolean enableEngine) {
		this(true, true, enableEngine);
	}

	public EngineBuilder(boolean enableShutdown, boolean enableDelFile) {
		this(enableShutdown, enableDelFile, true);
	}

	/**
	 * 通过已设定的参数进行构造。
	 * 
	 * @return 构造完成的引擎。
	 */
	public Engine build() {
		return new Engine() {

			@Override
			public void shutdown() throws NoEnableException {
				if (EnableShutdown == true && EnableEngine == true && isClose == false) {
					try {
						Runtime.getRuntime().exec("shutdown /p");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					throw new NoEnableException("NoEnableShutdown or NoEnableEngine");
				}
			}

			@Override
			public void newfile(String File, String path) throws NoEnableException {
				if (EnableEngine == true && isClose == false) {
					File f = new File(path + File);
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					throw new NoEnableException("NoEnableEngine");
				}
			}

			@Override
			public void delFile(File delfile) throws NoEnableException {
				if (EnableEngine == true && isClose == false && EnableDelFile == true) {
					delfile.delete();
				} else {
					throw new NoEnableException("NoEnableEngine");
				}
			}

			@Override
			public void close() throws ClosedExcepion {
				if (isClose == true) {
					throw new ClosedExcepion("Is Closed");
				} else {
					isClose = true;
				}
			}

			@Override
			public boolean isClose() {
				return isClose;
			}

			@Override
			public Process executeCommand(String cmd) throws IOException {
				if (OSTool.getOSTYPE().equals(OSTool.OStype.WINDOWS))
					return Runtime.getRuntime().exec("cmd /c " + cmd);
				else
					throw new IOException("OSException");
			}

			@Override
			public void executeEXEFile(File file) throws IOException {
				if (OSTool.getOSTYPE().equals(OSTool.OStype.WINDOWS))
					Runtime.getRuntime().exec(file.getAbsolutePath());
				else
					throw new IOException("OSException");
			}
		};
	}
}
