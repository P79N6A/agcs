package org.agcs.system.web.controller.glpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.hibernate.util.EasyuiUtil;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.web.entity.glpt.GlptRoleEntity;
import org.agcs.system.web.services.glpt.GlptRoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* @Title: Controller
* @Description:  角色管理
* @author vivian
* @date 2016-02-18
* @version V1.0
 */

@Controller
@RequestMapping("/glptRoleController")
public class GlptRoleController extends BaseController{
	
	@Autowired
	private GlptRoleServiceI glptRoleService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 角色管理界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "glptRole")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("glpt/glptRoleList");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, GlptRoleEntity glptRole, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(GlptRoleEntity.class, dataGrid);
		CriteriaUtils.installHql(cq, glptRole, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, GlptRoleEntity glptRole){
		if(StringUtil.isNotEmpty(glptRole.getId())){
			//查询实体
			glptRole = this.commonService.getEntity(GlptRoleEntity.class, glptRole.getId());
			request.setAttribute("glptRolePage", glptRole);
		}
		return new ModelAndView("glpt/glptRole");
	}

}
