/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDto.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dto
 * Author:  Archetype Generate
 */

package cn.yxg.yxgCms.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class CourseFilterInputDto {

	@NotNull(message="总分类不能为空")
	private Integer category;
	
	private Integer subCategory;
	
	private Integer age;
	
	private Integer purpose;
	
	private Integer difficulty;
	
	private Integer price;
	
	private Integer borderId;
	
	private Integer number;

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getBorderId() {
		return borderId;
	}

	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
}
