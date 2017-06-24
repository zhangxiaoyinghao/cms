/* @(#) DictCatalog.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.entity
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "yxg_dict_catalog")
@JsonIgnoreProperties(value = "catalogTemplate")
public class DictCatalog {

	/** 主键。 */
	@Id
	@GeneratedValue
	private Integer id;

	/** 编目模板ID。 */
	@ManyToOne
	@JoinColumn(name = "template_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CatalogTemplate catalogTemplate;

	/** 编目项ID。 */
	@ManyToOne
	@JoinColumn(name = "item_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CatalogItem catalogItem;

	/** 是否必填。 */
	private boolean required;
	
//	/** 是否为整切或者分切 */
//	private int type;
		
		/** 是否可重复*/
//		@Column(name = "repeatable")
		private boolean repeatable;
		
		/** 是否可修改*/
//		@Column(name = "modifiable")
		private boolean modifiable;
	
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

	/**
	 * @return the catalogTemplate
	 */
	public CatalogTemplate getCatalogTemplate() {
		return catalogTemplate;
	}

	/**
	 * @param catalogTemplate the catalogTemplate to set
	 */
	public void setCatalogTemplate(CatalogTemplate catalogTemplate) {
		this.catalogTemplate = catalogTemplate;
	}

	/**
	 * @return the catalogItem
	 */
	public CatalogItem getCatalogItem() {
		return catalogItem;
	}

	/**
	 * @param catalogItem the catalogItem to set
	 */
	public void setCatalogItem(CatalogItem catalogItem) {
		this.catalogItem = catalogItem;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

//	/**
//	 * @return the type
//	 */
//	public int getType() {
//		return type;
//	}
//
//	/**
//	 * @param type the type to set
//	 */
//	public void setType(int type) {
//		this.type = type;
//	}
	
}
