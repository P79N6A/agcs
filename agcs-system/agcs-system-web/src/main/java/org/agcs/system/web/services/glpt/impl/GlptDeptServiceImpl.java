package org.agcs.system.web.services.glpt.impl;

import javax.annotation.Resource;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.web.services.glpt.GlptDeptServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("glptDeptService")
@Transactional
public class GlptDeptServiceImpl implements GlptDeptServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
