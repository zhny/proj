package cn.zhny.base.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhny.base.entity.Constraint;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import com.avaje.ebean.PagedList;

/**
 * 抽象服务，包含对象的基本增删查改操作
 * 
 * @author zhny
 *
 * @param <T>
 */
@Service
public abstract class AbstractService<T> {
	private static Logger log = LoggerFactory.getLogger(AbstractService.class);;

	public abstract Class<T> getClazz();

	public final static Integer PAGE_SIZE=10;

	@Autowired
	private EbeanServer ebeanServer;
	
	/**
	 * 根据主键获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		if (id == null)
			return null;
		return ebeanServer.find(getClazz()).where().eq("id", id).ne("status", Constraint.StatusEnum.D).findUnique();
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> list() {
		return ebeanServer.find(getClazz()).where().ne("status", Constraint.StatusEnum.D).findList();
	}

	/**
	 * 创建实体
	 * 
	 * @param obj
	 * @param result
	 * @return
	 */
	public T create(T obj) {
		ebeanServer.save(obj);
		return obj;
	}

	public T update(T obj) {
		ebeanServer.update(obj);
		return obj;
	}
	
	public T delete(T obj) {
		ebeanServer.delete(obj);
		return obj;
	}
	

	public boolean isNullOrEmpty(Object obj) {
		if (obj instanceof String) {
			return (obj == null || "".equals(obj.toString().trim())) ? true
					: false;
		} else {
			return obj == null;
		}
	}

	private static final String REG_YMD = "\\d{4}\\-\\d{2}\\-\\d{2}";
	private static final String REG_YMDHMS = "\\d{4}\\-\\d{2}\\-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}";
	private static final SimpleDateFormat shortFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat longFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public Date parse(String source) {
		Date date = null;
		try {
			if (source.matches(REG_YMDHMS)) {
				date = longFormat.parse(source);
			} else if (source.matches(REG_YMD)) {
				date = shortFormat.parse(source);
			}
		} catch (ParseException e) {
			log.error("日期转换失败", e);
		}
		return date;
	}

	/**
	 * 硬批量删除对象，以逗号间隔
	 * 
	 * @param ids
	 * @return
	 */
	public void hardDeleteBatchObj(String ids) {
		if (isNullOrEmpty(ids)) {
			return;
		}
		String[] idArray = ids.split(";");
		for (int i = 0; i < idArray.length; i++) {
			if (!isNullOrEmpty(idArray[i])) {
				T obj = ebeanServer.find(getClazz(),Long.parseLong(idArray[i]));
				if (obj != null) {
					ebeanServer.delete(obj);
				}
			}
		}
	}
	
	public PagedList<T> findPage(Integer cp,Integer pageSize){
		PagedList<T> page=findPage(cp,pageSize,null,null);
		return page;
	}
	
	/**
	 * 分页查询
	 * @param cp
	 * @param pageSize
	 * @param exp
	 * @return
	 */
	public PagedList<T> findPage(Integer cp,Integer pageSize,Expression exp){
		PagedList<T> page=findPage(cp,pageSize,exp,null);
		return page;
	}
	
	/**
	 * 分页查询
	 * @param cp
	 * @param pageSize
	 * @param exp
	 * @param orderBy
	 * @return
	 */
	public PagedList<T> findPage(Integer cp,Integer pageSize,Expression exp,String orderBy){
		if (cp == null){
			cp = 0;
		}else{
			cp = cp-1;
		}
		if(pageSize==null)pageSize=PAGE_SIZE;
		if(exp == null){
			exp = Expr.ne("status",Constraint.StatusEnum.D);
		}
		if(orderBy==null){
			orderBy="id desc";
		}
		PagedList<T> page = ebeanServer.find(getClazz()).where(exp).orderBy(orderBy).findPagedList(cp, pageSize);

		return page;
	}
	

	
	/**
	 * 软删除对象
	 * 
	 * @param ids
	 * @return
	 */
	public void softDeleteBatchObj(String ids) {
		if (isNullOrEmpty(ids)) {
			return;
		}
		String[] idArray = ids.split(";");
		for (int i = 0; i < idArray.length; i++) {
			if (!isNullOrEmpty(idArray[i])) {
				ebeanServer.createSqlUpdate("update "+StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(getClazz().getSimpleName()), "_").toLowerCase()+" set status='D' where id=:id").setParameter("id", idArray[i]).execute();
			}
		}
	}
	
	
	/**
	 * 根据条件查询对象列表
	 * @param expr
	 * @return
	 */
	public List<T> getObjListByExpression(Expression expr) {
		return ebeanServer.find(getClazz()).where(expr).findList();
	}
}
