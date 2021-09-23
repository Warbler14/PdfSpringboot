package com.lotus.jewel.booker.model;

public class PagingForSqlite {
	
	private int cntPage = 5;
	
	private int cntPerPage = 10;
	
	private int total;

	private int startPage, nowPage = 1, endPage, lastPage;
	
	private int start;
	
	private int offset;
	
	public PagingForSqlite() {}
	
	public PagingForSqlite(int total, int nowPage, int cntPerPage) {
		this.total = total;
		this.nowPage = nowPage;
		this.cntPerPage = cntPerPage;
		calcPage();
	}
	
	public void calcPage() {
		calcLastPage(total, cntPerPage);
		calcStartEndPage(nowPage, cntPage, lastPage);
		calcStartEnd(nowPage, cntPerPage);
	}
	
	public void calcLastPage(int total, int cntPerPage) {
		this.lastPage = (int) Math.ceil((double)total / (double)cntPerPage);
	}
	
	public void calcStartEndPage(int nowPage, int cntPage, int lastPage) {
		int endPage = ((int) Math.ceil((double)nowPage / (double)cntPage)) * cntPage;
		if(lastPage < endPage) {
			endPage = lastPage;
		}
		this.endPage = endPage;
		
		int startPage = this.endPage - cntPage + 1;
		if(startPage < -1) {
			startPage = 1;
		}
		this.startPage = startPage;
	}
	
	public PagingForSqlite getPaging() {
		return this;
	}
	
	public void calcStartEnd(int nowPage, int cntPerPage) {
		this.offset = cntPerPage;
		this.start = (nowPage -1) * cntPerPage;
	}

	public int getCntPage() {
		return cntPage;
	}

	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
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

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
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
