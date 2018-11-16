package org.agcs.system.web.services.glpt.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.web.services.glpt.GlptRolePrivilegeServiceI;

@Service("glptRolePrivilegeService")
@Transactional
public class GlptRolePrivilegeServiceImpl implements GlptRolePrivilegeServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
