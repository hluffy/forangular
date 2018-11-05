package com.dk.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name="wifilocation")
@Table(name = "wifilocation")
@EntityListeners(AuditingEntityListener.class)
public class WifiLocation implements Serializable{

	@Id
	@GenericGenerator(name="wifilocationid",strategy = "uuid")
	@GeneratedValue(generator = "wifilocationid")
	@Column(name="ID")
	private String id;

	@Column(name = "IMEI")
	private String imei;

	@Column(name = "XLOC")
	private double xloc;

	@Column(name = "YLOC")
	private double yloc;

	@CreatedDate
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@LastModifiedDate
	@Column(name = "LAST_MODIFY_DATE")
	private Date lastModifyDate;

	@Column(name = "INFO")
	private String info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public double getXloc() {
		return xloc;
	}

	public void setXloc(double xloc) {
		this.xloc = xloc;
	}

	public double getYloc() {
		return yloc;
	}

	public void setYloc(double yloc) {
		this.yloc = yloc;
	}

	public String getCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return sdf.format(this.createDate);
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return sdf.format(this.lastModifyDate);
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
