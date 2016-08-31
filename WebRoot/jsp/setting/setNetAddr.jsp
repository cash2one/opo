<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery.dataTables.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery-ui.css">


<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>


<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

<script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/setting/setNetAddr.js"></script>
<script>
	$.extend($.fn.dataTable.defaults, {
		"searching" : false,
		"ordering" : false,
		"bLengthChange" : false,
		"bInfo" : false
	});
</script>

</head>

<body>
	<!---------设置网址  start--------->
	<div id="netAddr_management" style="font-family:Microsoft Yahei;">
		<div style="padding-left:26px;" class="main">
			<div>
				<input type="text" id='netAddr_serchValue' maxlength="30" placeholder='请输入关键字'>
				<button style="font-family:Microsoft Yahei;" onclick="serchNetAddr()">搜索</button>
				<input type="text" id='url_netAddr' maxlength="30">
				<button style="font-family:Microsoft Yahei   ;" onclick="addNetAddrInfo()">添加</button>
				<input type="text" id='url_netAddrModify' maxlength="30"> <input type="hidden" id='cindex_netAddrModify' maxlength="30">
				<button style="font-family:Microsoft Yahei   ;" onclick="saveNetAddrModifyInfo()">修改</button>
			</div>
			<div id='tableNetAddr_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;">
				<table style="" id='tableNetAddr' class="display" cellspacing="0" width="98%">
					<thead>
						<tr>
							<td>关键词</td>
							<td>时间</td>
							<td>操作</td>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!---------设置网址  end--------->

	<%-- //页面弹出消息框，相当于alert--%>
	<div id="dialog-message" title='提示信息'>
		<p></p>
	</div>
	<%-- //页面弹出确认消息框，相当于confirm--%>
	<div id="dialog-confirm" title='提示确认信息框'>
		<p></p>
	</div>
</body>
</html>
