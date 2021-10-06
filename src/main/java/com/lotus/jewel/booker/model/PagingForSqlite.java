package com.lotus.jewel.booker.model;

public class PagingForSqlite {
	
	private int rangeSize = 10;	//page count for block
	
	private int pageSize = 10;  //list for page
	
	private int total;

	private int startPage, currentPage = 1, endPage, lastPage;  //nowPage
	
	private int start;
	
	private int offset;
	
	public PagingForSqlite() {}
	
	public PagingForSqlite(int total, int currentPage, int pageSize) {
		this.total = total;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		calcPage();
	}
	
	public void calcPage() {
		calcLastPage(total, pageSize);
		calcStartEndPage(currentPage, rangeSize, lastPage);
		calcStartEnd(currentPage, pageSize);
	}
	
	public void calcLastPage(int total, int pageSize) {
		this.lastPage = (int) Math.ceil((double)total / (double)pageSize);
	}
	
	public void calcStartEndPage(int currentPage, int rangeSize, int lastPage) {
		int endPage = ((int) Math.ceil((double)currentPage / (double)rangeSize)) * rangeSize;
		if(lastPage < endPage) {
			endPage = lastPage;
		}
		this.endPage = endPage;
		
		int startPage = this.endPage - rangeSize + 1;
		if(startPage < -1) {
			startPage = 1;
		}
		this.startPage = startPage;
	}
	
	public PagingForSqlite getPaging() {
		return this;
	}
	
	public void calcStartEnd(int currentPage, int pageSize) {
		this.offset = pageSize;
		this.start = (currentPage -1) * pageSize;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
