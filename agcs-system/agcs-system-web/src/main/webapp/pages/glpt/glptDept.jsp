<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>部门信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="glptDeptForm" class="wraper" action="glptDeptController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptDeptPage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">部门代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="deptCode" name="deptCode" datatype="*" nullmsg="请输入部门代号！" value="${glptDeptPage.deptCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">部门名称:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="deptName" name="deptName"  value="${glptDeptPage.deptName}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">上级部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="supDeptCode" name="supDeptCode"  value="${glptDeptPage.supDeptCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">状态:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="status" name="status"  value="${glptDeptPage.status}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">描述:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="remark" name="remark"  value="${glptDeptPage.remark}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrr" name="lrr"  value="${glptDeptPage.lrr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrxm" name="lrrxm"  value="${glptDeptPage.lrrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrbm" name="lrrbm"  value="${glptDeptPage.lrrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrsj" name="lrsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptDeptPage.lrsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作IP:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrip" name="lrip"  value="${glptDeptPage.lrip}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作MAC:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrmac" name="lrmac"  value="${glptDeptPage.lrmac}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">最后修改时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lastUpdate" name="lastUpdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptDeptPage.lastUpdate}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptDeptForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptDeptController.do?glptDept";
								}, 1000);
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