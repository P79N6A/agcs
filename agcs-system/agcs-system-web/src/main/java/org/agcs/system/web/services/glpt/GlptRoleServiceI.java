package org.agcs.system.web.services.glpt;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.web.entity.glpt.GlptRoleEntity;
import org.agcs.system.web.entity.glpt.GlptUserEntity;

public interface GlptRoleServiceI {
	
	public List<GlptRoleEntity> getUserRole(HttpServletRequest request, GlptUserEntity user);

}
