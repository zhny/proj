package cn.zhny.environment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.avaje.ebean.Model;

@Entity
@Table(name="channel")
public class Channel extends Model implements Serializable{
	
	public static final long serialVersionUID = 1L;
	public static final Find<Long,Channel> find=new Find<Long, Channel>() {};
	
	@Column(name="ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne
	@JoinColumn(name="sID")
	private Stations station;
	
	@Column(name="num")
	private Integer num;
	
	@Column(name="cName")
	@Length(max=30)
	private String cName;
	
	@Column(name="mName")
	@Length(max=30)
	private String mName;
	
	@Column(name="RAD")
	private Integer RAD;
	
	@Column(name="RAM")
	private Integer RAM;
	
	@Column(name="mID")
	private Integer mID;
	
	@Column(name="Accuracy")
	private Integer Accuracy;
	
	@Column(name="maxVal")
	private Integer maxVal;
	
	@Column(name="minVal")
	private Integer minVal;
	
	@Column(name="DST")
	private Integer DST;
	
	@Column(name="sNote")
	private String sNote;
	
	@Column(name="CorrectT")
	private Integer CorrectT;
	
	@Column(name="CorrectVi")
	private Integer CorrectVi;
	
	@Column(name="CorrectVs")
	private String CorrectVs;
	
	@Column(name="ord")
	private Integer ord;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
	
	public Stations getStation() {
		return station;
	}

	public void setStation(Stations station) {
		this.station = station;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Integer getRAD() {
		return RAD;
	}

	public void setRAD(Integer rAD) {
		RAD = rAD;
	}

	public Integer getRAM() {
		return RAM;
	}

	public void setRAM(Integer rAM) {
		RAM = rAM;
	}

	public Integer getmID() {
		return mID;
	}

	public void setmID(Integer mID) {
		this.mID = mID;
	}

	public Integer getAccuracy() {
		return Accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		Accuracy = accuracy;
	}

	public Integer getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	public Integer getMinVal() {
		return minVal;
	}

	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

	public Integer getDST() {
		return DST;
	}

	public void setDST(Integer dST) {
		DST = dST;
	}

	public String getsNote() {
		return sNote;
	}

	public void setsNote(String sNote) {
		this.sNote = sNote;
	}

	public Integer getCorrectT() {
		return CorrectT;
	}

	public void setCorrectT(Integer correctT) {
		CorrectT = correctT;
	}

	public Integer getCorrectVi() {
		return CorrectVi;
	}

	public void setCorrectVi(Integer correctVi) {
		CorrectVi = correctVi;
	}

	public String getCorrectVs() {
		return CorrectVs;
	}

	public void setCorrectVs(String correctVs) {
		CorrectVs = correctVs;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}
	
	
	
}
