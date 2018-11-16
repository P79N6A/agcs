var iframe;// iframe操作对象

function add(title, icon, width, height, url){
	openDialog(title, icon, width, height, url);
}

function del(url, id){
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length == 0) {
		tip('请选择删除项目');
		return;
	}
	var ids = [];
	$.messager.confirm('删除', '确定要删除此条记录吗?', function(r){
		if (r){
			for ( var i = 0; i < rowsData.length; i++) {
				ids.push(rowsData[i].id);
			}
			$.ajax({
				cache: false,
				async: false,
				type:'post',
				url: url,
				data : {
					ids : ids.join(',')
				},
				success: function(data){
					var d = $.parseJSON(data);
					if(d.success){
						tip(d.msg);
						$('#'+id).datagrid('reload');
					}
				},
				error: function(){
					$.messager.alert('删除','删除异常,请稍后再试!','error');
				}
			});
		}
	});
}

function update(title, icon, width, height, url, id){
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length == 0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length > 1) {
		tip('请选择一条记录再编辑');
		return;
	}
	url = url + "&id="+rowsData[0].id;
	openDialog(title, icon, width, height, url);
}

$(function(){
	if(location.href.indexOf("cztype=detail")!=-1){
		$(":input").attr("disabled","true");
		//$(":input").attr("style","border:0;border-bottom:1 solid black;background:white;");
	}
});

function detail(title, icon, width, height, url, id){
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length == 0) {
		tip('请选择查看项目');
		return;
	}
	if (rowsData.length > 1) {
		tip('请选择一条记录再查看');
		return;
	}
	//模式窗口打开一个页面
	width = width?width:700;
	height = height?height:400;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;
	}
	url = url + "&cztype=detail&id="+rowsData[0].id;
	var html = "<div id='dialog'></div>";
	$(html).dialog({
	    title: title,
	    iconCls: icon,
	    width: width,
	    height: height,
	    closed: false,
	    cache: false,
	    href: url,
	    modal: true,
	    buttons: [{
            text: '关&nbsp;&nbsp;闭',
            iconCls: 'icon-undo',
            handler: function () {
            	closeDialog();
            }
        }],
	    onClose: function(){
	    	$(this).dialog('destroy'); //销毁
	    }
	});
}

function openDialog(title, icon, width, height, url){
	//模式窗口打开一个页面
	width = width?width:700;
	height = height?height:400;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;
	}
	var html = "<div id='dialog'></div>";
	$(html).dialog({
	    title: title,
	    iconCls: icon,
	    width: width,
	    height: height,
	    closed: false,
	    cache: false,
	    href: url,
	    modal: true,
	    buttons: [{
            text: '提&nbsp;&nbsp;交',
            id: 'Edit',
            iconCls: 'icon-save',
            handler: function () {
            	//iframe = this.iframe.contentWindow;
            	saveObj();
            }
        }, {
            text: '关&nbsp;&nbsp;闭',
            iconCls: 'icon-undo',
            handler: function () {
            	closeDialog();
            }
        }],
	    onClose: function(){
	    	$(this).dialog('destroy'); //销毁
	    }
	});
	//调用父页面方法创建一个tab
	//parent.treeClick("新增", 'pages/glpt/addNode.jsp', true, "icon-add");
}

/**
 * 执行保存
 */
function saveObj() {
	$('#btn_sub').click();
}

/**
 * 提示信息
 */
function tip(msg, width, height, timeout) {
	//模式窗口打开一个页面
	width = width?width:250;
	height = height?height:100;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;
	}
	timeout = timeout?timeout:0;
	$.messager.show({
		title:'提示信息',
		msg:msg,
		showType:'fade',
		width:width,
		height: height,
		timeout: timeout,
		style:{
			right:'',
			bottom:''
		}
	});
}

function closeDialog(){
	$('#dialog').dialog('close');
}