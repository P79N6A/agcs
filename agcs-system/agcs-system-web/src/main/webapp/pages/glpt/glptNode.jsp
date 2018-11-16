<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>菜单信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="glptNodeForm" class="wraper" action="glptNodeController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptNodePage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点代号:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="nodeCode" name="nodeCode" datatype="*" nullmsg="请输入节点代号！" value="${glptNodePage.nodeCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点名称:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="node_name" name="node_name"  value="${glptNodePage.node_name}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点url:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="nodeUrl" name="nodeUrl"  value="${glptNodePage.nodeUrl}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点图标:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="nodeIcon" name="nodeIcon"  value="${glptNodePage.nodeIcon}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">上级节点:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="supNodeCode" name="supNodeCode"  value="${glptNodePage.supNodeCode}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">状态:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="status" name="status"  value="${glptNodePage.status}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">节点类型:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="type" name="type"  value="${glptNodePage.type}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">所属系统:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="system" name="system"  value="${glptNodePage.system}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">排序:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="sort" name="sort"  value="${glptNodePage.sort}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">描述:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="remark" name="remark"  value="${glptNodePage.remark}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrr" name="lrr"  value="${glptNodePage.lrr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrxm" name="lrrxm"  value="${glptNodePage.lrrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrrbm" name="lrrbm"  value="${glptNodePage.lrrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrsj" name="lrsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptNodePage.lrsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作IP:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrip" name="lrip"  value="${glptNodePage.lrip}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作MAC:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lrmac" name="lrmac"  value="${glptNodePage.lrmac}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">最后修改时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="lastUpdate" name="lastUpdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptNodePage.lastUpdate}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptNodeForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							debugger;
							if(data.success == true){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptNodeController.do?glptNode";
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