package ${bussiPackage}.controller.${entityPackage};

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.services.${entityPackage}.${entityName}ServiceI;
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
* @Description:  ${funDescription}
* @author vivian
* @date ${create_time}
* @version V1.0
 */

@Controller
@RequestMapping("/${entityName?uncap_first}Controller")
public class ${entityName}Controller extends BaseController{
	
	@Autowired
	private ${entityName}ServiceI ${entityName?uncap_first}Service;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * ${funDescription}界面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "${entityName?uncap_first}")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("${entityPackage}/${entityName?uncap_first}List");
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPage")
	public void datagridPage(HttpServletRequest request, HttpServletResponse response, ${entityName}Entity ${entityName?uncap_first}, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(${entityName}Entity.class, dataGrid);
		CriteriaUtils.installHql(cq, ${entityName?uncap_first}, request.getParameterMap());
		this.commonService.datagridPage(cq, true);
		EasyuiUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 新增或修改
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "saveorupdate")
	public ModelAndView saveorupdate(HttpServletRequest request, ${entityName}Entity ${entityName?uncap_first}){
		if(StringUtil.isNotEmpty(${entityName?uncap_first}.getId())){
			String cztype = request.getParameter("cztype");
			//查询实体
			${entityName?uncap_first} = this.commonService.getEntity(${entityName}Entity.class, ${entityName?uncap_first}.getId());
			request.setAttribute("${entityName?uncap_first}Page", ${entityName?uncap_first});
			request.setAttribute("cztype", cztype);
		}
		return new ModelAndView("${entityPackage}/${entityName?uncap_first}");
	}
	
	/**
	 * 保存${funDescription}
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request, ${entityName}Entity ${entityName?uncap_first}){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			if(StringUtil.isNotEmpty(${entityName?uncap_first}.getId())){
				this.commonService.saveOrUpdate(request, ${entityName?uncap_first});
				j.setMsg("${funDescription}修改成功!");
			}else{
				this.commonService.save(request, ${entityName?uncap_first});
				j.setMsg("${funDescription}保存成功!");
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
	 * 删除${funDescription}
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete(HttpServletRequest request, ${entityName}Entity ${entityName?uncap_first}){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("请选择需要删除的记录！");
		try {
			String ids = request.getParameter("ids");
			if(StringUtil.isNotEmpty(ids)){
				this.commonService.delete(request, ${entityName}Entity.class, ids);
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
