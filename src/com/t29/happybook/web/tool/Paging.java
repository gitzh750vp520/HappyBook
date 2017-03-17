package com.t29.happybook.web.tool;

public class Paging {
	
	private int pageNO = 1;
	private int pagePrev;
	private int pageNext;
	private int pageTotal;
	private int countTotal;

	/**
	 * 实现分页逻辑
	 * @param countTotal 数据的总数量
	 * @param pageNOStr 当前页参数
	 * @param pageSize 每页几条
	 */
	public Paging(int countTotal, String pageNOStr, int pageSize) {	
		if(countTotal < 0) {
			throw new RuntimeException("数据总条数不能为负数");
		}
		if(pageSize < 1) {
			throw new RuntimeException("每页条数不能小于1");
		}
		this.countTotal = countTotal;
		this.pageTotal = this.countTotal % pageSize == 0 ? this.countTotal / pageSize : this.countTotal / pageSize + 1; 
		this.pageTotal = this.pageTotal == 0 ? 1 : this.pageTotal;
		
		if(pageNOStr != null) {
			try {
				this.pageNO = Integer.parseInt(pageNOStr);
			} catch(Exception ex) {				
			}
		}
		
		if(this.pageNO < 1) {
			this.pageNO = 1;
		}
		
		if(this.pageNO > this.pageTotal) {
			this.pageNO = this.pageTotal;
		}
		
		if(this.pageNO == 1) {
			this.pagePrev = 1;
		} else {
			this.pagePrev = this.pageNO - 1;			
		}
		
		if(this.pageNO == this.pageTotal) {
			this.pageNext = this.pageNO;
		} else {
			this.pageNext = this.pageNO + 1;			
		}
	}

	public int getPageNO() {
		return pageNO;
	}

	public int getPagePrev() {
		return pagePrev;
	}

	public int getPageNext() {
		return pageNext;
	}

	public int getPageTotal() {
		return pageTotal;
	}
	
	public int getCountTotal() {
		return countTotal;
	}
	
}
