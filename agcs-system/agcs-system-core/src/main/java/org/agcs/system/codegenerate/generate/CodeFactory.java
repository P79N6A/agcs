package org.agcs.system.codegenerate.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.agcs.system.codegenerate.util.CodeStringUtils;
import org.agcs.system.codegenerate.util.ResourceUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CodeFactory extends BaseCodeFactory{
	
	private ICallback callback;
	
	@SuppressWarnings("rawtypes")
	public void invoke(String templateFileName, String type){
		Map data = new HashMap();
		data = callback.execute();
		generateFile(templateFileName, type, data);
	}
	
	public void generateFile(String templateFileName, String type, Map data){
		try {
			String entityPackage = data.get("entityPackage").toString();
			String entityName = data.get("entityName").toString();
			String filePath = getCodePath(type, entityPackage, entityName);
			//获取文件路劲
			String fileDir = StringUtils.substringBeforeLast(filePath, "/");
			//创建文件路劲
			FileUtils.forceMkdir(new File(fileDir+"/"));
			//创建模板
			Template template = getConfiguration().getTemplate(templateFileName);
			Writer out = new OutputStreamWriter(new FileOutputStream(filePath), ResourceUtil.SYSTEM_ENCODING);
			template.process(data, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e){
			e.printStackTrace();
		}
		
	}
	
	public String getCodePath(String type, String entityPackage, String entityName){
		StringBuilder str = new StringBuilder();
		String path = getProjectPath();
		if(StringUtils.isNotBlank(type)){
			String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
			str.append(path);
			if(("jsp".equals(type)) || ("jspList".equals(type))){
				str.append(ResourceUtil.JSPPATH);
			}else{
				str.append(ResourceUtil.CODEPATH);
			}
			if("Action".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("action/"+entityPackage+"/"));
			}else if("ServiceImpl".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("services/"+entityPackage+"/impl/"));
			}else if("ServiceI".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("services/"+entityPackage+"/"));
			}else if("DaoI".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("dao/"+entityPackage+"/"));
			}else if("DaoImpl".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase("dao/"+entityPackage+"/impl/"));
			}else if(!"List".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase(codeType+"/"+entityPackage+"/"));
			}else if("List".equalsIgnoreCase(codeType)){
				str.append(StringUtils.lowerCase(entityPackage+"/"));
			}
		    if("jsp".equals(type) || "jspList".equals(type)){
				String jspName = StringUtils.capitalize(entityName);//首字母大写
				str.append(CodeStringUtils.getInitialSmall(jspName));
				str.append(codeType);
				str.append(".jsp");
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
	
	public static String getProjectPath(){
		String path = System.getProperty("user.dir").replace("\\", "/")+"/";
		return path;
	}
	
	public ICallback getCallback() {
		return callback;
	}

	public void setCallback(ICallback callback) {
		this.callback = callback;
	}
	
	public static enum CodeType{
		dao("DaoI"),
		daoImpl("DaoImpl"),
		serviceImpl("ServiceImpl"), 
	    service("ServiceI"), 
	    controller("Controller"), 
	    entity("Entity"), 
	    jsp(""), 
	    jspList("List");
		
		private String type;
		
		private CodeType(String type){
			this.type = type;
		}
		
		public String getValue(){
			return this.type;
		}
	}

	public static void main(String[] args) {
		System.out.println(CodeFactory.class);
		System.out.println(getProjectPath());
		System.out.println(new CodeFactory().getCodePath("entity", "test", "Test"));
		System.out.println("src.main.java".replace(".", "/"));
		String str1 = "I am a am student, and I like sport";
		String str2 = str1.replace("am","was");
		System.out.println(str2);
	}

}
