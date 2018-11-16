package org.agcs.system.codegenerate.pojo;

/**
* @Title: CreateFileFlag.java 
* @Package org.braveframwork.codegenerate.pojo 
* @Description:  生成文件标识
* @author vivian
* @date 2016-1-21 下午5:25:56 
* @version V1.0
 */
public class CreateFileFlag {
	
	private boolean actionFlag;
	private boolean serviceIFlag;
	private boolean serviceImplFlag;
	private boolean daoIFlag;
	private boolean daoImplFlag;
	private boolean entityFlag;
	private boolean pageFlag;
	private boolean jspFlag;
	private String jspMode;
	
	public CreateFileFlag(){}

	public boolean isActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(boolean actionFlag) {
		this.actionFlag = actionFlag;
	}

	public boolean isServiceIFlag() {
		return serviceIFlag;
	}

	public void setServiceIFlag(boolean serviceIFlag) {
		this.serviceIFlag = serviceIFlag;
	}

	public boolean isServiceImplFlag() {
		return serviceImplFlag;
	}

	public void setServiceImplFlag(boolean serviceImplFlag) {
		this.serviceImplFlag = serviceImplFlag;
	}

	public boolean isEntityFlag() {
		return entityFlag;
	}

	public void setEntityFlag(boolean entityFlag) {
		this.entityFlag = entityFlag;
	}

	public boolean isPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}

	public boolean isJspFlag() {
		return jspFlag;
	}

	public void setJspFlag(boolean jspFlag) {
		this.jspFlag = jspFlag;
	}

	public String getJspMode() {
		return jspMode;
	}

	public void setJspMode(String jspMode) {
		this.jspMode = jspMode;
	}

	public boolean isDaoIFlag() {
		return daoIFlag;
	}

	public void setDaoIFlag(boolean daoIFlag) {
		this.daoIFlag = daoIFlag;
	}

	public boolean isDaoImplFlag() {
		return daoImplFlag;
	}

	public void setDaoImplFlag(boolean daoImplFlag) {
		this.daoImplFlag = daoImplFlag;
	}
	

}
