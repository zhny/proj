package cn.zhny.base.vo;

public class ReturnMsg {
	private boolean success;
	private String msg;
	
	public ReturnMsg() {}
	
	public ReturnMsg(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
