/* @(#) CatalogItemType.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.enumeration
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.enumeration;

/**
 * 编目项类型玫举。
 * 
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum CatalogItemType {

	// /** 多行文本框 */
	// TEXT_AREA,
	//
	// /** 下拉框 */
	// SELECT,
	//
	// /** 单选框 */
	// RADIO,
	//
	// /** 复选框 */
	// CHECKBOX,
	//
	// /** 单行文本框 */
	// TEXT_FIELD,
	//
	// /** 表格 */
	// TABLE,
	//
	// /** 时码输入框 */
	// TIME_CODE,
	//
	// /** 日期输入框 */
	// DATETIME,
	//
	// /** 图片选择框 */
	// IMAGE;
	TEXT_AREA("多行文本框", 0), SELECT("下拉框", 1), RADIO("单选框", 2), CHECKBOX("复选框", 3), TEXT_FIELD(
			"单行文本框", 4), TABLE("表格", 5), TIME_CODE("时码输入框", 6), DATETIME(
			"日期输入框", 7), IMAGE("图片选择框", 8),TAG("标签",9),SELECTS("复选下拉框",10),TREES("复选树",11);

	private String name;

	private int index;

	// 构造方法
	private CatalogItemType(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the field called 'name' to the given value.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the field called 'index'.
	 * @return Returns the index.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Sets the field called 'index' to the given value.
	 * @param index The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
