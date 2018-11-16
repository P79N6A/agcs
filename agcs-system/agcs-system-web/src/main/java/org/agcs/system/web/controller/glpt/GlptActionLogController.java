package org.agcs.system.web.controller.glpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.qbc.CriteriaQuery;
import org.agcs.system.hibernate.qbc.CriteriaUtils;
import org.agcs.system.hibernate.qbc.DataGrid;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.hibernate.util.EasyuiUtil;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.web.entity.glpt.GlptActionLogEntity;
import org.agcs.system.web.services.glpt.GlptActionLogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
* @Title: Controller
* @Description:  操作日志
* @author vivian
* @date 2016-02-20
* @version V1.0
 */

@Controller
@RequestMapping("/glptActionLogController")
public class GlptActionLogController extends BaseController{
	
	@Autowired
	private GlptActionLogServiceI glptActionLogService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 操作日志界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "glptActionLog")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("glpt/glptActionLogList");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, GlptActionLogEntity glptActionLog, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(GlptActionLogEntity.class, dataGrid);
		CriteriaUtils.installHql(cq, glptActionLog, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, GlptActionLogEntity glptActionLog){
		if(StringUtil.isNotEmpty(glptActionLog.getId())){
			String cztype = request.getParameter("cztype");
			//查询实体
			glptActionLog = this.commonService.getEntity(GlptActionLogEntity.class, glptActionLog.getId());
			request.setAttribute("glptActionLogPage", glptActionLog);
			request.setAttribute("cztype", cztype);
		}
		return new ModelAndView("glpt/glptActionLog");
	}
	
	/**
	 * 保存操作日志
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request, GlptActionLogEntity glptActionLog){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			if(StringUtil.isNotEmpty(glptActionLog.getId())){
				this.commonService.saveOrUpdate(request, glptActionLog);
				j.setMsg("操作日志修改成功!");
			}else{
				this.commonService.save(request, glptActionLog);
				j.setMsg("操作日志保存成功!");
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
	 * 删除操作日志
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete(HttpServletRequest request, GlptActionLogEntity glptActionLog){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("请选择需要删除的记录！");
		try {
			String ids = request.getParameter("ids");
			if(StringUtil.isNotEmpty(ids)){
				this.commonService.delete(request, GlptActionLogEntity.class, ids);
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
