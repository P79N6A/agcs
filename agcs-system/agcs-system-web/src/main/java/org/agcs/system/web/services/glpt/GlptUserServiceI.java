package org.agcs.system.web.services.glpt;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.agcs.system.web.entity.glpt.GlptUserEntity;

public interface GlptUserServiceI {
	
	/**
	 * 查询用户信息
	 * @param request
	 * @param userCode
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public List<GlptUserEntity> getUserList(HttpServletRequest request, String userCode, String password) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param user
	 * @throws Exception
	 */
	public void updatePwd(HttpServletRequest request, GlptUserEntity user) throws Exception;
	
	/**
	 * 检查用户是否存在
	 */
	public GlptUserEntity checkUserExit(GlptUserEntity user);
	

}
