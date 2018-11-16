package org.test.app.common;

import java.io.Serializable;

public class MemberVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5402260479673177799L;
	/**id*/
	private java.lang.String id;
	/**手机号*/
	private java.lang.String phone;
	/**密码*/
	private java.lang.String password;
	/**会员昵称*/
	private java.lang.String membername;
	/**会员头像*/
	private java.lang.String memberpicture;
	/**性别 0:男 1: 女*/
	private java.lang.Integer membersex;
	/**积分*/
	private java.lang.Integer score;
	/**积分规则*/
	private java.lang.String scoredesc;
	
	/**等级*/
	private java.lang.String gradeid;
/*	private MemberGradeEntity mGradeEntity;*/
	/**状态 0：正常 1：删除*/
	private java.lang.Integer memberstate;
	/**会员备注*/
	private java.lang.String memberContent;
	/**时间*/
	private String createTime;
	/**是否融资*/
	private String crest;
	/**商家认证状态*/
	private String sellerState;
	/**商家认证审核标识 0无需审核  其他需审核*/
	private String sellerFlag;
	private String token;
	
	private Integer account_id;
	private Integer storeId;
	private String storeName;
	/**主账户联系方式*/
	private String primaryPhone;
	
	/**纳税人识别号*/
	private java.lang.String taxpaySbnum;
	/**开户行名称*/
	private java.lang.String openBankName;
	/**开户行账户*/
	private java.lang.String openBankAccount;
	/**发票抬头*/
	private java.lang.String invoiceTitle;
	/**权限类型*/
	private String rightsType;
	
	public String getCrest() {
		return crest;
	}
	public void setCrest(String crest) {
		this.crest = crest;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setMemberContent(java.lang.String memberContent) {
		this.memberContent = memberContent;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getPhone() {
		return phone;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	public java.lang.String getPassword() {
		return password;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	public java.lang.String getMembername() {
		return membername;
	}
	public void setMembername(java.lang.String membername) {
		this.membername = membername;
	}
	public java.lang.String getMemberpicture() {
		return memberpicture;
	}
	public void setMemberpicture(java.lang.String memberpicture) {
		this.memberpicture = memberpicture;
	}
	public java.lang.Integer getMembersex() {
		return membersex;
	}
	public void setMembersex(java.lang.Integer membersex) {
		this.membersex = membersex;
	}
	public java.lang.Integer getScore() {
		return score;
	}
	public void setScore(java.lang.Integer score) {
		this.score = score;
	}
	public java.lang.String getScoredesc() {
		return scoredesc;
	}
	public void setScoredesc(java.lang.String scoredesc) {
		this.scoredesc = scoredesc;
	}
	public java.lang.String getGradeid() {
		return gradeid;
	}
	public void setGradeid(java.lang.String gradeid) {
		this.gradeid = gradeid;
	}
	public java.lang.Integer getMemberstate() {
		return memberstate;
	}
	public void setMemberstate(java.lang.Integer memberstate) {
		this.memberstate = memberstate;
	}
	public java.lang.String getMemberContent() {
		return memberContent;
	}
	public String getSellerState() {
		return sellerState;
	}
	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}
	public String getSellerFlag() {
		return sellerFlag;
	}
	public void setSellerFlag(String sellerFlag) {
		this.sellerFlag = sellerFlag;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**主账户联系方式*/
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	/**主账户联系方式*/
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	
	public java.lang.String getTaxpaySbnum() {
		return taxpaySbnum;
	}
	public void setTaxpaySbnum(java.lang.String taxpaySbnum) {
		this.taxpaySbnum = taxpaySbnum;
	}
	public java.lang.String getOpenBankName() {
		return openBankName;
	}
	public void setOpenBankName(java.lang.String openBankName) {
		this.openBankName = openBankName;
	}
	public java.lang.String getOpenBankAccount() {
		return openBankAccount;
	}
	public void setOpenBankAccount(java.lang.String openBankAccount) {
		this.openBankAccount = openBankAccount;
	}
	public java.lang.String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(java.lang.String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public java.lang.String getRightsType() {
		return rightsType;
	}
	public void setRightsType(java.lang.String rightsType) {
		this.rightsType = rightsType;
	}
	
}

