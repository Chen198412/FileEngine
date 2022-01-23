package com.xxx.fileengine;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import com.xxx.fileengine.util.OSTool;

/**
 * 引擎的构造类
 * 
 * @author CKF
 *
 */
public class EngineBuilder implements Builder<Engine>{

	@Override
	public int hashCode() {
		return Objects.hash(EnableDelFile, EnableEngine, EnableShutdown, isClose);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EngineBuilder other = (EngineBuilder) obj;
		return EnableDelFile == other.EnableDelFile && EnableEngine == other.EnableEngine
				&& EnableShutdown == other.EnableShutdown && isClose == other.isClose;
	}

	private boolean EnableShutdown;

	private boolean EnableDelFile;

	private boolean EnableEngine;

	private boolean isClose;
	
	private boolean EnableLogger;
	
	private final Logger logger = Logger.getLogger("info");

	public EngineBuilder(boolean enableShutdown, boolean enableDelFile, boolean enableEngine, boolean enablelogger) {
		super();
		EnableShutdown = enableShutdown;
		EnableDelFile = enableDelFile;
		EnableEngine = enableEngine;
		EnableLogger = enablelogger;
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
	
	public EngineBuilder setEnableLogger(boolean b) {
		this.EnableLogger = b;
		return this;
	}

	public EngineBuilder() {
		this(true, true, true, true);
	}

	public EngineBuilder(boolean enableEngine) {
		this(true, true, enableEngine, true);
	}

	public EngineBuilder(boolean enableShutdown, boolean enableDelFile) {
		this(enableShutdown, enableDelFile, true, true);
	}

	/**
	 * 通过已设定的参数进行构造。
	 * 
	 * @return 构造完成的引擎。
	 */
	@Override
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
					if(EnableLogger) {
						logger.info("Shutdown!");
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
					if(EnableLogger) {
						logger.info("New a File " + File + "!");
					}
				} else {
					throw new NoEnableException("NoEnableEngine");
				}
			}

			@Override
			public void delFile(File delfile) throws NoEnableException {
				if (EnableEngine == true && isClose == false && EnableDelFile == true) {
					delfile.delete();
					if(EnableLogger) {
						logger.info("Del a file " + delfile.getName() + "!");
					}
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
					if(EnableLogger) {
						logger.info("Closed!");
					}
				}
			}

			@Override
			public boolean isClose() {
				return isClose;
			}

			@Override
			public Process executeCommand(String cmd) throws IOException {
				if (OSTool.getOSTYPE().equals(OSTool.OStype.WINDOWS)) {
					if(EnableLogger) {
						logger.info("execute a cmd " + cmd + "!");
					}
					return Runtime.getRuntime().exec("cmd /c " + cmd);
				}
				else {
					throw new IOException("OSException");
				}
			}

			@Override
			public void executeEXEFile(File file) throws IOException {
				if (OSTool.getOSTYPE().equals(OSTool.OStype.WINDOWS)) {
					Runtime.getRuntime().exec(file.getAbsolutePath());
					if(EnableLogger) {
						logger.info("execute a EXEfile " + file.getName() + "!");
					}
				}
				else {
					throw new IOException("OSException");
				}
			}
		};
	}
}
