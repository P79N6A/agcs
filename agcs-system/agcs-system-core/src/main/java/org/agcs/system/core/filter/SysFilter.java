package org.agcs.system.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.log4j.Logger;

public class SysFilter implements Filter {
	private static final Logger log = Logger.getLogger(SysFilter.class);
	public static HashMap keyMap = null;
	public static String path;

	public void destroy() {
	}
	
	@SuppressWarnings("rawtypes")
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
//		if (request.getMethod().equalsIgnoreCase("get")) {
//			//this.encoding(request);
//		}
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		String ip;
		try {
			ip = ToolsUtil.getHostIP();
			session.setAttribute("ip", ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//加载特殊替换字符
		if(keyMap == null){
			String contentpath = this.getClass().getResource("/").getPath().substring(1);
			String filepath = "/"+contentpath+SysConst.KEYWORD;
			keyMap = (HashMap)PropertiesUtil.readProperties(filepath);
		}
		
		chain.doFilter(new KeyWordRequestWrapper(request, keyMap), response);
		return;
	}

	/**
	 * GET提交方式中文编码过滤
	 * @param request
	 */
	@SuppressWarnings("unused")
	private void encoding(HttpServletRequest request) {
		Iterator<?> iter = request.getParameterMap().values().iterator();
		while (iter.hasNext()) {
			String[] parames = (String[]) iter.next();
			for (int i = 0; i < parames.length; i++) {
				try {
					parames[i] = new String(parames[i].getBytes("ISO-8859-1"),"UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}
	
}
