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
  
 
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js" ></script> 


  <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>  

  <script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.dataTables.js"></script> 
  
  <script type="text/javascript" src="<%=path%>/js/jquery-ui.js" ></script>  
  <script type="text/javascript" src="<%=path%>/js/setting/setKeywordList.js"></script> 
   <script>
        $.extend( $.fn.dataTable.defaults, {
            "searching": false,
            "ordering": false,
            "bLengthChange": false,
            "bInfo": false
        } );
    </script>
  	
  </head>
  
 <body>
 <!--===========加载敏感词================= START ====================-->
         <div id="Keyword_management" style="font-family:Microsoft Yahei;">        
           <div style="padding-left:26px;" class="main">                   
           <div>                 
                 <input type="text" id='keywords_serchValue' maxlength="30" placeholder='请输入关键字'>
                 <button style="font-family:Microsoft Yahei;"onclick="serchVaguekeywords()">搜索</button>
                 <br>
                 <br>
                 <input type="text" id='addKeyword' maxlength="30" placeholder='请输入敏感词'>
                 <button style="font-family:Microsoft Yahei;"onclick="keyword_Add()">添加敏感词</button>
                 <br>
                 <br>
                 <input type="hidden" id="ModifyKeyword_ID" name="keyword_id" value=''>
                 <input type="text" id='ModifyKeyword' maxlength="30" placeholder='请在这里修改敏感词'>
                 <button style="font-family:Microsoft Yahei;"onclick="keyword_Modify()">修改敏感词</button>
            </div>         	
           <div id='tableKeyword_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;" > 
            <table style="" id='tableKeyword' class="display" cellspacing="0" width="98%">
             <thead> 
              <tr>            
               <td>敏感词</td> 
               <td>时间</td> 
               <td>操作</td>          
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
