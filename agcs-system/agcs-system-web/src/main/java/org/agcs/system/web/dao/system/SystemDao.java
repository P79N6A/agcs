package org.agcs.system.web.dao.system;

import java.util.List;
import java.util.Map;

import org.agcs.system.hibernate.antidao.annotation.AntiDao;
import org.agcs.system.hibernate.antidao.annotation.Arguments;
import org.agcs.system.hibernate.antidao.dao.AntiDaoSupportHiber;
import org.agcs.system.web.entity.glpt.GlptUserEntity;

@AntiDao
public interface SystemDao extends  AntiDaoSupportHiber<GlptUserEntity>{
	
	@Arguments({"glptUser", "page", "row"})
	public List<Map> getAllEntities(GlptUserEntity glptUser, int page, int row);
	
	

}
