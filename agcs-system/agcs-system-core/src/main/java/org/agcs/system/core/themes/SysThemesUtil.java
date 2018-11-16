package org.agcs.system.core.themes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.util.StringUtil;

/**
* @Title: SysThemesUtil.java 
* @Package org.braveframwork.core.systhemes 
* @Description:  系统样式获取类
* @author vivian
* @date 2016-1-14 下午2:35:43 
* @version V1.0
 */
public class SysThemesUtil {
	
	/**
	 * 获取系统风格
	 * @return
	 */
	public static SysThemesEnum getSysTheme(HttpServletRequest request){
//		String indexStyle = "";
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null){
//			for(Cookie cookie : cookies){
//				if(cookie == null || StringUtil.isEmpty(cookie.getName())){
//					continue;
//				}
//				if(cookie.getName().equalsIgnoreCase("INDEXSTYLE")){
//					indexStyle = cookie.getValue();
//					break;
//				}
//			}
//		}
		String themes = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie == null || StringUtil.isEmpty(cookie.getName())){
					continue;
				}
				if(cookie.getName().equalsIgnoreCase("SYSTHEMES")){
					themes = cookie.getValue();
					break;
				}
			}
		}
		return SysThemesEnum.toEnum(null, themes);
	}
	
	/**
	 * 获取easyui 主题
	 * @return
	 */
	public static String getEasyuiThemes(SysThemesEnum sysEnum){
		String link = "<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/jquery-easyui-1.4.3/themes/"+sysEnum.getThemes()+"/easyui.css\">";
		return link;
	}

}
