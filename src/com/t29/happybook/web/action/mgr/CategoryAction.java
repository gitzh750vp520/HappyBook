package com.t29.happybook.web.action.mgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.t29.happybook.entity.Category;
import com.t29.happybook.service.CategoryService;
import com.t29.happybook.web.tool.Paging;

@SuppressWarnings("serial")
public class CategoryAction extends HttpServlet {
	
	private CategoryService categoryService = new CategoryService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String uri = request.getRequestURI();
		if(uri.equals("/hb/mgr/category/manage")) {
			manage(request, response);			
		} else if(uri.equals("/hb/mgr/category/save")) {
			save(request, response);			
		} else if(uri.equals("/hb/mgr/category/list")) {
			list(request, response);			
		} else if(uri.equals("/hb/mgr/category/delete")) {
			delete(request, response);			
		} else {
			manage(request, response);
		}
	}
	
	public void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int countTotal = categoryService.getCategoryCount();
		Paging paging = new Paging(countTotal, request.getParameter("pageNO"), 3);
		
		List<Category> categories = categoryService.getCategories(paging.getPageNO(), 3);
			
		request.setAttribute("paging", paging);
		request.setAttribute("categories", categories);		
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/category-mgr.jsp").forward(request, response);	
	} 
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categories = categoryService.getAllCategories();
		JSONArray categoryArr = new JSONArray(categories, false);
		
		response.setContentType("text/html;charset=UTF-8");		
		PrintWriter out = response.getWriter();		
		out.write(categoryArr.toString());
		out.flush();
		out.close();
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String categoryName = request.getParameter("categoryName");	
		if(categoryName == null) {
			request.setAttribute("msg", "<span style='color: red; font-size:12px; font-weight: bold;'>分类信息保存失败：不能乱搞！</span>");
			manage(request, response);
			return;
		}
		boolean b = categoryService.save(categoryName);
		
		if(b == true) {
			// 向request中设置一个属性（当前想设置一个保存成功的信息属性）
			request.setAttribute("msg", "<span style='color: green; font-size:12px; font-weight: bold;'>分类信息保存成功！</span>");
		} else {
			// 向request中设置一个属性（当前想设置一个保存失败的信息属性）
			request.setAttribute("msg", "<span style='color: red; font-size:12px; font-weight: bold;'>分类信息保存失败：分类已经存在！</span>");
		}
		
		// 获取“请求派发器对象”，进行请求的转发（请求和响应）
		request.getRequestDispatcher("/mgr/category/manage").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tip;		
		String idStr = request.getParameter("id");
		if(idStr == null) {
			tip = "没有指定要删除的分类id参数，不要乱搞哦！";
		} else {
			int id;
			try {
				id = Integer.parseInt(idStr);
				CategoryService categoryService = new CategoryService();
				String result = categoryService.delete(id);			
				if(result.equals("有关联")){
					tip = "删除分类信息失败：有关联图书信息，无法删除！";
				} else if( result.equals("不存在")) {
					tip = "删除分类信息失败：指定的分类不存在，无法删除";
				} else {
					tip = "删除分类信息成功！";
				}
			} catch(Exception ex) {
				tip = "删除的分类id参数必须是整型，不要乱搞哦！";
			}	
		}		
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("/mgr/category/manage").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
