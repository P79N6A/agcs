package org.agcs.system.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
* @Title: BaseController.java 
* @Package org.brave.web.controller 
* @Description:  上下文工具类
* @author vivian
* @date 2016-1-15 下午7:10:40 
* @version V1.0
 */
public class ContextHolderUtils {
	
	/**
	 * spring mvc获取request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		HttpSession session = getRequest().getSession();
		return session;
	}

}
