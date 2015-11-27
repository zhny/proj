package cn.zhny.base.vo;

import java.util.List;

/**
 * EasyUi tree的数据结构
 * @author zhny
 *
 */
public class EUITree {
	private Long id;
	private String text;
	private List<EUITree> children;
	private String state="closed";
	private boolean checked=false;
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<EUITree> getChildren() {
		return children;
	}
	public void setChildren(List<EUITree> children) {
		this.children = children;
	}
	
	
}
