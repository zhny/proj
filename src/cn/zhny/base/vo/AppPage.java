package cn.zhny.base.vo;

import java.util.ArrayList;
import java.util.List;

public class AppPage<T> {
	private Long total=0L;
	private List<T> items;
	
	public AppPage(Long total, List<T> items) {
		this.total = total;
		this.items = items;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getItems() {
		return items==null?new ArrayList<T>():items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	
}
