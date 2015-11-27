package cn.zhny.environment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Model;

@Entity
@Table(name="record")
public class Record extends Model implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final Find<Long,Record> find = new Find<Long,Record>(){};
	
	@Column(name="ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name="rTime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date rTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sID")
	private Stations station;
	
	@Column(name="d0",precision=20,scale=5)
	private BigDecimal d0;
	
	@Column(name="d1",precision=20,scale=5)
	private BigDecimal d1;
	
	@Column(name="d2",precision=20,scale=5)
	private BigDecimal d2;
	
	@Column(name="d3",precision=20,scale=5)
	private BigDecimal d3;
	
	@Column(name="d4",precision=20,scale=5)
	private BigDecimal d4;
	
	@Column(name="d5",precision=20,scale=5)
	private BigDecimal d5;
	
	@Column(name="d6",precision=20,scale=5)
	private BigDecimal d6;
	
	@Column(name="d7",precision=20,scale=5)
	private BigDecimal d7;
	
	@Column(name="d8",precision=20,scale=5)
	private BigDecimal d8;
	
	@Column(name="d9",precision=20,scale=5)
	private BigDecimal d9;
	
	@Column(name="d10",precision=20,scale=5)
	private BigDecimal d10;
	
	@Column(name="d11",precision=20,scale=5)
	private BigDecimal d11;
	
	@Column(name="d12",precision=20,scale=5)
	private BigDecimal d12;
	
	@Column(name="d13",precision=20,scale=5)
	private BigDecimal d13;
	
	@Column(name="d14",precision=20,scale=5)
	private BigDecimal d14;
	
	@Column(name="d15",precision=20,scale=5)
	private BigDecimal d15;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Date getrTime() {
		return rTime;
	}

	public void setrTime(Date rTime) {
		this.rTime = rTime;
	}

	
	public Stations getStation() {
		return station;
	}

	public void setStation(Stations station) {
		this.station = station;
	}

	public BigDecimal getD0() {
		return d0;
	}

	public void setD0(BigDecimal d0) {
		this.d0 = d0;
	}

	public BigDecimal getD1() {
		return d1;
	}

	public void setD1(BigDecimal d1) {
		this.d1 = d1;
	}

	public BigDecimal getD2() {
		return d2;
	}

	public void setD2(BigDecimal d2) {
		this.d2 = d2;
	}

	public BigDecimal getD3() {
		return d3;
	}

	public void setD3(BigDecimal d3) {
		this.d3 = d3;
	}

	public BigDecimal getD4() {
		return d4;
	}

	public void setD4(BigDecimal d4) {
		this.d4 = d4;
	}

	public BigDecimal getD5() {
		return d5;
	}

	public void setD5(BigDecimal d5) {
		this.d5 = d5;
	}

	public BigDecimal getD6() {
		return d6;
	}

	public void setD6(BigDecimal d6) {
		this.d6 = d6;
	}

	public BigDecimal getD7() {
		return d7;
	}

	public void setD7(BigDecimal d7) {
		this.d7 = d7;
	}

	public BigDecimal getD8() {
		return d8;
	}

	public void setD8(BigDecimal d8) {
		this.d8 = d8;
	}

	public BigDecimal getD9() {
		return d9;
	}

	public void setD9(BigDecimal d9) {
		this.d9 = d9;
	}

	public BigDecimal getD10() {
		return d10;
	}

	public void setD10(BigDecimal d10) {
		this.d10 = d10;
	}

	public BigDecimal getD11() {
		return d11;
	}

	public void setD11(BigDecimal d11) {
		this.d11 = d11;
	}

	public BigDecimal getD12() {
		return d12;
	}

	public void setD12(BigDecimal d12) {
		this.d12 = d12;
	}

	public BigDecimal getD13() {
		return d13;
	}

	public void setD13(BigDecimal d13) {
		this.d13 = d13;
	}

	public BigDecimal getD14() {
		return d14;
	}

	public void setD14(BigDecimal d14) {
		this.d14 = d14;
	}

	public BigDecimal getD15() {
		return d15;
	}

	public void setD15(BigDecimal d15) {
		this.d15 = d15;
	}
	
	
	
}
