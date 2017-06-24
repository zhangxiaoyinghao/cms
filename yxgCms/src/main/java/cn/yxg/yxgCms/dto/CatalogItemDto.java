/* @(#) CatalogItemDto.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.dto
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.yxg.yxgCms.entity.CatalogItem;
import cn.yxg.yxgCms.enumeration.CatalogItemCategory;
import cn.yxg.yxgCms.enumeration.CatalogItemType;

/**
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class CatalogItemDto  {

	/** 编目项ID。 */
	private int id;

	/** 编目项Key名称，全局唯一。 */
	@NotNull
	@Size(min = 1, max = 50)
	private String key;

	/** 编目项名称。 */
	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	/**
	 * 编目项类型。
	 *
	 * @see cn.videoworks.videofact.enumeration.CatalogItemType
	 */
	@NotNull
	private CatalogItemType type;

	/** 编目项内容。 */
	private String content;
	
	/**默认值**/
	private String defaultValue;

	/** 编目项描述。 */
	private String description;
	

	/** 编目项分类。 */
	@NotNull
	private CatalogItemCategory category;
	
	/** 是否换行。 */
	@NotNull
	private boolean skiped;
	

	public CatalogItem convert() {
		CatalogItem catalogItem = new CatalogItem();
		catalogItem.setId(id);
		catalogItem.setKey(key);
		catalogItem.setName(name);
		catalogItem.setType(type);
		catalogItem.setContent(content);
		catalogItem.setDescription(description);
		catalogItem.setCategory(category);
		catalogItem.setSkiped(skiped);
		catalogItem.setDefaultValue(defaultValue);
		return catalogItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public CatalogItemCategory getCategory() {
		return category;
	}

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
