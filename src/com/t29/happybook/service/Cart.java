package com.t29.happybook.service;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Cart extends ArrayList<CartItem> {
	
	/* 重写了父类ArrayList的add方法
	 * 如果要添加的CartItem已经存在，则找到该CartItem，更新数量
	 * 如果不存在，才调用父类的add方法，将其添加到购物车中
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

	
	/* 获取购物车中购物项金额总和的方法 */
	public double getSum() {
		double sum = 0;
		for(CartItem item : this) {
			sum += item.getTotal();
		}
		return sum;
	}
	
}
