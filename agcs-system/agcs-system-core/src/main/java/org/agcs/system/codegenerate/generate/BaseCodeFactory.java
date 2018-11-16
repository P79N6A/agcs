package org.agcs.system.codegenerate.generate;

import java.util.Locale;

import org.agcs.system.codegenerate.util.CodeStringUtils;
import org.agcs.system.codegenerate.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import freemarker.template.Configuration;

/**
* @Title: BaseCodeFactory.java 
* @Package org.braveframwork.codegenerate.generate 
* @Description:  代码生成基础类
* @author vivian
* @date 2016-1-22 下午6:35:26 
* @version V1.0
 */
public class BaseCodeFactory {
	
	protected String packageStyle;
	
	public Configuration getConfiguration(){
		Configuration cg = new Configuration();
		cg.setClassForTemplateLoading(getClass(), ResourceUtil.FREEMARKER_CLASSPATH);
		cg.setLocale(Locale.CHINA);
		cg.setDefaultEncoding("utf-8");
		return cg;
	}
	
	public  String getCodePathServiceStyle(String path, String type, String entityPackage, String entityName){
		StringBuilder str = new StringBuilder();
		if(StringUtils.isNotBlank(type)){
			String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
			str.append(path);
			if(("jsp".equals(type)) || ("jspList".equals(type)) || ("js".equals(type)) || ("jsList".equals(type))
					||("jsp_add".equals(type)) || ("jsp_update".equals(type))){
				str.append(ResourceUtil.JSPPATH);
			}else{
				str.append(ResourceUtil.CODEPATH);
			}
			str.append(StringUtils.lowerCase(entityPackage));
			str.append("/");
			if("Action".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("action"));
			}else if("ServiceImpl".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service/impl"));
			}else if("ServiceI".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service"));
			}else if(!"List".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase(codeType));
			}
			str.append("/");
			if("jsp".equals(type) || "jspList".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".jsp");
			}else if("jsp_add".equals(type) || "jspList_add".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append("-add.jsp");
			}else if("jsp_update".equals(type) || "jspList_update".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append("-update.jsp");
			}else if("js".equals(type) || "jsList".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".js");
			}else{
				str.append(StringUtils.capitalize(entityName));
                str.append(codeType);
                str.append(".java");
			}
			
		}else{
			throw new IllegalArgumentException("type is null");
		}
		
		return str.toString();
	}
	
	public String getCodePathProjectStyle(String path, String type, String entityPackage, String entityName){
		StringBuilder str = new StringBuilder();
		if(StringUtils.isNotBlank(type)){
			String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
			str.append(path);
			if(("jsp".equals(type)) || ("jspList".equals(type)) || ("js".equals(type)) || ("jsList".equals(type))
					||("jsp_add".equals(type)) || ("jsp_update".equals(type))){
				str.append(ResourceUtil.JSPPATH);
			}else{
				str.append(ResourceUtil.CODEPATH);
			}
			str.append(StringUtils.lowerCase(entityPackage));
			str.append("/");
			if("Action".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("action"));
			}else if("ServiceImpl".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service/impl"));
			}else if("ServiceI".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("service"));
			}else if(!"List".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase(codeType));
			}
			str.append("/");
			if("jsp".equals(type) || "jspList".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".jsp");
			}else if("jsp_add".equals(type) || "jspList_add".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append("-add.jsp");
			}else if("jsp_update".equals(type) || "jspList_update".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append("-update.jsp");
			}else if("js".equals(type) || "jsList".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".js");
			}else{
				str.append(StringUtils.capitalize(entityName));
                str.append(codeType);
                str.append(".java");
			}
			
		}else{
			throw new IllegalArgumentException("type is null");
		}
		
		return str.toString();
	}

	public String getPackageStyle() {
		return packageStyle;
	}

	public void setPackageStyle(String packageStyle) {
		this.packageStyle = packageStyle;
	}
	
	public static enum CodeType{
		serviceImpl("ServiceImpl"), 
	    dao("Dao"), 
	    service("ServiceI"), 
	    controller("Controller"), 
	    page("Page"), 
	    entity("Entity"), 
	    jsp(""), 
	    jsp_add(""), 
	    jsp_update(""), 
	    js(""), 
	    jsList("List"), 
	    jspList("List"), 
	    jspSubList("SubList");
		
		private String type;
		
		private CodeType(String type){
			this.type = type;
		}
		
		public String getValue(){
			return this.type;
		}
	}
	
	public static void main(String[] args) {
		String codeType = ((CodeType)Enum.valueOf(CodeType.class, "service")).getValue();
		System.out.println(codeType);
		System.out.println(String.valueOf(codeType));
		System.out.println(StringUtils.capitalize("aaa"));
	}

}
