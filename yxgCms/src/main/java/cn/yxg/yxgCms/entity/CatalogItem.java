/* @(#) CatalogItem.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.entity
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.entity;


import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.yxg.yxgCms.enumeration.CatalogItemCategory;
import cn.yxg.yxgCms.enumeration.CatalogItemType;

import java.util.Date;
import java.util.List;

/**
 * 编目项实体类。
 *
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "yxg_catalog_item")
@JsonIgnoreProperties("dictCatalog")
public class CatalogItem {

	/** 编目项ID。 */
	@Id
	@GeneratedValue
	private Integer id;

	/** 编目项Key名称，全局唯一。 */
	@Column(name = "key_name")
	private String key;

	/** 编目项名称。 */
	private String name;

	/**
	 * 编目项类型。
	 *
	 * @see cn.videoworks.videofact.enumeration.CatalogItemType
	 */
	@Enumerated(EnumType.ORDINAL)
	private CatalogItemType type;

	
	@Transient
	private Integer contentType;
	
	/**
	 * 编目项类型名称
	 */
	@Transient
	private String catalogItemTypeName;
	

	/** 编目项内容。 */
	private String content;
	
	/** 编目项默认值 */
	@Column(name = "default_value")
	private String defaultValue;

	/** 编目项描述。 */
	private String description;

	/** 创建时间。 */
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
	private Date createTime;

	/** 最后更新时间。 */
	@Column(name = "update_time", insertable = false, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
	private Date updateTime;
	
	@OneToMany(mappedBy = "catalogItem",fetch = FetchType.EAGER,cascade=CascadeType.REMOVE)
	@Fetch(FetchMode.SELECT)
	private List<DictCatalog> dictCatalog;

	/**
	 * 编目项分类。
	 *
	 */
	@Enumerated(EnumType.ORDINAL)
	private CatalogItemCategory category;
	
	/**
	 * 编目项分类。
	 *
	 */
	@Column(name = "skiped")
	private boolean skiped;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CatalogItemType getType() {
		return type;
	}

	public void setType(CatalogItemType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	 * @return the dictCatalog
	 */
	public List<DictCatalog> getDictCatalog() {
		return dictCatalog;
	}

	/**
	 * @param dictCatalog the dictCatalog to set
	 */
	public void setDictCatalog(List<DictCatalog> dictCatalog) {
		this.dictCatalog = dictCatalog;
	}

	/**
	 * Returns the value of the field called 'catalogItemTypeName'.
	 * @return Returns the catalogItemTypeName.
	 */
	public String getCatalogItemTypeName() {
		return this.type.getName();
	}

	/**
	 * Sets the field called 'catalogItemTypeName' to the given value.
	 * @param catalogItemTypeName The catalogItemTypeName to set.
	 */
	public void setCatalogItemTypeName(String catalogItemTypeName) {
		this.catalogItemTypeName = catalogItemTypeName;
	}

	/**
	 * Returns the value of the field called 'contentType'.
	 * @return Returns the contentType.
	 */
	public Integer getContentType() {
		return this.type.getIndex();
	}

	/**
	 * Sets the field called 'contentType' to the given value.
	 * @param contentType The contentType to set.
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	/**
	 * Returns the value of the field called 'category'.
	 * @return Returns the category.
	 */
	public CatalogItemCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the field called 'category' to the given value.
	 * @param category The category to set.
	 */
	public void setCategory(CatalogItemCategory category) {
		this.category = category;
	}

	/**
	 * Returns the value of the field called 'skiped'.
	 * @return Returns the skiped.
	 */
	public boolean isSkiped() {
		return this.skiped;
	}

	/**
	 * Sets the field called 'skiped' to the given value.
	 * @param skiped The skiped to set.
	 */
	public void setSkiped(boolean skiped) {
		this.skiped = skiped;
	}

	/**
	 * Returns the value of the field called 'defaultValue'.
	 * @return Returns the defaultValue.
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * Sets the field called 'defaultValue' to the given value.
	 * @param defaultValue The defaultValue to set.
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
