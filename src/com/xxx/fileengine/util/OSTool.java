package com.xxx.fileengine.util;

/**
 * 用于获取操作系统的类
 * 
 * @author CKF
 *
 */
public class OSTool {

	public enum OStype {
		WINDOWS, LINUX, MACOS, UNKNOWN
	}

	private OSTool() {
		// TODO 自动生成的构造函数存根
	}

	public static OStype getOSTYPE() {
		String s = System.getProperty("os.name");
		if (s.startsWith("Windows")) {
			return OStype.WINDOWS;
		} else if (s.startsWith("Linux")) {
			return OStype.LINUX;
		} else if (s.startsWith("Mac")) {
			return OStype.MACOS;
		} else {
			return OStype.UNKNOWN;
		}
	}
}
