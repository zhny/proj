package cn.zhny.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Model;

/**
 * 系统参数配置
 * @author zhny
 *
 */
@Entity
public class SysConf extends Model implements Serializable{

	public static final long serialVersionUID = 1L;
	
	public static final Find<Long,SysConf> find=new Find<Long, SysConf>() {};
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateUpdate  = new Date();			//数据更新日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreate = new Date();			//数据创建日期
	
	@Enumerated(EnumType.STRING)
	private Constraint.StatusEnum status=Constraint.StatusEnum.Y;						//有效为Y ，被删除 标记为 D
	
	@Length(max=20)
	private String name;
	
	@Length(max=20)
	private String code;
	
	@Length(max=20)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
