package cn.zhny.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Model;


/**
 * 角色
 * @author zhny
 *
 */
@Entity
public class Role extends Model implements Serializable{

	public static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateUpdate  = new Date();			//数据更新日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreate = new Date();			//数据创建日期
	
	private String name;			//角色名
	
	private String description;		//角色描述
	
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity = Permission.class)
	@JoinTable(name = "role_permissions", joinColumns ={@JoinColumn(name = "role_id" )}, inverseJoinColumns = { @JoinColumn(name = "permission_id")})
	private List<Permission> permissions=new ArrayList<Permission>();				//权限集合

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
	
}
