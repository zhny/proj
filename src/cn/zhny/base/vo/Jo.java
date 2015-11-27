package cn.zhny.base.vo;

import java.util.Collections;

/**
 * 
 * @author zhny
 * 用户返回数据的VO对象
 */
public class Jo {
	
	
	public static final int CODE_SUCCESS=0;
	public static final int CODE_FAIL=1;
	public static final int CODE_UNLOGIN=2;			//未登录
	public static final int CODE_NOPERMISSION=3;	//未授权的操作
	//用户模块11，接口01，接口错误码01
	public static final int INVALID_PHONE = 110101;//手机号码不正确
	public static final int INVALID_SMSCODE = 110102;//无效的验证码
	public static final int SMSCODE_SENDFAILED = 110103;//验证码发送失败
	public static final int PASSWORD_RESET_FAILED = 110201;//密码重置失败
	public static final int INVALID_OLDPAS = 110301;//无效的旧密码
	public static final int INVALID_PAS = 110302;//无效的密码
	public static final int UPDATE_PAS_FAILED = 110303;//密码修改失败
	public static final int USER_NOT_FOUND = 110304;//用户未找到
	public static final int TICKET_ORDER_FAILED = 110401;//船票下订单失败
	public static final int NO_SERVICE_FOUND = 110501;//未找到对应名称的服务
	public static final int GET_TGT_FAILED = 110502;//获取授权令牌失败
	public static final int GET_ST_FAILED = 110503;//获取访问票据失败
	
	private int code;				//状态码
	private String msg;				//消息
	private Object data = Collections.emptyMap();				//返回对象
	
	public Jo(){
		this.code=CODE_SUCCESS;
		this.msg = "成功";
	}
	
	public Jo(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public Jo(int code,Object data){
		this.code=code;
		this.msg="";
		this.data=data;
	}
	
	public Jo(Object data) {
		this.code=CODE_SUCCESS;
		this.msg="";
		this.data = data;
	}
	public Jo(String msg, Object data) {
		this.code=CODE_SUCCESS;
		this.msg = msg;
		this.data = data;
	}
	public Jo(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
