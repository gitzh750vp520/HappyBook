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
			request.setAttribute("msg", "<span style='color: red; font-size:12px; font-weight: bold;'>������Ϣ����ʧ�ܣ������Ҹ㣡</span>");
			manage(request, response);
			return;
		}
		boolean b = categoryService.save(categoryName);
		
		if(b == true) {
			// ��request������һ�����ԣ���ǰ������һ������ɹ�����Ϣ���ԣ�
			request.setAttribute("msg", "<span style='color: green; font-size:12px; font-weight: bold;'>������Ϣ����ɹ���</span>");
		} else {
			// ��request������һ�����ԣ���ǰ������һ������ʧ�ܵ���Ϣ���ԣ�
			request.setAttribute("msg", "<span style='color: red; font-size:12px; font-weight: bold;'>������Ϣ����ʧ�ܣ������Ѿ����ڣ�</span>");
		}
		
		// ��ȡ�������ɷ������󡱣����������ת�����������Ӧ��
		request.getRequestDispatcher("/mgr/category/manage").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tip;		
		String idStr = request.getParameter("id");
		if(idStr == null) {
			tip = "û��ָ��Ҫɾ���ķ���id��������Ҫ�Ҹ�Ŷ��";
		} else {
			int id;
			try {
				id = Integer.parseInt(idStr);
				CategoryService categoryService = new CategoryService();
				String result = categoryService.delete(id);			
				if(result.equals("�й���")){
					tip = "ɾ��������Ϣʧ�ܣ��й���ͼ����Ϣ���޷�ɾ����";
				} else if( result.equals("������")) {
					tip = "ɾ��������Ϣʧ�ܣ�ָ���ķ��಻���ڣ��޷�ɾ��";
				} else {
					tip = "ɾ��������Ϣ�ɹ���";
				}
			} catch(Exception ex) {
				tip = "ɾ���ķ���id�������������ͣ���Ҫ�Ҹ�Ŷ��";
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
