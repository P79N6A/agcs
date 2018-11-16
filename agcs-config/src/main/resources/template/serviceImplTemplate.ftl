package ${bussiPackage}.services.${entityPackage}.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.agcs.system.hibernate.dao.ICommonDao;
import ${bussiPackage}.services.${entityPackage}.${entityName}ServiceI;

@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl implements ${entityName}ServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
