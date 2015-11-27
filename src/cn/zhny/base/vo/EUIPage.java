package cn.zhny.base.vo;

import java.util.Collections;
import java.util.List;

import com.avaje.ebean.PagedList;


public class EUIPage {
	private Long total=0L;
	private List<?> rows=Collections.emptyList();
	
	public EUIPage(PagedList<?> page) {
		 if(page!=null){
			 this.rows = page.getList();
			 this.total = (long) page.getTotalRowCount();
		 }
	}
	
	public EUIPage(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
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
