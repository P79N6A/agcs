package org.agcs.system.web.controller.glpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.agcs.system.web.entity.glpt.GlptRolePrivilegeEntity;
import org.agcs.system.web.services.glpt.GlptRolePrivilegeServiceI;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.util.EasyuiUtil;
import org.springframework.web.servlet.ModelAndView;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.hibernate.services.CommonServiceI;

/**
* @Title: Controller
* @Description:  角色权限
* @author vivian
* @date 2016-04-23
* @version V1.0
 */

@Controller
@RequestMapping("/glptRolePrivilegeController")
public class GlptRolePrivilegeController extends BaseController{
	
	@Autowired
	private GlptRolePrivilegeServiceI glptRolePrivilegeService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 角色权限界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "glptRolePrivilege")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("glpt/glptRolePrivilegeList");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, GlptRolePrivilegeEntity glptRolePrivilege, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(GlptRolePrivilegeEntity.class, dataGrid);
		CriteriaUtils.installHql(cq, glptRolePrivilege, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, GlptRolePrivilegeEntity glptRolePrivilege){
		if(StringUtil.isNotEmpty(glptRolePrivilege.getId())){
			String cztype = request.getParameter("cztype");
			//查询实体
			glptRolePrivilege = this.commonService.getEntity(GlptRolePrivilegeEntity.class, glptRolePrivilege.getId());
			request.setAttribute("glptRolePrivilegePage", glptRolePrivilege);
			request.setAttribute("cztype", cztype);
		}
		return new ModelAndView("glpt/glptRolePrivilege");
	}
	
	/**
	 * 保存角色权限
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request, GlptRolePrivilegeEntity glptRolePrivilege){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			if(StringUtil.isNotEmpty(glptRolePrivilege.getId())){
				this.commonService.saveOrUpdate(request, glptRolePrivilege);
				j.setMsg("角色权限修改成功!");
			}else{
				this.commonService.save(request, glptRolePrivilege);
				j.setMsg("角色权限保存成功!");
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if(estr != null){
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
			}
			exceptionstr += StringUtil.isEmpty(estr)?"":estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			j.setSuccess(false);
			j.setMsg(exceptionstr);
			
		}
		return j;
	}
	
	/**
	 * 删除角色权限
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete(HttpServletRequest request, GlptRolePrivilegeEntity glptRolePrivilege){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("请选择需要删除的记录！");
		try {
			String ids = request.getParameter("ids");
			if(StringUtil.isNotEmpty(ids)){
				this.commonService.delete(request, GlptRolePrivilegeEntity.class, ids);
				j.setMsg("删除成功!");
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if(estr != null){
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
			}
			exceptionstr += StringUtil.isEmpty(estr)?"":estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			j.setSuccess(false);
			j.setMsg(exceptionstr);
		}
		return j;
	}

}
