package com.ssafy.ssafytrip.model.dto;

//페이징 관련 데이터 처리 클래스
public class PageResult {
	private int pageNo;
	private int count;

	private int tabSize;
	private int listSize;

	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	public PageResult(int pageNo, int count) {
		this(pageNo, count, 10, 10);
	}

	public PageResult(int pageNo, int count, int listSize, int tabSize) {
		this.pageNo = pageNo;
		this.count = count;
		this.listSize = listSize;
		this.tabSize = tabSize;

		int currTab = (pageNo - 1) / tabSize + 1;
		int lastPage = (count - 1) / listSize + 1;
		this.beginPage = (currTab - 1) * tabSize + 1;
		this.endPage = (currTab * tabSize > lastPage) ? lastPage : currTab * tabSize;

		this.prev = beginPage != 1;
		this.next = endPage != lastPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getCount() {
		return count;
	}

	public int getTabSize() {
		return tabSize;
	}

	public int getListSize() {
		return listSize;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
}
