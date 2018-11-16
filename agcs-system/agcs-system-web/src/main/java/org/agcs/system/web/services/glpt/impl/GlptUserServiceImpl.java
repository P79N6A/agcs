package org.agcs.system.web.services.glpt.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.util.DESCorder;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.agcs.system.web.services.glpt.GlptUserServiceI;
import org.springframework.stereotype.Service;

@Service("glptUserService")
public class GlptUserServiceImpl implements GlptUserServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public List<GlptUserEntity> getUserList(HttpServletRequest request, String userCode, String password) throws Exception{
		List<GlptUserEntity> userList = null;
		GlptUserEntity user = new GlptUserEntity();
		user.setUserCode(userCode);
		user.setPassword(DESCorder.encodeMD5(userCode+password));
		//userList = userMapper.findUserList(user);
		userList = commonDao.findByHQl("from GlptUserEntity ");
		return userList;
	}
	
	public void updatePwd(HttpServletRequest request, GlptUserEntity user) throws Exception{
		//userMapper.updateUserInfo(user);
	}
	
	public GlptUserEntity checkUserExit(GlptUserEntity user){
		String hql = "from GlptUserEntity t where user_code = ? and password = ?";
		List<GlptUserEntity> u = this.commonDao.findByHQl(hql, user.getUserCode(), user.getPassword());
		if(u != null && u.size() > 0){
			return u.get(0);
		}else{
			return null;
		}
		
	}

}
