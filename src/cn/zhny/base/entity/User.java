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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 用户实体
 * @author zhny
 *
 */
@Entity
@XmlRootElement
public class User extends Model implements Serializable{
	
	public static final long serialVersionUID = 1L;
	public static final Find<Long,User> find = new Find<Long,User>(){};
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateUpdate  = new Date();			//数据更新日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreate = new Date();			//数据创建日期
	
	@NotEmpty(message="用户名不能为空")
	@Size(min=5,max=20,message="用户名为5到20位字符")
	@JoinColumn(unique=true)
	private String phone;
	
	@JsonIgnore
	@NotEmpty(message="用户密码不能为空")
	private String password;
	
	@JsonIgnore
	@Size(min=1,max=2)
	private String isInternal;						//是否内部管理账户，如果是，则设置为Y即可		
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;	//上次登录时间
	
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity = Role.class)
	@JoinTable(name = "user_roles", joinColumns ={@JoinColumn(name = "user_id" )}, inverseJoinColumns = { @JoinColumn(name = "role_id")})
	private List<Role> roles=new ArrayList<Role>();			//用户角色集合
	
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public String getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(String isInternal) {
		this.isInternal = isInternal;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
	
}
