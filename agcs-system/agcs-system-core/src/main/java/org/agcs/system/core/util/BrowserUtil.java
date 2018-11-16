package org.agcs.system.core.util;

import javax.servlet.http.HttpServletRequest;

public class BrowserUtil {
	
	//判断是否是IE
	public static boolean idIE(HttpServletRequest request){
		return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0 || 
				request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11")>0)?true:false;
	}
	
	

}
