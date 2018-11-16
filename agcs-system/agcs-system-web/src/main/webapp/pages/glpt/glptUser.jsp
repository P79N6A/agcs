<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>角色信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="glptUserForm" class="wraper" action="glptUserController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptUserPage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">用户代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="userCode" name="userCode" datatype="*" nullmsg="请输入用户代号！" value="${glptUserPage.userCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">用户名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="userName" name="userName"  value="${glptUserPage.userName}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">用户密码:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="password" name="password"  value="${glptUserPage.password}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">真实姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="realName" name="realName"  value="${glptUserPage.realName}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">性别:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="sex" name="sex"  value="${glptUserPage.sex}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">年龄:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="age" name="age"  value="${glptUserPage.age}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">生日:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="birthday" name="birthday"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptUserPage.birthday}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">手机号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="mobilePhone" name="mobilePhone"  value="${glptUserPage.mobilePhone}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">电话:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="telphone" name="telphone"  value="${glptUserPage.telphone}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">身份证明号码（身份证、军官证、护照等等）:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="caedId" name="caedId"  value="${glptUserPage.caedId}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">地址:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="address" name="address"  value="${glptUserPage.address}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">状态:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="status" name="status"  value="${glptUserPage.status}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">在线状态（在线，隐身、忙碌、离开、离线）:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="inline" name="inline"  value="${glptUserPage.inline}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">学历:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="education" name="education"  value="${glptUserPage.education}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">邮箱:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="email" name="email"  value="${glptUserPage.email}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">QQ号码:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="qq" name="qq"  value="${glptUserPage.qq}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">描述:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="remaek" name="remaek"  value="${glptUserPage.remaek}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">签名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="signature" name="signature"  value="${glptUserPage.signature}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrr" name="lrr"  value="${glptUserPage.lrr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrxm" name="lrrxm"  value="${glptUserPage.lrrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrsj" name="lrsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptUserPage.lrsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrbm" name="lrrbm"  value="${glptUserPage.lrrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作IP:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrip" name="lrip"  value="${glptUserPage.lrip}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作MAC:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrmac" name="lrmac"  value="${glptUserPage.lrmac}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">修改时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lastUpdate" name="lastUpdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptUserPage.lastUpdate}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptUserForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptUserController.do?user";
								}, 2000);
							}else{
								if(data.msg == null || data.msg == "" || data.msg == undefined){
									tip(data.status+":"+data.statusText, 250, 100, 0);
								}else{
									tip(data.msg, 250, 100, 3000);
								}
								
							}
						}
					});
				});
			</script>
		</form>
	</div>
</body>
</html>