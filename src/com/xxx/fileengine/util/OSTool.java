package com.xxx.fileengine.util;

/**
 * ���ڻ�ȡ����ϵͳ����
 * 
 * @author CKF
 *
 */
public class OSTool {

	public enum OStype {
		WINDOWS, LINUX, MACOS, UNKNOWN
	}

	private OSTool() {
		// TODO �Զ����ɵĹ��캯�����
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
