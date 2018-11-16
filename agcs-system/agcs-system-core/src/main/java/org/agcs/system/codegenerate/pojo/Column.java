package org.agcs.system.codegenerate.pojo;

/**
* @Title: Column.java 
* @Package org.braveframwork.codegenerate.pojo 
* @Description:  表单列对象
* @author vivian
* @date 2016-1-23 下午1:38:50 
* @version V1.0
 */
public class Column {
	
	private String filedDbName;
	private String filedName;
	private String filedComment;
	private String filedType;
	private String charmaxLength;
	private String precision;
	private String scale;
	private String nullable;
	
	private String classType;
	private String classType_row;
	private String optionType;
	
	public Column(){
		
	}
	
	public String getFiledDbName() {
		return filedDbName;
	}
	public void setFiledDbName(String filedDbName) {
		this.filedDbName = filedDbName;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public String getFiledComment() {
		return filedComment;
	}
	public void setFiledComment(String filedComment) {
		this.filedComment = filedComment;
	}
	public String getFiledType() {
		return filedType;
	}
	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}
	public String getCharmaxLength() {
		return charmaxLength;
	}
	public void setCharmaxLength(String charmaxLength) {
		this.charmaxLength = charmaxLength;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getClassType_row() {
		return classType_row;
	}
	public void setClassType_row(String classType_row) {
		this.classType_row = classType_row;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	
	

}
