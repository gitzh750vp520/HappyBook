package com.t29.happybook.service;

import java.util.List;

import com.t29.happybook.dao.BookDao;
import com.t29.happybook.dao.CategoryDao;
import com.t29.happybook.entity.Category;

public class CategoryService {
	
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	
	/**
	 * 分页获取图书分类信息
	 * @param pageNO 第几页
	 * @param pageSize 每页几条
	 * @return 当前页的分类信息
	 */
	public List<Category> getCategories(int pageNO, int pageSize) {
		return categoryDao.getCategories(pageNO, pageSize);
	}
	
	/**
	 * 获取分类信息总数
	 * @return 总数
	 */
	public int getCategoryCount() {
		return categoryDao.getCategoryCount();
	}
	
	// 保存新分类的业务逻辑方法：如果保存成功，返回true；如果保存失败，则一定是因为分类名已经存在，返回false；
	public boolean save(String categoryName) {		
		// 判断categoryName是否已经存在了
		List<Category> allCategories = categoryDao.getAllCategories();
		for(Category each : allCategories) {
			if(each.getName().equalsIgnoreCase(categoryName)) {
				return false;
			}
		}	
		categoryDao.add(categoryName);
		return true;
	}
	
	/**
	 * 根据指定的ID删除分类信息
	 * @param id 要删除的分类ID
	 * @return 如果删除成功返回2；如果因为没有找到指定ID的分类造成无法删除，则返回0；如果因为指定ID的分类有关联的图书信息造成无法删除，则返回1。
	 */
	public String delete(int id) {
		if(bookDao.getCountByCid(id) != 0) {
			return "有关联";
		}
		
		return categoryDao.delete(id) == 0 ? "不存在" : "成功";
	}
	
	public static void main(String[] args) {
		CategoryService ser = new CategoryService();
		System.out.println(ser.delete(110));
	}

}
