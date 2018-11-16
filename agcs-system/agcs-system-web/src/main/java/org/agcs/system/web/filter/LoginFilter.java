package org.agcs.system.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.util.ToolsUtil;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LoginFilter implements Filter{
	
	private static final Logger log = Logger.getLogger(LoginFilter.class);
	private Set<String> exclude = new HashSet<String>();
	private FilterConfig filterConfig = null;
	public static String path;

	public void destroy() {
	}
	
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String uri = request.getRequestURI();

		/******************测试环境打开 开始**********************/
//		GlptUser temp = new GlptUser();
//		temp.setUser_code("admin");
//		temp.setPassword("53F139E187E60F8376343990737B8FEC");
//		temp.setUser_id(1);
//		temp.setUser_name("超级管理员");
//		GlptRole role = new GlptRole();
//		role.setRole_id(1);
//		role.setRole_code("admin");
//		role.setRole_name("超级管理员");
//		session.setAttribute(SysConst.USERBEAN, temp);
//		session.setAttribute(SysConst.USERROLE, role);
		/******************测试环境打开 结束**********************/
		//log4j日志记录
		MDC.put("url", uri);
		MDC.put("ip", ToolsUtil.getIpAddr(request));
		GlptUserEntity user = (GlptUserEntity)session.getAttribute(SysConst.USERBEAN);
		if(user != null){
			MDC.put("usercode", user.getUserCode());
			MDC.put("username", user.getUserName());
		}
		
		if (allow(path, uri, exclude)) {
			chain.doFilter(request, response);
			return;
		} else if (session.getAttribute(SysConst.USERBEAN) != null) {
			chain.doFilter(request, response);
			return;
		} else if (session.getAttribute(SysConst.USERBEAN) == null) {
			response.getWriter().println("<script>alert('登陆超时,请重新登陆');window.top.location.replace('"+path+"/pages/login/login.jsp')</script>");
			return;
		}
		response.getWriter().println("<script>window.top.location.replace('"+path+"/pages/login/login.jsp')</script>");
		return;
	}

	/**
	 * URL地址过滤
	 * @param path
	 * @param uri
	 * @param urls
	 * @return boolean
	 */
	private boolean allow(String path, String uri, Set<String> urls) {
		for (String url : urls) {
			if (url.startsWith("*")) {
				if (uri.endsWith(url.substring(1))) {
					return true;
				}
			} else if (url.endsWith("*")) {
				if (uri.startsWith(url.substring(0, url.length() - 1))) {
					return true;
				}
			} else {
				if (uri.contains(path + url)) {
					return true;
				}
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		String values = config.getInitParameter("exclude");
		if (!"".equals(values) && values != null) {
			String[] params = values.split("\\|");
			for (String param : params) {
				exclude.add(param);
			}
		}
		
		log.info(exclude);
	}

}
