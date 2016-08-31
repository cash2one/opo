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

 <link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery.dataTables.css" /> 
 <link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery-ui.css"> 
  
 
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js" ></script> 


  <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>  

  <script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.dataTables.js"></script> 
  
  <script type="text/javascript" src="<%=path%>/js/jquery-ui.js" ></script>  
 <script type="text/javascript" src="<%=path%>/js/setting/newkeywords.js"></script>
 <style type="text/css">      
   
          #BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:50px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;} 
      
      #DialogDiv{position:absolute; left:50%; top:50%; margin-left:-200px; height:auto; z-index:100;background-color:#fff; border:1px #8FA4F5 solid;   background: #bbeeeb;} 
      #DialogDiv h2{ height:25px; font-size:14px; background-color:#8FA4F5; position:relative; padding-left:10px; line-height:25px;  margin-top: 0px;}
      #DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
     
      #DialogDiv .form{height:300px;height:auto;min-height:200px;width:800px; background:#bbeeeb;margin:0 auto;} 
     
  </style>
 
  <script>
        $.extend( $.fn.dataTable.defaults, {
            "searching": false,
            "ordering": false,
            "bLengthChange": false,
            "bInfo": false
        } );
    </script>
</head>

 <!---------设置最新关键词  start---------------------------------------------------------------------->
         <div id="newKeyword_management" style="font-family:Microsoft Yahei;">        
           <div style="padding-left:26px;" class="main">                   
           <div>                 
                 <input type="text" id='newKeyword_serchValue' maxlength="30" placeholder='请输入关键字'>
                 <button style="font-family:Microsoft Yahei;"onclick="serchkeywords()">搜索</button>
                 <button style="font-family:Microsoft Yahei;"onclick="addnewkeyword()">添加</button>
            </div>         	
           <div id='tablenewKeyword_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;" > 
            <table style="" id='tablenewKeyword' class="display" cellspacing="0" width="98%">
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
         
       <!-- ----------------关键字修改、添加 蒙层div----------start--------------- -->     
          <div id="HiddenBgDiv" >
                      <div id="BgDiv"></div>
  
						  <!--遮罩层显示的DIV1-->
						  <div id="DialogDiv" style="display:none;opacity: 0.9;">
						   <div class="form" id="form" style="">
						    <h2>啦啦啦<a id="btnClose" onclick="closeDiv('DialogDiv')">关闭</a></h2> 
						    <!--  <a onMouseOut="document.all.HiddenBgDiv.style.visibility='hidden'">返回</a>   -->
						    <p style="text-align: center;margin-top: 30px;font-size: 20px;">暂无评论</p>
							
						      </div>
							  
						  </div>
					 </div>
		<!-- ----------------关键字修改、添加蒙层div----------end------------- -->  
       
              
 <!---------设置最新关键词  end----------------------------------------------------------------------->

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
