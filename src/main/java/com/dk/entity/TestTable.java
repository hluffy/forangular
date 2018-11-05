package com.dk.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name="testable")
@Table(name = "testable")
@EntityListeners(AuditingEntityListener.class)
public class TestTable implements Serializable{
	@Id
	@GenericGenerator(name="testableid",strategy = "uuid")
	@GeneratedValue(generator = "testableid")
	@Column(name="ID")
	private String id;

	@Column(name="RENDERING_ENGINE")
	private String renderingEngine;

	@Column(name="BROWSER")
	private String browser;

	@Column(name="PLATFORMS")
	private String platforms;

	@Column(name="ENGINE_VERSION")
	private String engineVersion;

	@Column(name="CSS_GRADE")
	private String cssGrade;

	@CreatedDate
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@LastModifiedDate
	@Column(name="LAST_MODIFY_DATE")
	private Date lastModifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRenderingEngine() {
		return renderingEngine;
	}

	public void setRenderingEngine(String renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getEngineVersion() {
		return engineVersion;
	}

	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}

	public String getCssGrade() {
		return cssGrade;
	}

	public void setCssGrade(String cssGrade) {
		this.cssGrade = cssGrade;
	}

	public String getCreateDate() {
		if(!StringUtils.isEmpty(createDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(createDate);
		}
		return null;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyDate() {
		if(!StringUtils.isEmpty(lastModifyDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(lastModifyDate);
		}
		return null;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
}
