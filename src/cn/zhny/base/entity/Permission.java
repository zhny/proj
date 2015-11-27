package cn.zhny.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 权限
 * @author zhny
 *
 */
@Entity
public class Permission extends Model implements Serializable{

	public static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateUpdate  = new Date();			//数据更新日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreate = new Date();			//数据创建日期
	
	private String name;						//权限名
	
	private String expression;					//权限表达式
	
	@Enumerated(EnumType.STRING)
	private Constraint.PermissionTypeEnum permissionType;			
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Permission parent;		//父类权限
	
	private transient List<Permission> children;
	
	private String url;				
	
	
	@Length(max=20)
	@Enumerated(EnumType.STRING)
	private Constraint.StatusEnum status=Constraint.StatusEnum.Y;						//有效为Y ，被删除 标记为 D
	
	
	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Constraint.PermissionTypeEnum getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Constraint.PermissionTypeEnum permissionType) {
		this.permissionType = permissionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}
	
	
}
