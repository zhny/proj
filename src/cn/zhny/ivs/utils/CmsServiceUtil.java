package cn.zhny.ivs.utils;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhny.base.utils.Configure;
import cn.zhny.base.utils.Digests;
import cn.zhny.ivs.webservice.CmsService;
import cn.zhny.ivs.webservice.CmsServicePortType;
import cn.zhny.ivs.webservice.CommonResult;
import cn.zhny.ivs.webservice.LoginResult;

public class CmsServiceUtil {
	public static final Logger log = LoggerFactory.getLogger(CmsServiceUtil.class);
	
	private static CmsServicePortType port;
	private static String sessionId;
	
	private static String url=Configure.getInstance().getValue("production-application", "hk.webservice.url").toString();
	private static String localip=Configure.getInstance().getValue("production-application", "local.ip").toString();
	private static String remoteip=Configure.getInstance().getValue("production-application", "hk.webservice.ip").toString();
	private static int remoteport=Integer.valueOf(Configure.getInstance().getValue("production-application", "hk.webservice.port").toString());
	private static String user=Configure.getInstance().getValue("production-application", "hk.webservice.user").toString();
	private static String password=Configure.getInstance().getValue("production-application", "hk.webservice.password").toString();
	
	
	private static ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
	
	private static int i=1;
	
	static{
		login();
	}
	
	private static boolean threadIsRuning=false;
	
	private static void login(){
		try {
			CmsService ss = new CmsService(new URL(url));
			port = ss.getCmsServiceHttpSoap12Endpoint();
	        LoginResult loginResult=port.userLogin(user, Digests.md5(password), localip, remoteport, remoteip);
	        if(loginResult.getErrorCode().equals(0)){
	        	log.info("海康服务登陆成功");
	        	sessionId=loginResult.getSessionId().getValue();
	        	if(!threadIsRuning){
	        		threadIsRuning=true;
		        	scheduler.scheduleAtFixedRate(new Runnable(){
						@Override
						public void run() {
							if(i++%3==0)sessionId+="1";
							CommonResult commonResult=port.userOnlineHeartbeat(sessionId);
			        		if(commonResult.getErrorCode().equals(0)){
			        			log.info("海康心跳检测正常");
			        		}else if(commonResult.getErrorCode().equals(10005)){
			        			log.info("海康登陆超时，重新登陆");
			        			login();
			        		}else{
			        			log.info("海康心跳检测异常，错误码:{},尝试重新登陆",commonResult.getErrorCode());
			        			login();
			        		}
						}}, 0, 60, TimeUnit.SECONDS);
	        	}
	        }else{
	        	log.error("海康服务登陆失败，请检查配置,错误码:{}",loginResult.getErrorCode());
	        }
		} catch (MalformedURLException e) {
			log.error("海康服务登陆失败，请检查配置",e);
		} 
	}

	public static CmsServicePortType getPort() {
		return port;
	}

	public static String getSessionId() {
		return sessionId;
	}
	
}
