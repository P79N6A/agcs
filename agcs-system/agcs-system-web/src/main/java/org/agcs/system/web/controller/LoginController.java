package org.agcs.system.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.common.Logger;
import org.agcs.system.cache.redis.RedisClientTemplate;
import org.agcs.system.core.common.SysConst;
import org.agcs.system.core.themes.SysThemesEnum;
import org.agcs.system.core.util.DESCorder;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.hibernate.services.CommonServiceI;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.web.common.BaseController;
import org.agcs.system.web.entity.glpt.GlptActionLogEntity;
import org.agcs.system.web.entity.glpt.GlptRoleEntity;
import org.agcs.system.web.entity.glpt.GlptUserEntity;
import org.agcs.system.web.services.glpt.GlptRoleServiceI;
import org.agcs.system.web.services.glpt.GlptUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;
import org.agcs.system.core.themes.SysThemesUtil;

/**
* @Title: LoginController.java 
* @Package org.brave.web.controller 
* @Description:  登录控制器
* @author vivian
* @date 2016-1-18 下午4:01:34 
* @version V1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{
	
	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private GlptUserServiceI glptUserService;
	@Autowired
	private GlptRoleServiceI glptRoleService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 检查用户是否存在
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="checkuser")
	@ResponseBody
	public AjaxJson checkUser(HttpServletRequest request, GlptUserEntity user) throws Exception{
		AjaxJson j = new AjaxJson();
		HttpSession session = ContextHolderUtils.getSession();
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		String randCode = request.getParameter("round");
		if(StringUtil.isEmpty(randCode)){
			j.setSuccess(false);
			j.setMsg("验证码为空，请输入验证码!");
		}else if(!randCode.equalsIgnoreCase((String)session.getAttribute("rand"))){
			j.setSuccess(false);
			j.setMsg("验证码错误!");
		}else{
			GlptActionLogEntity logEntity = new GlptActionLogEntity(user, request);
			logEntity.setCzr(userCode);
			logEntity.setCznr("用户登录");
			logEntity.setOpname("用户登录");
			logEntity.setMethod("checkUser");
			//检查用户是否存在
			if(user == null){
				user = new GlptUserEntity();
			}
			user.setUserCode(userCode);
			user.setPassword(DESCorder.encodeMD5(userCode+password));
			GlptUserEntity euser = glptUserService.checkUserExit(user);
			if(euser != null){
				j.setSuccess(true);
				j.setMsg("登录成功!");
				logEntity.setOutresult("登录成功");
				session.setAttribute(SysConst.USERBEAN, euser);
			}else{
				j.setSuccess(false);
				j.setMsg("用户名或密码错误!");
				logEntity.setOutresult("用户名或密码错误");
			}
			this.commonService.addLog(logEntity);
		}
		return j;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="login")
	public String login(ModelMap modelMap,HttpServletRequest request) throws Exception{
		HttpSession session = ContextHolderUtils.getSession();
		GlptUserEntity user = (GlptUserEntity) session.getAttribute(SysConst.USERBEAN);
		if(user != null){
			GlptActionLogEntity logEntity = new GlptActionLogEntity(user, request);
			logEntity.setCznr("用户登录");
			logEntity.setOpname("用户登录");
			logEntity.setMethod("login");
			logEntity.setClassname(LoginController.class.getName());
			modelMap.put("userName", user.getUserName());
			//查询用户角色信息
			if(session.getAttribute(SysConst.USERROLE) == null){
				List<GlptRoleEntity> roleList = this.glptRoleService.getUserRole(request, user);
				if(roleList != null && !roleList.isEmpty()){
					session.setAttribute(SysConst.USERROLE, roleList.get(0));
				}else{
					GlptRoleEntity role = new GlptRoleEntity();
					role.setRoleName("该用户尚没有分配角色");
					session.setAttribute(SysConst.USERROLE, role);
				}
			}
			logEntity.setOutresult("登录成功");
			commonService.addLog(logEntity);
			SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
			
			//从后台代码获取国际化信息
            RequestContext requestContext = new RequestContext(request);
            request.setAttribute("title", requestContext.getMessage("title"));
			
			return sysThemesEnum.getIndexPath();
		}else{
			return "login/login";
		}
		
	}
	
	/**
	 * 退出
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="logout")
	public String logout(HttpServletRequest request) throws Exception{
		log.info("--------logout--------");
		HttpSession session = ContextHolderUtils.getSession();
		session.removeAttribute(SysConst.USERBEAN);
		session.removeAttribute(SysConst.USERMENU);
		session.removeAttribute(SysConst.USERROLE);
		return "login/login";
	}
	
}
