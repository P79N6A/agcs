package org.agcs.system.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.agcs.system.core.themes.SysThemesEnum;
import org.agcs.system.core.themes.SysThemesUtil;
import org.agcs.system.core.util.StringUtil;

/**
* @Title: BaseTag.java 
* @Package org.braveframwork.tag 
* @Description:  基础js
* @author vivian
* @date 2016-1-14 下午2:16:22 
* @version V1.0
 */
public class BaseTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082931686604387792L;
	
	protected String type;  //要引入的js，多个用“,”分割
	protected String cssTheme;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCssTheme() {
		return cssTheme;
	}
	public void setCssTheme(String cssTheme) {
		this.cssTheme = cssTheme;
	}
	
	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			
			SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme((HttpServletRequest)this.pageContext.getRequest());
			String[] types = type.split(",");
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/frame.css\"/>");
			sb.append("<script type=\"text/javascript\" src=\"plugin/jquery/jquery-1.8.3.min.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"js/common/public.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"js/common/curdtools.js\"></script>");
			if(StringUtil.contains(types, "easyui")){
				sb.append(SysThemesUtil.getEasyuiThemes(sysThemesEnum));
				sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/jquery-easyui-1.4.3/themes/icon.css\"/>");
				sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/jquery-easyui-1.4.3/demo.css\"/>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/jquery-easyui-1.4.3/jquery.min.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/jquery-easyui-1.4.3/jquery.easyui.min.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js\"></script>");
			}
			if(StringUtil.contains(types, "cookie")){
				sb.append("<script type=\"text/javascript\" src=\"plugin/jquery/jquery.cookie.js\"></script>");
			}
			if(StringUtil.contains(types, "WdatePicker")){
				sb.append("<script type=\"text/javascript\" src=\"plugin/My97DatePicker/WdatePicker.js\"></script>");
			}
			if(StringUtil.contains(types, "Validform")){
				sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/Validform/css/style.css\"></link>");
				sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/Validform/css/tablefrom.css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/Validform/js/datatype_zh-cn.js\"></script>");
			}
			if(StringUtil.contains(types, "artDialog")){
				sb.append("<script type=\"text/javascript\" src=\"plugin/artDialog4.1.6/jquery.artDialog.source.js?skin=opera\"></script>");
				sb.append("<script type=\"text/javascript\" src=\"plugin/artDialog4.1.6/plugins/iframeTools.source.js\"></script>");
			}
			out.print(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	

}
