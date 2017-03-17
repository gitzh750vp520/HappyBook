package com.t29.happybook.web.action.mgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.t29.happybook.entity.Book;
import com.t29.happybook.entity.Category;
import com.t29.happybook.service.BookService;
import com.t29.happybook.service.CategoryService;
import com.t29.happybook.web.tool.Helper;
import com.t29.happybook.web.tool.Paging;

@SuppressWarnings("serial")
public class BookAction extends HttpServlet {

	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// uri : /hb/mgr/book/xxx
		String uri = request.getRequestURI();
		if(uri.equals("/hb/mgr/book/manage")) {
			manage(request, response);
		} else if(uri.equals("/hb/mgr/book/list")) {
			list(request, response);
		} else if(uri.equals("/hb/mgr/book/delete")) {
			delete(request, response);
		} else if(uri.equals("/hb/mgr/book/save")) {
			save(request, response);
		} else if(uri.equals("/hb/mgr/book/edit")) {
			edit(request, response);
		} else if(uri.equals("/hb/mgr/book/modify")) {
			modify(request, response);
		} else {
			manage(request, response);			
		}
	}
	
	public void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/book-mgr.jsp").forward(request, response);
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> allCategories = categoryService.getAllCategories();
		
		int countTotal = bookService.getCountTotal();
		Paging paging = new Paging(countTotal, request.getParameter("pageNO"), 3);
		List<Book> books = bookService.getBooks(paging.getPageNO(), 3);	
		
		
		JSONObject map = new JSONObject();		
		try {
			map.put("paging", new JSONObject(paging));			
			map.put("books", new JSONArray(books, false));		
		} catch (JSONException e) {		
			throw new RuntimeException(e);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(map);
		out.flush();
		out.close();
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("bid"));			
		bookService.delete(id);
		response.sendRedirect("/hb/mgr/book/manage");
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), request, response);
		try {
			su.upload();
		} catch (SmartUploadException e) {
			System.out.println("文件上传组件在上传文件时报错！");
			out.write("error#图书信息保存失败：文件上传异常！");
			out.flush();
			out.close();
			return;
		}
		
		String photo = "default.jpg";
			
		File file = su.getFiles().getFile(0);	
		if(!file.getFileName().equals("")) {
			// 必须是图片（文件后缀必须是gif或者jpg或者png的）
			if( !file.getFileExt().equals("gif") && !file.getFileExt().equals("jpg") &&  !file.getFileExt().equals("png")){			
				out.write("error#图书信息保存失败：图书图片必需是图片格式！");
				out.flush();
				out.close();
				return;
			}
			
			if( file.getSize() > 1024 * 100){
				out.write("error#图书信息保存失败：图书图片文件不能超过100KB！");
				out.flush();
				out.close();
				return;
			}
			
			String fileSaveName = Helper.getFileNameOfCurrTime(file.getFileExt());
			try {
				file.saveAs("/photo/" + fileSaveName);
			} catch (SmartUploadException e) {
				System.out.println("文件上传组件在另存已上传的文件时报错！");
				out.write("error#图书信息保存失败：上传的文件在保存到服务器时异常！");
				out.flush();
				out.close();
				return;
			}
			photo = fileSaveName;
		} 
		Request req = su.getRequest();
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		int cid = Integer.parseInt(req.getParameter("cid"));
		double price = Double.parseDouble(req.getParameter("price"));
		String publisher = req.getParameter("publisher");
		
		
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.getCategory().setId(cid);
		book.setPhoto(photo);
		
		bookService.save(book);
		out.write("success#图书信息保存成功！");
		out.flush();
		out.close();
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("bid"));		
		request.setAttribute("book", bookService.getBookById(id));
		request.setAttribute("allCategories", categoryService.getAllCategories());
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/book-edit.jsp").forward(request, response);
	}
	
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);
		try {
			su.upload();
		} catch (SmartUploadException e) {
			System.out.println("文件上传组件在上传文件时报错！");
			request.setAttribute("tipSave", "图书信息修改失败：文件上传异常！");
			request.getRequestDispatcher("/mgr/book/manage").forward(request, response);
			return;
		}
		
		Request req = su.getRequest();
		int id = Integer.parseInt(req.getParameter("bid"));
		String photo = req.getParameter("oldPhoto");
			
		File file = su.getFiles().getFile(0);	
		if(!file.getFileName().equals("")) {
			// 必须是图片（文件后缀必须是gif或者jpg或者png的）
			if( !file.getFileExt().equals("gif") && !file.getFileExt().equals("jpg") &&  !file.getFileExt().equals("png")){
				request.setAttribute("tipBookModify", "图书信息修改失败：图书图片必需是图片格式！");
				request.getRequestDispatcher("/mgr/book/edit?bid=" + id).forward(request, response);
				return;
			}
			
			if( file.getSize() > 1024 * 100){
				request.setAttribute("tipBookModify", "图书信息修改失败：图书图片文件不能超过100KB！");
				request.getRequestDispatcher("/mgr/book/edit?bid=" + id).forward(request, response);
				return;
			}
			
			if(photo.equals("default.jpg")) {
				photo = Helper.getFileNameOfCurrTime(file.getFileExt());
			} 			
			try {
				file.saveAs("/photo/" + photo);
			} catch (SmartUploadException e) {
				System.out.println("文件上传组件在另存已上传的文件时报错！");
				request.setAttribute("tipSave", "图书信息修改失败：上传的文件在保存到服务器时异常！");
				request.getRequestDispatcher("/mgr/book/manage").forward(request, response);
				return;
			}
						
		}			
		
		String title = req.getParameter("btitle");
		String author = req.getParameter("bauthor");
		int cid = Integer.parseInt(req.getParameter("cid"));
		double price = Double.parseDouble(req.getParameter("bprice"));
		String publisher = req.getParameter("bpublisher");
		
		Book book = new Book(id, title, author, price, publisher, photo, new Category(cid, null));
		
		bookService.modify(book);
		
		request.setAttribute("tipBookModify", "图书信息修改成功！");
		request.getRequestDispatcher("/mgr/book/edit?bid=" + id).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
