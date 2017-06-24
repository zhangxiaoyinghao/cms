/* @(#) CatalogTemplate.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.entity
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 编目模板实体类。
 *
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "yxg_catalog_template")
public class CatalogTemplate {

	/** 编目模板ID。 */
	@Id
	@GeneratedValue
	private int id;

	/** 编目模板名称。 */
	private String name;

	/** 是否启用。 */
	private boolean enable = true;

	/** 模板创建时间。 */
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
	private Date createTime;

	/** 模板最后更新时间。 */
	@Column(name = "update_time", insertable = false, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
	private Date updateTime;
	
	/** 编目模版对应影射表1对多关系 */
	@OneToMany(mappedBy = "catalogTemplate",fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<DictCatalog> dictCatalogs;
    
	@Transient
	private List<Map<String,Object>> catalogs;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the dictCatalogs
	 */
	public List<DictCatalog> getDictCatalogs() {
		return dictCatalogs;
	}

	/**
	 * @param dictCatalogs the dictCatalogs to set
	 */
	public void setDictCatalogs(List<DictCatalog> dictCatalogs) {
		this.dictCatalogs = dictCatalogs;
	}

	/**
	 * Returns the value of the field called 'catalogs'.
	 * @return Returns the catalogs.
	 */
	public List<Map<String, Object>> getCatalogs() {
		return this.catalogs;
	}

	/**
	 * Sets the field called 'catalogs' to the given value.
	 * @param catalogs The catalogs to set.
	 */
	public void setCatalogs(List<Map<String, Object>> catalogs) {
		this.catalogs = catalogs;
	}


}
