<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>操作日志</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/pages/context/context.jsp" %>
</head>
<body>
	<div class='div_center'>
		<form id="glptActionLogForm" class="wraper" action="glptActionLogController.do?save" method="post">
			<input id="btn_sub" name="btn_sub" type="hidden"/>
			<input id="id" name="id" type="hidden" value="${glptActionLogPage.id }">
			<table class='edittable' style='width: 90%;' cellpadding='0' cellspacing='0'>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作类名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="classname" name="classname"  value="${glptActionLogPage.classname}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">表名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="tablename" name="tablename"  value="${glptActionLogPage.tablename}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">输入参数:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="inputpagram" name="inputpagram"  value="${glptActionLogPage.inputpagram}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">输出结果:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="outresult" name="outresult"  value="${glptActionLogPage.outresult}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作方法名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="method" name="method"  value="${glptActionLogPage.method}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">请求地址:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="url" name="url"  value="${glptActionLogPage.url}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">开始时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="kssj" name="kssj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptActionLogPage.kssj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">结束时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="jssj" name="jssj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptActionLogPage.jssj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作事项:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="opname" name="opname"  value="${glptActionLogPage.opname}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">状态:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="status" name="status"  value="${glptActionLogPage.status}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">日志级别:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="levels" name="levels"  value="${glptActionLogPage.levels}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="czr" name="czr"  value="${glptActionLogPage.czr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人姓名:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="czrxm" name="czrxm"  value="${glptActionLogPage.czrxm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作人部门:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="czrbm" name="czrbm"  value="${glptActionLogPage.czrbm}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作时间:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="czsj" name="czsj"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${glptActionLogPage.czsj}' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"  ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作内容:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="cznr" name="cznr"  value="${glptActionLogPage.cznr}" ></input>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right" style="background-color: #F3F5F0;">操作IP:&nbsp;</td>
					<td align="left">
						&nbsp;<input type="text" class="inputs" id="czip" name="czip"  value="${glptActionLogPage.czip}" ></input>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function(){
					var cztype = '${cztype}';
					if(cztype == 'detail'){
						$(":input").attr("disabled","true");
					}
					$("#glptActionLogForm").Validform({
						tiptype:3,
						btnSubmit:"#btn_sub", 
						ajaxPost:true,
						callback: function(data){
							if(data.success){
								//tip(data.msg);
								setTimeout(function(){
									window.location.href = "glptActionLogController.do?glptActionLog";
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