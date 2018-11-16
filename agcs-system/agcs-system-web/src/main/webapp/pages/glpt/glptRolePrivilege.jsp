<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>角色权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="glptRolePrivilegeForm" class="wraper" action="glptRolePrivilegeController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptRolePrivilegePage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">角色代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="roleCode" name="roleCode"  value="${glptRolePrivilegePage.roleCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="nodeCode" name="nodeCode"  value="${glptRolePrivilegePage.nodeCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">描述:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="remark" name="remark"  value="${glptRolePrivilegePage.remark}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrr" name="lrr"  value="${glptRolePrivilegePage.lrr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrxm" name="lrrxm"  value="${glptRolePrivilegePage.lrrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrsj" name="lrsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptRolePrivilegePage.lrsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrbm" name="lrrbm"  value="${glptRolePrivilegePage.lrrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入ip:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrip" name="lrip"  value="${glptRolePrivilegePage.lrip}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">录入mac:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrmac" name="lrmac"  value="${glptRolePrivilegePage.lrmac}" ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptRolePrivilegeForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success == true){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptRolePrivilegeController.do?glptRolePrivilege";
								}, 500);
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