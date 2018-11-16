package org.agcs.system.web.controller.glpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.hibernate.util.EasyuiUtil;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.web.entity.glpt.GlptNodeEntity;
import org.agcs.system.web.services.glpt.GlptNodeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
* @Title: Controller
* @Description:  菜单管理
* @author vivian
* @date 2016-02-18
* @version V1.0
 */

@Controller
@RequestMapping("/glptNodeController")
public class GlptNodeController extends BaseController{
	
	@Autowired
	private GlptNodeServiceI glptNodeService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 菜单管理界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "glptNode")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("glpt/glptNodeList");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, GlptNodeEntity glptNode, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(GlptNodeEntity.class, dataGrid);
		CriteriaUtils.installHql(cq, glptNode, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, GlptNodeEntity glptNode){
		if(StringUtil.isNotEmpty(glptNode.getId())){
			String cztype = request.getParameter("cztype");
			//查询实体
			glptNode = this.commonService.getEntity(GlptNodeEntity.class, glptNode.getId());
			request.setAttribute("glptNodePage", glptNode);
			request.setAttribute("cztype", cztype);
		}
		return new ModelAndView("glpt/glptNode");
	}
	 
	 /**
	 * 保存
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request, GlptNodeEntity glptNode){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			if(StringUtil.isNotEmpty(glptNode.getId())){
				this.commonService.saveOrUpdate(request, glptNode);
				j.setMsg("菜单信息修改成功!");
			}else{
				this.commonService.save(request, glptNode);
				j.setMsg("菜单信息保存成功!");
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
	 * 删除
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete(HttpServletRequest request, GlptNodeEntity glptNode){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("请选择需要删除的记录！");
		try {
			String ids = request.getParameter("ids");
			if(StringUtil.isNotEmpty(ids)){
				this.commonService.delete(request, GlptNodeEntity.class, ids);
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
	
	 /**
	  * 用户菜单权限
	  * @return
	  */
	 @RequestMapping(params = "getNodePrivilege")
	 @ResponseBody
	public AjaxJson getNodePrivilege(HttpServletRequest request){
		 AjaxJson j = new AjaxJson();
		 HttpSession session = request.getSession();
		 try {
			 if(session.getAttribute(SysConst.USERMENU) == null){
				String nodeJson = this.glptNodeService.getNodePrivilege(request);
				session.setAttribute(SysConst.USERMENU, nodeJson);
				j.setMsg(nodeJson);
			 }else{
				String nodeJson = session.getAttribute(SysConst.USERMENU)+"";
				j.setMsg(nodeJson);
			 }
			 j.setSuccess(true);
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
