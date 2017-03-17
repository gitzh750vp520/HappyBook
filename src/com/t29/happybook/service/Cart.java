package com.t29.happybook.service;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Cart extends ArrayList<CartItem> {
	
	/* ��д�˸���ArrayList��add����
	 * ���Ҫ��ӵ�CartItem�Ѿ����ڣ����ҵ���CartItem����������
	 * ��������ڣ��ŵ��ø����add������������ӵ����ﳵ��
	 *  */
	public boolean add(CartItem item) {
		boolean exists = false;
		for(CartItem each : this) {
			if(each.getId() == item.getId()) {
				exists = true;
				each.setCount(each.getCount() + 1);
				break;
			}
		}		
		if(exists == false) {
			super.add(item);
		}
		return true;
	}

	
	/* ��ȡ���ﳵ�й��������ܺ͵ķ��� */
	public double getSum() {
		double sum = 0;
		for(CartItem item : this) {
			sum += item.getTotal();
		}
		return sum;
	}
	
}
