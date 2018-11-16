package org.agcs.system.web.services.glpt;

import javax.servlet.http.HttpServletRequest;

public interface GlptNodeServiceI {
	
	/**
	 * 查询用户权限菜单
	 * @param request
	 * @return
	 */
	public String getNodePrivilege(HttpServletRequest request) throws Exception;

}
