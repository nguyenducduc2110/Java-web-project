package com.webtutorial.paging;

import com.webtutorial.sort.Sorter;

public class PageRequest implements Pageable{
	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		super();
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}
	
	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}
	@Override
	public Integer getOffset() {
		if(this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if(this.sorter != null) {
			return sorter;
		}
		return null;
	}
}
