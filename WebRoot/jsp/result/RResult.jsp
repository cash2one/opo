<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <!--===========引入日期插件css================= START ====================-->
  <link rel="stylesheet" href="css/jquery-ui-1.9.2.custom.css" type="text/css">
 
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js" ></script> 


  <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>  
  <script type="text/javascript" src="<%=path%>/js/result/RResult.js"></script> 
  <!--===========引入表格插件js================= START ====================-->
  <script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.dataTables.js"></script> 
  
  <script type="text/javascript" src="<%=path%>/js/jquery-ui.js" ></script>  
  
  <!--===========引入日期插件js================= START ====================-->
  <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
  <script type="text/javascript" src="js/share.js"></script>
  
  
  <!--<script>
        $.extend( $.fn.dataTable.defaults, {
            "searching": false,
            "ordering": false,
            "bLengthChange": false,
            "bInfo": false
        } );
  </script>-->
  	
  </head>
  
 <body>
 <!--===========时间查询显示列表================= START ====================-->
 
 <input id="RResultDate" type="text" class="ui-datepicker-time" readonly value="" />
 <button style="font-family:Microsoft Yahei;"onclick="serchRResultList_click()">搜索消息</button>

 <div class="ui-datepicker-css">	
    <div class="ui-datepicker-quick">
        <p>快捷日期<a class="ui-close-date">X</a></p>
        <div>
            <input class="ui-date-quick-button" type="button" value="今天" alt="0"  name=""/>
            <input class="ui-date-quick-button" type="button" value="昨天" alt="-1" name=""/>
            <input class="ui-date-quick-button" type="button" value="7天内" alt="-6" name=""/>
            <input class="ui-date-quick-button" type="button" value="14天内" alt="-13" name=""/>
            <input class="ui-date-quick-button" type="button" value="30天内" alt="-29" name=""/>
            <input class="ui-date-quick-button" type="button" value="60天内" alt="-59" name=""/>
        </div>
    </div>
    <div class="ui-datepicker-choose">
        <p>自选日期</p>
        <div class="ui-datepicker-date">
            <input name="startDate" id="startDate" class="startDate" readonly value="2014/12/20" type="text">
           -
            <input name="endDate" id="endDate" class="endDate" readonly  value="2014/12/20" type="text" disabled onChange="datePickers()">
        
        </div>
    </div>
</div>
 
 
 <!--===========时间查询显示列表================= END ====================-->
 <!--===========加载敏感词================= START ====================-->
         <div id="rResult_management" style="font-family:Microsoft Yahei;">        
           <div style="padding-left:26px;" class="main">                   
           <div>                 
                 <!--===========存放模糊查询的div ====================-->    
            </div>         	
           <div id='tableRResult_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;" > 
            <table style="" id='tableRResult' class="display" cellspacing="0" width="98%">
             <thead> 
              <tr>            
               <td>关键词</td> 
               <td>网址</td> 
               <td>发布日期</td>    
               <td>抓取日期</td>        
              </tr> 
             </thead>          
            </table>
            
           </div>
    
            
          </div> 
            
         </div>
       
              
<!--===========加载敏感词================= END ====================-->


  <%-- //页面弹出消息框，相当于alert--%>
	<div id="dialog-message" title='提示信息'>
		<p>
		</p>
	</div>
	<%-- //页面弹出确认消息框，相当于confirm--%>
	<div id="dialog-confirm" title='提示确认信息框'>
		<p>
		</p>
	</div> 
</body>
</html>
