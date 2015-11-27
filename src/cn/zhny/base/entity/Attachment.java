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


@Entity
public class Attachment extends Model implements Serializable{
	
	public static final long serialVersionUID = 1L;

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateUpdate  = new Date();			//数据更新日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreate = new Date();			//数据创建日期
	
	@Length(max=32)
	private String ext;		//扩展名
	
	@Length(max=128)
	private String title;	//文件标题
	
	@Length(max=32)
	private String size;	//文件大小
	
	@Length(max=128)
	private String type;    //文件媒体属性
	
	@Length(max=255)
	private String uri;	//文件路径

	@Enumerated(EnumType.STRING)
	private Constraint.StatusEnum status=Constraint.StatusEnum.Y;						//有效为Y ，被删除 标记为 D
	

	public Constraint.StatusEnum getStatus() {
		return status;
	}

	public void setStatus(Constraint.StatusEnum status) {
		this.status = status;
	}

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


	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
