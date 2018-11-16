package org.agcs.system.web.controller.glpt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.hibernate.util.EasyuiUtil;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.agcs.system.web.services.glpt.GlptUserServiceI;
import org.apache.log4j.Logger;

/**
* @Title: Controller
* @Description:  用户信息列表
* @author vivian
* @date 2016-02-01
* @version V1.0
 */

@Controller
@RequestMapping("/glptUserController")
public class GlptUserController extends BaseController{
	
	private static final Logger log = Logger.getLogger(GlptUserController.class);
	
	@Autowired
	private GlptUserServiceI glptUserService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 保存系统主题
	 * @return
	 */
	@RequestMapping(params = "saveThemes")
	@ResponseBody
	public AjaxJson saveThemes(HttpServletRequest request, HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		j.setSuccess(Boolean.FALSE);
		j.setMsg("样式修改失败!");
		String themes = request.getParameter("themes");
		if(StringUtil.isNotEmpty(themes)){
			Cookie cookie = new Cookie(SysConst.SYSTHEMES, themes);
			cookie.setMaxAge(3600*24*30);//设置有效期一个月
			response.addCookie(cookie);
			j.setSuccess(Boolean.TRUE);
			j.setMsg("样式修改成功,请刷新页面!");
		}
		return j;
	}
	
	/**
	 * 用户管理界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "user")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("glpt/glptUserList");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param glptUser
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, GlptUserEntity glptUser, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(GlptUserEntity.class, dataGrid);
		CriteriaUtils.installHql(cq, glptUser, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @param glptUser
	 * @return
	 */
	@RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, GlptUserEntity glptUser){
		if(StringUtil.isNotEmpty(glptUser.getId())){
			//查询实体
			glptUser = this.commonService.getEntity(GlptUserEntity.class, glptUser.getId());
			request.setAttribute("glptUserPage", glptUser);
		}
		return new ModelAndView("glpt/glptUser");
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request, GlptUserEntity glptUser){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			if(StringUtil.isNotEmpty(glptUser.getId())){
				this.commonService.saveOrUpdate(request, glptUser);
				j.setMsg("用户信息修改成功!");
			}else{
				this.commonService.save(request, glptUser);
				j.setMsg("用户信息保存成功!");
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
	public AjaxJson delete(HttpServletRequest request, GlptUserEntity glptUser){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("请选择需要删除的记录！");
		try {
			String ids = request.getParameter("ids");
			if(StringUtil.isNotEmpty(ids)){
				this.commonService.delete(request, GlptUserEntity.class, ids);
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
