<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="/hb/css/mgr.css"/>
	<link rel="stylesheet" type="text/css" href="/hb/js/jquery/jquery-ui.css" />
	<script type="text/javascript" src="/hb/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/hb/js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="/hb/js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="/hb/js/jquery/ajaxfileupload.js"></script>
	<script type="text/javascript">
	
	
$(function(){
	$("#addBookForm").validate({
		submitHandler : function(){ 
			addBook();
		} ,
		errorClass : "fail",		
		rules : {
			title : {
				required : true,
				minlength : 2
			},
			author : "required",
			price : {
				required : true,
				number : true,
				min : 0.0
			},
			publisher : "required"
		},
		messages : {
			title : {
				required : "图书名称不能为空！",
				minlength : "图书名称不能少于2个字符"
			},
			author : "作者不能为空",
			price : {
				required : "价格必填",
				number : "价格必须是数字",
				min : "价格不能为负数"
			},
			publisher : "出版社不能为空！"
			
		}
	});

	loadAllCategories();
	loadBooks(1);
	$("a[id^='a-paging-']").on("click", function(){
		var pageNO = this.id.substring(11);
		loadBooks(pageNO);
	});	
	
});

/* 保存图书信息的方法 */
function addBook(){
	$("#tipAddBook").html("");
	var title = $("[name='title']").val();
	var author = $("[name='author']").val();
	var price = $("[name='price']").val();
	var cid = $("[name='cid']").val();
	var publisher = $("[name='publisher']").val();
	
	$.ajaxFileUpload({
		"url" : "/hb/mgr/book/save", // 提交的地址
		"type" : "POST", // 提交的方式
		"data" : {"title" : title, "author" : author, "price" : price, "cid" : cid, "publisher" : publisher}, // 提交的参数
		"dataType" : "text", // 希望返回的数据格式
		"fileElementId" : "pic", // 要上传的文件域的id属性值（谨记：该文件域必须有name属性）
		"success" : function(result){ // 当服务器响应成功后，调用我们的方法（方法中的第1个参数是服务器响应的内容）
			if(result.charAt(0) == "s") {
				$("#tipAddBook").html(result.substring(8)).attr("class", "success");
				loadBooks(1);
			} else if(result.charAt(0) == "e") {
				$("#tipAddBook").html(result.substring(6)).attr("class", "fail");
			} else {
				$("#tipAddBook").html("保存图书信息时，服务器异常！").attr("class", "fail");
			}
		},
		"error" : function(data, status, e) {
			alert(e);
		}
	});
}

/* 加载所有的图书分类信息 */
function loadAllCategories() {
	$.getJSON("/hb/mgr/category/list", function(categoryArr){			
		var options = "";
		for(var i in categoryArr) {
			options += "<option value='" + categoryArr[i].id + "'>" + categoryArr[i].name + "</option>";
		}
		$("#cid").html(options);
		
	});
}

/* 加载图书信息列表 */
function loadBooks(pageNO){
	$.getJSON("/hb/mgr/book/list?r=" + Math.random(), {"pageNO" : pageNO}, function(jsonData){
		var bookArr = jsonData["books"];
		var paging = jsonData["paging"];		
		var trs = '<tr><td class="header" width="100">书名</td>';
		trs += '<td class="header" width="60">作者</td>';
		trs += '<td class="header" width="60">类型</td>';
		trs += '<td class="header" width="60">售价</td>';
		trs += '<td class="header" width="60">操作</td>';		
		for(var i in bookArr) {
			var b = bookArr[i];
			trs += '<tr><td>《' + b.title + '》</td>';
			trs += '<td>' + b.author + '</td>';
			trs += '<td>' + b.category.name + '</td>';
			trs += '<td>￥' + b.price + '</td>';
			trs += '<td><a href="javascript://">删除</a>&nbsp;';
			trs += '<a href="javascript://">编辑</a></td></tr>';
		}		
		$(".section-left table").html(trs);
		
		$("a[id^='a-paging-f-']").attr("id","a-paging-f-1" );
		$("a[id^='a-paging-p-']").attr("id","a-paging-p-" + paging.pagePrev );
		$("a[id^='a-paging-n-']").attr("id","a-paging-n-" + paging.pageNext );
		$("a[id^='a-paging-t-']").attr("id","a-paging-t-" + paging.pageTotal );
		$("#span-paging-pageNO").html(paging.pageNO);
		$("#span-paging-pageTotal").html(paging.pageTotal);
		$("#span-paging-totalCount").html(paging.countTotal);
	});
}
	</script>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>鼎育教育--图书网后台管理系统</h1></div>
    	<%@ include file="/WEB-INF/jsp/mgr/inc/menu.jsp" %>
    	<div id="main">
			<div class="section-left">    	
				<h2>图书信息列表</h2>		
				<table class="table" cellspacing="0" style="font-size: 12px;"></table>
			    <div style="height: 40px; line-height: 40px; font-size: 12px;">
			    	<a id="a-paging-f-" href="javascript://">首页</a>
			    	<a id="a-paging-p-" href="javascript://">上一页</a>
			    	<a id="a-paging-n-" href="javascript://">下一页</a>
			    	<a id="a-paging-t-" href="javascript://">尾页</a>
			    	<span id="span-paging-pageNO"></span>/<span id="span-paging-pageTotal"></span>&nbsp;共计：<span id="span-paging-totalCount"></span>条			    	
			    </div>
			</div>
			<div class="section-right">
				<h2>添加图书信息</h2>
				<form id="addBookForm" action="/hb/mgr/book/save" method="post" enctype="multipart/form-data">
					<p>图书书名：<input type="text" name="title"  /></p>
					<p>图书作者：<input type="text" name="author"  /></p>
					<p>图书分类：
						<select id="cid" name="cid"></select>
					</p>
					<p>图书售价：<input type="text" name="price"  /></p>
					<p>图书出版社：<input type="text" name="publisher"  /></p>
					<p>选择图片：<input id="pic" type="file" name="photo"  /></p>    				 				
					<p><input id="btnSave" type="submit" value=" 保 存 "  />&nbsp;<span id="tipAddBook"></span></p>
				</form>
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>