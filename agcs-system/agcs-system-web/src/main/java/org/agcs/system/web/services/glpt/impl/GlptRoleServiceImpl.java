package org.agcs.system.web.services.glpt.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.web.entity.glpt.GlptRoleEntity;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.agcs.system.web.services.glpt.GlptRoleServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("glptRoleService")
@Transactional
public class GlptRoleServiceImpl implements GlptRoleServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public List<GlptRoleEntity> getUserRole(HttpServletRequest request, GlptUserEntity user){
		List<GlptRoleEntity> roleList = null;
		String hql = "from GlptRoleEntity a where a.roleCode in (select t.roleCode from GlptUserRoleEntity t where t.userCode = ?)";
		roleList = this.commonDao.findByHQl(hql, user.getUserCode());
		return roleList;
	}

}
