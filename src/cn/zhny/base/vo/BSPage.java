package cn.zhny.base.vo;

import java.util.Collections;
import java.util.List;

import com.avaje.ebean.PagedList;

public class BSPage {
	private Long total=0L;
	private Integer current=0;
	private Integer rowCount=0;
	private List<?> rows=Collections.emptyList();
	
	public BSPage(PagedList<?> page,Integer rowCount) {
		 if(page!=null){
			 this.rows = page.getList();
			 this.total = (long) page.getTotalRowCount();
			 this.current=page.getPageIndex()+1;
			 this.rowCount=rowCount;
		 }
	}
	
	public BSPage(Long total, List<?> rows,Integer current,Integer rowCount) {
		this.total = total;
		this.rows = rows;
		this.current=current;
		this.rowCount=rowCount;
	}
	
	public Integer getCurrent() {
		return current;
	}


	public void setCurrent(Integer current) {
		this.current = current;
	}


	public Integer getRowCount() {
		return rowCount;
	}


	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
