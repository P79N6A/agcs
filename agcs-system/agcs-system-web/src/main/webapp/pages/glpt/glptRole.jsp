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
		<form id="glptRoleForm" class="wraper" action="glptRoleController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptRolePage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">角色代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="roleCode" name="roleCode" datatype="*" nullmsg="请输入角色代号！" value="${glptRolePage.roleCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">角色名称:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="roleName" name="roleName"  value="${glptRolePage.roleName}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">状态(有效1、无效0):&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="state" name="state"  value="${glptRolePage.state}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">描述:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="remaek" name="remaek"  value="${glptRolePage.remaek}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrr" name="lrr"  value="${glptRolePage.lrr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrxm" name="lrrxm"  value="${glptRolePage.lrrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrsj" name="lrsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptRolePage.lrsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrbm" name="lrrbm"  value="${glptRolePage.lrrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作IP:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrip" name="lrip"  value="${glptRolePage.lrip}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作MAC:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrmac" name="lrmac"  value="${glptRolePage.lrmac}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">修改时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lastUpdate" name="lastUpdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptRolePage.lastUpdate}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptRoleForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptRoleController.do?glptRole";
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