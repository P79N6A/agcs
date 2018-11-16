package org.agcs.system.codegenerate.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class CodeStringUtils {
	
	public CodeStringUtils(){
		
	}
	
	public static String getInitialSmall(String str){
		if(StringUtils.isNotBlank(str)){
			str = (new StringBuilder(String.valueOf(str.substring(0, 1).toLowerCase()))).append(str.substring(1)).toString();
		}
		return str;
	}
	
	public static String formatDate(Date date){
		if(date == null){
			return null;
		}else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		}
	}
	
	public static String formatDate(Date date, String formatStr){
		if(date == null){
			return null;
		}else{
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.format(date);
		}
	}
	
}
