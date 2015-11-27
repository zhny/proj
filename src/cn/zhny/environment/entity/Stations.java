package cn.zhny.environment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.avaje.ebean.Model;

@Entity
@Table(name="stations")
public class Stations  extends Model implements Serializable{
	
	public static final long serialVersionUID = 1L;

	public static final Find<Long,Stations> find = new Find<Long,Stations>(){};
	
	@Column(name="ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name="sName")
	@Length(max=64)
	private String sName;
	
	@Column(name="iStatus")
	private Integer iStatus;
	
	@Column(name="sid")
	private Integer sid;
	
	@Lob
	private String sProtocol; 
	
	@Column(name="timeOut")
	private Integer timeOut;
	
	@Column(name="retryNum")
	private Integer retryNum;
	
	@Column(name="sNote")
	private String sNote;
	
	@Column(name="sLocation")
	private String sLocation;
	
	@Column(name="sSvrInf")
	@Lob
	private String sSvrInf;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Integer getiStatus() {
		return iStatus;
	}

	public void setiStatus(Integer iStatus) {
		this.iStatus = iStatus;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getsProtocol() {
		return sProtocol;
	}

	public void setsProtocol(String sProtocol) {
		this.sProtocol = sProtocol;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public Integer getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(Integer retryNum) {
		this.retryNum = retryNum;
	}

	public String getsNote() {
		return sNote;
	}

	public void setsNote(String sNote) {
		this.sNote = sNote;
	}

	public String getsLocation() {
		return sLocation;
	}

	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}

	public String getsSvrInf() {
		return sSvrInf;
	}

	public void setsSvrInf(String sSvrInf) {
		this.sSvrInf = sSvrInf;
	}
	
	
}
