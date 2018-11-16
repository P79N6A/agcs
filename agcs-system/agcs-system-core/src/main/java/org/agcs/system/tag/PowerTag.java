package org.agcs.system.tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
* @Title: PowerTag.java 
* @Package org.braveframwork.tag 
* @Description  权限：
* 菜单权限
* 按钮权限
* 字段查看权限
* 区域显示权限
* @author wy
* @date 2016-1-14 下午2:16:22 
* @version V1.0
 */
public class PowerTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6658626081380260559L;
	
	/**
	 * 权限代号
	 */
	protected String optCode;

	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	
	/**
	 * 判断是否有权限
	 * @return
	 */
	public static boolean getPower(){
		
		return false;
	}

	
}
