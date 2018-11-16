package org.agcs.system.web.services.glpt.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.util.JsonUtil;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.dao.ICommonDao;
import org.agcs.system.web.entity.glpt.GlptNodeEntity;
import org.agcs.system.web.entity.glpt.GlptRoleEntity;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.agcs.system.web.services.glpt.GlptNodeServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("glptNodeService")
@Transactional
public class GlptNodeServiceImpl implements GlptNodeServiceI {
	
	private ICommonDao commonDao;
	
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public String getNodePrivilege(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		GlptUserEntity user = (GlptUserEntity) session.getAttribute(SysConst.USERBEAN);
		GlptRoleEntity role = (GlptRoleEntity) session.getAttribute(SysConst.USERROLE);
		String sql = "select t.* from (select gn.* from glpt_node gn inner join glpt_role_privilege grp on gn.node_code = grp.node_code where gn.status = 'T' and grp.role_code = ? " +
				"union select gn.* from glpt_node gn inner join glpt_user_privilege gup on gn.node_code = gup.node_code where gn.status = 'T' and gup.user_code = ? ) t " +
				"order by sort asc";
		//开始节点
		String startNode = request.getParameter("startNode");
		startNode = StringUtil.isEmpty(startNode)?"0":startNode;
		List<GlptNodeEntity> nodeList = this.commonDao.findBySql(GlptNodeEntity.class, sql, role.getRoleCode(), user.getUserCode());
		String json = JsonUtil.list2json(nodeList);
		return json;
	}

}
