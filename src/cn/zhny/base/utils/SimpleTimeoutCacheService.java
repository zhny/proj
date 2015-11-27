package cn.zhny.base.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

/**
 * 简单按时间失效的缓存实现
 * @author app
 *
 */
public class SimpleTimeoutCacheService {
	private static Logger log = Logger.getLogger(SimpleTimeoutCacheService.class);

	private static volatile SimpleTimeoutCacheService instance;
	private static int Reasonable_size = 200;
	private static Map<String,CacheEntity<?>> cache = new ConcurrentHashMap<String, CacheEntity<?>>(Reasonable_size);
	private static AtomicBoolean runing = new AtomicBoolean(false);
	
	private SimpleTimeoutCacheService(){
	}
	
	public static SimpleTimeoutCacheService getInstance(){
		if(instance == null){
			synchronized (SimpleTimeoutCacheService.class) {
				if(instance == null){
					instance = new SimpleTimeoutCacheService();
					instance.init();
				}
			}
		}
		return instance;
	}
	
	private void init(){
		if(!runing.get()){
			long time = System.currentTimeMillis();
			log.info("begin start clear thread at:"+time);
			runing.compareAndSet(false, true);
			Thread clear = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						if(cache != null && cache.size()>0){
							Iterator<Map.Entry<String, CacheEntity<?>>> itr = cache.entrySet().iterator();
							while(itr.hasNext()){
								Map.Entry<String, CacheEntity<?>> entry = itr.next();
								if(entry.getValue().getExpireTime() < System.currentTimeMillis()){
									CacheEntity<?> ce = cache.remove(entry.getKey());
									if(log.isInfoEnabled()){
										log.info("remove:"+(ce.getData() == null?ce.getData().toString():"no data"));								
									}
								}
							}
						}
						if(log.isInfoEnabled()){
							log.info("after clear");
						}
						try {
							TimeUnit.MINUTES.sleep(2);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
				}
				}
			});
			clear.start();
			log.info("clear thread started,use:"+(System.currentTimeMillis()-time)+" mills");
		}
	}
//	public void shutdown(){
//		runing.set(false);
//	}
	
	public Object get(String key){
		CacheEntity<?> entity = (cache.get(key)==null?null:cache.get(key));
		if(entity != null){
			if(System.currentTimeMillis()<=entity.getExpireTime()){
				return entity.getData();
			}
		}
		return null;
	}
	public void put(String key,Object data){
		put(key, data, System.currentTimeMillis()+(24*60*60*1000), false);
	}
	public void put(String key,Object data,long expireTime){
		put(key, data, expireTime, false);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void put(String key,Object data,long expireTime,boolean canRemove){
		if(cache.size()>=Reasonable_size){
			shrink();//尝试收缩缓存
		}
		CacheEntity<?> entity = new CacheEntity(data,expireTime,canRemove);
		cache.put(key, entity);
	}
	public Object remove(String key){
		CacheEntity<?> ce = cache.remove(key);
		return (ce == null?null:ce.getData());
	}
	private void shrink(){
		Iterator<Map.Entry<String, CacheEntity<?>>> itr = cache.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String, CacheEntity<?>> entry = itr.next();
			if(entry.getValue().isCanRemove()){
				CacheEntity<?> ce = cache.remove(entry.getKey());
				if(log.isInfoEnabled()){
					log.info("remove:"+(ce.getData() == null?ce.getData().toString():"no data"));								
				}
			}
		}
	}
	class CacheEntity<T>{
		//数据值
		private T data;
		//过期时间
		private long expireTime;
		//缓存数量不足时可否优先删除
		private boolean canRemove;
		
		public CacheEntity(T data,long expireTime,boolean canRemove){
			this.data = data;
			this.expireTime = expireTime;
			this.canRemove = canRemove;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public long getExpireTime() {
			return expireTime;
		}
		public void setExpireTime(long expireTime) {
			this.expireTime = expireTime;
		}
		public boolean isCanRemove() {
			return canRemove;
		}
		public void setCanRemove(boolean canRemove) {
			this.canRemove = canRemove;
		}
		
		
	}
}
