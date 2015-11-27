package cn.zhny.base.vo;

import java.util.List;
import java.util.concurrent.Future;

import com.avaje.ebean.PagedList;

/**
 * 用于解决ebean在自定义sql时分页的Bug
 * @author zhny
 *
 * @param <T>
 */
public class EbeanPageFix<T> implements PagedList<T>{
	private List<T> list;
	
	private int totalRowCount;
	
	private int pageSize;
	
	private int pageIndex;
	
	public EbeanPageFix(List<T> list,int totalRowCount,int pageSize,int pageIndex) {
		this.list=list;
		this.totalRowCount=totalRowCount;
		this.pageSize=pageSize;
		this.pageIndex=pageIndex;
	}
	
	@Override
	public List<T> getList() {
		return list;
	}

	@Override
	public int getTotalRowCount() {
		return totalRowCount;
	}

	@Override
	public int getTotalPageCount() {
		return totalRowCount/pageSize+(totalRowCount%pageSize==0?0:1);
	}

	@Override
	public int getPageIndex() {
		return pageIndex;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public boolean hasPrev() {
		return false;
	}


	@Override
	public String getDisplayXtoYofZ(String to, String of) {
		return null;
	}

	@Override
	public void loadRowCount() {
		
	}

	@Override
	public Future<Integer> getFutureRowCount() {
		return null;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
