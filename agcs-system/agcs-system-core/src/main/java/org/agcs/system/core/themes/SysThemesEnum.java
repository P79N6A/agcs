package org.agcs.system.core.themes;

import org.agcs.system.core.util.StringUtil;


/**
 * 
* @Title: SysThemesEnum.java 
* @Package org.vivian.core.systhemes 
* @Description:  主题与样式
* @author vivian
* @date 2016-1-11 上午11:50:11 
* @version V1.0
 */
public enum SysThemesEnum {
	DEFAULT_STYLE("default","main/frame","default", "经典风格"),
	SHORTCUT_STYLE("default","main/frame","black", "黑色"),
	SLIDING_STYLE("default","main/frame","bootstrap", "bootstrap"),
	ACE_STYLE("default","main/frame","gray", "灰色");
	
//	DEFAULT_STYLE("default","main/main","default", "经典风格"),
//	SHORTCUT_STYLE("shortcut","main/shortcut_main","default", "ShortCut风格"),
//	SLIDING_STYLE("sliding","main/sliding_main","default", "Sliding云桌面"),
//	ACE_STYLE("ace","main/ace_main","metro", "ACE平面风格");
	
	
	/**
	 * 风格
	 */
	private String style;
	/**
	 * 样式
	 */
	private String themes;
	
	/**
	 * 首页路劲
	 */
	private String indexPath;
	
	/**
	 * 描述
	 */
	private String description;
	
	private SysThemesEnum(String style, String indexpath, String themes, String description){
		this.style = style;
		this.themes = themes;
		this.indexPath = indexpath;
		this.description = description;
	}
	
	public static SysThemesEnum toEnum(String style){
		if(StringUtil.isEmpty(style)){
			return SHORTCUT_STYLE;
		}
		for(SysThemesEnum item : SysThemesEnum.values()){
			if(item.getStyle().equals(style)){
				return item;
			}
		}
		return SHORTCUT_STYLE;
	}
	
	public static SysThemesEnum toEnum(String style, String themes){
		if(StringUtil.isEmpty(themes)){
			return DEFAULT_STYLE;
		}
		for(SysThemesEnum item : SysThemesEnum.values()){
			if(item.getThemes().equals(themes)){
				return item;
			}
		}
		return DEFAULT_STYLE;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public String getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString(){
		return "{style: "+style+", themes: "+themes+", indexPath: "+indexPath+", description: "+description+"}";
	}
	
	public static void main(String[] args) {
		System.out.println(SysThemesEnum.SHORTCUT_STYLE.toString());
	}

}
