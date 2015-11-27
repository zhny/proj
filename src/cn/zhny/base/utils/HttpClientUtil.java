package cn.zhny.base.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpclient工具类
 * 
 * @author app
 *
 */
public class HttpClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	private static final int MAX_SIZE = 102400;//http response 最大100K 
	//get请求
	public static String doGet(String url) throws URISyntaxException{
		String res = null;
		HttpClient client = HttpConnManager.getHttpClient();
		HttpClientParams params = client.getParams();
//		params.setConnectionManagerTimeout(10000);
		params.setContentCharset("utf-8");
		params.setHttpElementCharset("utf-8");
		params.setSoTimeout(20000);
		GetMethod get = new GetMethod(url);
		
		if(log.isInfoEnabled()){
			log.info("url:"+url);
		}
		int status = 0;
		try {
			status = client.executeMethod(get);
			if(status == HttpStatus.SC_OK){
				res = getResponseAsString(get.getResponseBodyAsStream());
			}else{
				log.error("response error with response code:"+status);
			}
		}catch (IOException e) {
			log.error(e.getMessage(), e);
		}finally{
			if(get != null){
				get.releaseConnection();
			}
		}
		return res;
	}
	/**
	 * Post请求
	 * @param url 请求地址
	 * @param body 请求消息体
	 * @param content_type 对应HttpHeader:Content-Type
	 * @return 结果字符串
	 * @throws URISyntaxException
	 */
	public static String doPost(String url,RequestEntity body,Map<String,String> headers) throws URISyntaxException{
		String res = null;
		HttpClient client = HttpConnManager.getHttpClient();
		PostMethod post = new PostMethod(url);
		if(log.isInfoEnabled()){
			log.info("url:"+url);
		}
		if(null != headers && !headers.isEmpty()){
			Iterator<Map.Entry<String, String>> itr = headers.entrySet().iterator();
			while(itr.hasNext()){
				Map.Entry<String, String> ent = itr.next();
				post.addRequestHeader(ent.getKey(), ent.getValue());
			}
		}
		if(null != body){
			post.setRequestEntity(body);
		}
		int status = 0;
		try {
			status = client.executeMethod(post);
			if(status == HttpStatus.SC_OK){
				res = getResponseAsString(post.getResponseBodyAsStream());
			}else{
				log.error("response error with response code:"+status);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}finally{
			if(post != null){
				post.releaseConnection();
			}
		}
				
		return res;		
	}
	static String getResponseAsString(InputStream is){
		String res = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int total = 0,len = 0;
		try {
			while((len = is.read(buf))>0){
				total +=len;
				if(total>MAX_SIZE){
					throw new IllegalStateException("response body size over 100KB");
				}
				baos.write(buf, 0, len);
			}
			is.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		try {
			res = new String(baos.toByteArray(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return res;
	}
	public static void destoryHttpConnectionManager(){
		log.info("begin destoryHttpConnectionManager");
		HttpConnManager.shutdown();
	}
	public static HttpClient getHttpClient(){
		return HttpConnManager.getHttpClient();
	}
	/**
	 * http连接管理器
	 * @author app
	 *
	 */
	static class HttpConnManager{
	    private static HttpConnectionManager  connectionManager;  
	    
	    /** 
	     * 最大连接数 
	     */  
	    public final static int MAX_TOTAL_CONNECTIONS = 200;  
	    /** 
	     * 获取连接的最大等待时间 
	     */  
	    public final static int WAIT_TIMEOUT = 10000;  
	    /** 
	     * 每个路由最大连接数 
	     */  
	    public final static int MAX_ROUTE_CONNECTIONS = 30;  
	    /** 
	     * 连接超时时间 
	     */  
	    public final static int CONNECT_TIMEOUT = 10000;  
	    /** 
	     * 读取超时时间 
	     */  
	    public final static int READ_TIMEOUT = 30000;  
	    static {  
	    	connectionManager = new MultiThreadedHttpConnectionManager();
	    	HttpConnectionManagerParams params = connectionManager.getParams();
	    	params.setConnectionTimeout(CONNECT_TIMEOUT);
	    	params.setDefaultMaxConnectionsPerHost(MAX_ROUTE_CONNECTIONS);
	    	params.setMaxTotalConnections(MAX_TOTAL_CONNECTIONS);
//	    	params.setReceiveBufferSize(1024000);
	    	params.setSoTimeout(READ_TIMEOUT);
	    }  
	  
	    public static HttpClient getHttpClient() {  
	        return new HttpClient(connectionManager);  
	    }
	    public static void shutdown(){
	    	if(connectionManager != null){
	    		MultiThreadedHttpConnectionManager mm = (MultiThreadedHttpConnectionManager)connectionManager;
	    		mm.shutdown();
	    		connectionManager = null;
	    		log.info("connectionManager has been shutdown");
	    	}
	    }
	}
//	 static class HttpResponseHandler implements ResponseHandler<String> {
//
//         public String handleResponse(
//                 final HttpResponse response) throws ClientProtocolException, IOException {
//             int status = response.getStatusLine().getStatusCode();
//             if (status >= 200 && status < 300) {
//                 HttpEntity entity = response.getEntity();
//                 return entity != null ? EntityUtils.toString(entity) : null;
//             } else {
//                 throw new ClientProtocolException("Unexpected response status: " + status);
//             }
//         }
//
//     }
	 
}
