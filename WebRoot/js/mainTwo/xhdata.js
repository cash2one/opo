// JavaScript Document
//网络热点
var json5 = "{htc:[{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'}]}";
$(function(){	
	var jsonData = eval(json5);
	$.each(jsonData,function(i,n){
		$("#Searchresult").append("<li>"+"<p class=\"p1\">"+n.name1+"</p>"+"<p class=\"p2\">"+n.name2+"</p>"+"</li>");  
	});
	var num_entries = $("#Searchresult li").length;	
	var showCount = 15;
	$("#total").html("一共有 "+num_entries+" 条数据 每页显示"+showCount+"条");
	var initPagination = function() {
		
		// 创建分页
		$("#Pagination").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 6, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#htcList").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Searchresult li:eq("+i+")").clone();
			$("#htcList").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});



// JavaScript Document
//定网预警
var json1 = "{htc:[{'name1':'杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'}]}";
$(function(){	
	var jsonData = eval(json1);
	$.each(jsonData,function(i,n){
		$("#Searchresult1").append("<li>"+"<p class=\"p1\">"+n.name1+"</p>"+"<p class=\"p2\">"+n.name2+"</p>"+"</li>");  
	});
	var num_entries = $("#Searchresult1 li").length;	
	var showCount = 15;
	$("#total").html("一共有 "+num_entries+" 条数据 每页显示"+showCount+"条");
	var initPagination = function() {
		
		// 创建分页
		$("#Pagination1").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 6, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#htcList1").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Searchresult1 li:eq("+i+")").clone();
			$("#htcList1").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});
// JavaScript Document



// JavaScript Document
//当日预警
var json2 = "{htc:[{'name1':'李易峰','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'}]}";
$(function(){	
	var jsonData = eval(json2);
	$.each(jsonData,function(i,n){
		$("#Searchresult2").append("<li>"+"<p class=\"p1\">"+n.name1+"</p>"+"<p class=\"p2\">"+n.name2+"</p>"+"</li>");  
	});
	var num_entries = $("#Searchresult2 li").length;	
	var showCount = 15;
	$("#total").html("一共有 "+num_entries+" 条数据 每页显示"+showCount+"条");
	var initPagination = function() {
		
		// 创建分页
		$("#Pagination2").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 6, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#htcList2").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Searchresult2 li:eq("+i+")").clone();
			$("#htcList2").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});
// JavaScript Document// JavaScript Document


// JavaScript Document
//最新消息
var json3 = "{htc:[{'name1':'杨幂','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'}]}";
$(function(){	
	var jsonData = eval(json3);
	$.each(jsonData,function(i,n){
		$("#Searchresult3").append("<li>"+"<p class=\"p1\">"+n.name1+"</p>"+"<p class=\"p2\">"+n.name2+"</p>"+"</li>");  
	});
	var num_entries = $("#Searchresult3 li").length;	
	var showCount = 15;
	$("#total").html("一共有 "+num_entries+" 条数据 每页显示"+showCount+"条");
	var initPagination = function() {
		
		// 创建分页
		$("#Pagination3").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 6, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#htcList3").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Searchresult3 li:eq("+i+")").clone();
			$("#htcList3").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});
// JavaScript Document// JavaScript Document

// JavaScript Document
//往日预警
var json4 = "{htc:[{'name1':'河北','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'},{'name1':'周杰伦','name2':'2015-08-11'}]}";
$(function(){	
	var jsonData = eval(json4);
	$.each(jsonData,function(i,n){
		$("#Searchresult4").append("<li>"+"<p class=\"p1\">"+n.name1+"</p>"+"<p class=\"p2\">"+n.name2+"</p>"+"</li>");  
	});
	var num_entries = $("#Searchresult4 li").length;	
	var showCount = 15;
	$("#total").html("一共有 "+num_entries+" 条数据 每页显示"+showCount+"条");
	var initPagination = function() {
		
		// 创建分页
		$("#Pagination4").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 6, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#htcList4").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Searchresult4 li:eq("+i+")").clone();
			$("#htcList4").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});
// JavaScript Document// JavaScript Document








