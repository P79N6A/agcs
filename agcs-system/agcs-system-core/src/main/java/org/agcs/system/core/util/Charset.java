/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * 
 * Prohibition is granted, charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), limitation the rights to use, copy, 
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 * 
 */
package org.agcs.system.core.util;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: Charset
 * @Package cn.fengwoo.easysettlement.utils
 * @Description: TODO
 * @Author <a href="mailto:jacky@fengwoo.cn">Jacky Fang</a>
 * @Date 2014-11-17 下午6:01:03
 * @Version EasySettlement 1.0.0
 */
public class Charset {
	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public static String iso2utf8(String str) {
		if (str != null) {
			// // 用旧的字符编码解码字符串。解码可能会出现异常。
			// byte[] bs = str.getBytes("ISO-8859-1");
			// // 用新的字符编码生成字符串
			// return new String(bs, "UTF-8");
			try {
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String utf82iso(String str) {
		if (str != null) {
			// // 用旧的字符编码解码字符串。解码可能会出现异常。
			// byte[] bs = str.getBytes("ISO-8859-1");
			// // 用新的字符编码生成字符串
			// return new String(bs, "UTF-8");
			try {
				return new String(str.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		System.err.println(iso2utf8("sdfdsf低速复苏的 "));
		System.err.println(utf82iso("å¼èé"));
	}
}
