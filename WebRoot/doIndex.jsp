<%@ page language="java" import="java.util.*, com.t29.happybook.web.tool.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/autoLogin.jsp" %>
<% 	
	
	request.setCharacterEncoding("UTF-8");	
	String param_cname = request.getParameter("cname");
	if(param_cname != null) {	
		// get提交的方式，中文使用如下方式处理乱码	
		param_cname = new String(param_cname.getBytes("iso8859-1"), "UTF-8");		
		session.setAttribute("cond_cname", param_cname);
		session.setAttribute("pageNOStr", "1");
	}
	
	String param_btitle = request.getParameter("btitle");
	if(param_btitle != null) {		
		session.setAttribute("cond_btitle", param_btitle);
		session.setAttribute("pageNOStr", "1");
	}	
	
	String cname = (String)session.getAttribute("cond_cname");
	String btitle =(String)session.getAttribute("cond_btitle");
	
	String pageNOStr = request.getParameter("pageNO");
	if(pageNOStr != null) {
		session.setAttribute("pageNOStr", pageNOStr);
	}
	
	pageNOStr = (String)session.getAttribute("pageNOStr");	
	
	if(pageNOStr == null) {
		pageNOStr = "1";
		session.setAttribute("pageNOStr", pageNOStr);
	}
	
	BookService bookService = new BookService();
	int countTotal = bookService.getCountTotal(cname, btitle);
	Paging paging = new Paging(countTotal, pageNOStr, 3);	
	List<Book> books = bookService.getBooks(cname, btitle, paging.getPageNO(), 3);
	request.setAttribute("books", books);
	request.setAttribute("paging", paging);
	request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
%>