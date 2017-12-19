package com.portal.pageModel;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {

	// 总的记录行数
	private long totalLines;

	// 总的页数
	private long totalPages;

	// 每页显示多少行
	private long pageLines;

	// 当前显示第几页
	private long currentPage;

	// 当前页的数据行数量,只有在最后一页时会和每页显示多少行可能不同
	private long currentPageLineNum;

	// 当前页的所有数据行
	@SuppressWarnings("rawtypes")
	private List<?> rows = new ArrayList();

	public long getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(long totalLines) {
		this.totalLines = totalLines;
	}

	public long getPageLines() {
		return pageLines;
	}

	public void setPageLines(long pageLines) {
		this.pageLines = pageLines;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public long getCurrentPageLineNum() {
		return currentPageLineNum;
	}

	public void setCurrentPageLineNum(long currentPageLineNum) {
		this.currentPageLineNum = currentPageLineNum;
	}
}
