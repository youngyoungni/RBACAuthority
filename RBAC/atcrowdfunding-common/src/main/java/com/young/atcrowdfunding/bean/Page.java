package com.young.atcrowdfunding.bean;

import java.util.List;

public class Page<T> {

	private List<T> Datas;
	private int pageNo;
	private int totalNo;
	private int totalSize;
	
	public Page() {
		super();
	}
	
	public Page(List<T> datas, int pageNo, int totalNo, int totalSize) {
		super();
		Datas = datas;
		this.pageNo = pageNo;
		this.totalNo = totalNo;
		this.totalSize = totalSize;
	}

	public List<T> getDatas() {
		return Datas;
	}
	public void setDatas(List<T> datas) {
		Datas = datas;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	
	
}
