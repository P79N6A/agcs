package org.agcs.system.codegenerate.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.agcs.system.codegenerate.database.DatabaseTools;
import org.agcs.system.codegenerate.pojo.Column;
import org.agcs.system.codegenerate.pojo.CreateFileFlag;
import org.agcs.system.codegenerate.pojo.FltPo;
import org.agcs.system.codegenerate.util.CodeStringUtils;
import org.agcs.system.codegenerate.util.ResourceUtil;
import org.apache.log4j.Logger;

public class CodeGenerate implements ICallback{
	
	private static Logger log = Logger.getLogger(CodeGenerate.class);
	private static String entityPackage;
	private static String entityName;
	private static String tableName;
	private static String funDescription;
	private static String primarkKeyPolicy;
	private static String sequenceCode;
	private static String[] foreignKeys;
	private static CreateFileFlag createFileFlag = new CreateFileFlag();
	private List<Column> originalColumns = new ArrayList<Column>();
	private DatabaseTools databaseTools;
	public static int FIELD_ROW_NUM = 1;
	
	static{
		createFileFlag.setActionFlag(true);
		createFileFlag.setServiceIFlag(true);
		createFileFlag.setServiceImplFlag(true);
		createFileFlag.setEntityFlag(true);
		createFileFlag.setJspFlag(true);
		createFileFlag.setPageFlag(true);
		createFileFlag.setJspMode("01");
	}
	
	public CodeGenerate(){
		databaseTools = new DatabaseTools();
	}
	
	public CodeGenerate(String entityPackage, String entityName, String tableName, 
			String funDescription, String primarkKeyPolicy, String sequenceCode,
			CreateFileFlag createFileFlag, int fieldRowNum){
		CodeGenerate.entityPackage = entityPackage;
		CodeGenerate.entityName = entityName;
		CodeGenerate.tableName = tableName;
		CodeGenerate.funDescription = funDescription;
		CodeGenerate.primarkKeyPolicy = primarkKeyPolicy;
		CodeGenerate.sequenceCode = sequenceCode;
		CodeGenerate.createFileFlag = createFileFlag;
		FIELD_ROW_NUM = fieldRowNum;
	}
	 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map execute() {
		Map data = new HashMap();
		data.put("bussiPackage", ResourceUtil.BUSINESS_PACKAGE);
		data.put("entityPackage", entityPackage);
		data.put("entityName", entityName);
		data.put("tableName", tableName);
		data.put("funDescription", funDescription);
		data.put("foreignKeys", foreignKeys);
		data.put("create_time", CodeStringUtils.formatDate(new Date()));
		data.put(FltPo.PRIMARY_KEY_POLICY, primarkKeyPolicy);
		data.put(FltPo.SEQUENCE_CODE, sequenceCode);
		data.put(FltPo.TABLE_ID, ResourceUtil.TABLE_PRIMARY_KEY);
		//查询表所有字段
		try {
			originalColumns =  databaseTools.getOriginalTableColumn(tableName);
			data.put("originalColumns", originalColumns);
			for(Iterator iterator = originalColumns.iterator(); iterator.hasNext();){
				Column c = (Column)iterator.next();
				if(c.getFiledName().toLowerCase().equals(ResourceUtil.TABLE_PRIMARY_KEY.toLowerCase())){
					data.put("primary_key_type", c.getFiledType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		//long serialVersionUID = (new SecureRandom()).nextLong() + System.currentTimeMillis();;
        //data.put("serialVersionUID", String.valueOf(serialVersionUID));
		return data;
	}
	
	public void generateToFile(){
		log.info("Code Generation [\u5355\u8868\u6A21\u578B:"+tableName+"] \u751F\u6210\u4E2D\u3002\u3002\u3002");
		CodeFactory codeFactory = new CodeFactory();
		codeFactory.setCallback(new CodeGenerate());
		if(createFileFlag.isJspFlag()){
			if("01".equals(createFileFlag.getJspMode())){
				codeFactory.invoke("jspTableTemplate.ftl", "jsp");
			}else{
				codeFactory.invoke("jspDivTemplate.ftl", "jsp");
			}
			codeFactory.invoke("jspListTemplate.ftl", "jspList");
		}
		if(createFileFlag.isEntityFlag()){
			codeFactory.invoke("entityTemplate.ftl", "entity");
		}
		
		if(createFileFlag.isDaoIFlag()){
			codeFactory.invoke("daoITemplate.ftl", "dao");
		}
		if(createFileFlag.isDaoImplFlag()){
			codeFactory.invoke("daoImplTemplate.ftl", "daoImpl");
		}
		
		if(createFileFlag.isServiceIFlag()){
			codeFactory.invoke("serviceITemplate.ftl", "service");
		}
		if(createFileFlag.isServiceImplFlag()){
			codeFactory.invoke("serviceImplTemplate.ftl", "serviceImpl");
		}
		if(createFileFlag.isActionFlag()){
			codeFactory.invoke("controllerTemplate.ftl", "controller");
		}
		log.info("Code Generation [\u5355\u8868\u6A21\u578B:"+tableName+"] \u751F\u6210\u5B8C\u6210\u3002\u3002\u3002");
	}

}
