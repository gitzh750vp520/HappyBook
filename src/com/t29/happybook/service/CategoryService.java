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
	 * ��ҳ��ȡͼ�������Ϣ
	 * @param pageNO �ڼ�ҳ
	 * @param pageSize ÿҳ����
	 * @return ��ǰҳ�ķ�����Ϣ
	 */
	public List<Category> getCategories(int pageNO, int pageSize) {
		return categoryDao.getCategories(pageNO, pageSize);
	}
	
	/**
	 * ��ȡ������Ϣ����
	 * @return ����
	 */
	public int getCategoryCount() {
		return categoryDao.getCategoryCount();
	}
	
	// �����·����ҵ���߼��������������ɹ�������true���������ʧ�ܣ���һ������Ϊ�������Ѿ����ڣ�����false��
	public boolean save(String categoryName) {		
		// �ж�categoryName�Ƿ��Ѿ�������
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
	 * ����ָ����IDɾ��������Ϣ
	 * @param id Ҫɾ���ķ���ID
	 * @return ���ɾ���ɹ�����2�������Ϊû���ҵ�ָ��ID�ķ�������޷�ɾ�����򷵻�0�������Ϊָ��ID�ķ����й�����ͼ����Ϣ����޷�ɾ�����򷵻�1��
	 */
	public String delete(int id) {
		if(bookDao.getCountByCid(id) != 0) {
			return "�й���";
		}
		
		return categoryDao.delete(id) == 0 ? "������" : "�ɹ�";
	}
	
	public static void main(String[] args) {
		CategoryService ser = new CategoryService();
		System.out.println(ser.delete(110));
	}

}
