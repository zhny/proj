package cn.zhny.base.utils;

import cn.zhny.base.entity.User;


public class SessionObject {
	
	final public static String SESSION_KEY="SESSION_KEY";
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
