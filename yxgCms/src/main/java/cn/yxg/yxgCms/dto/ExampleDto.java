/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDto.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.dto
 * Author:  Archetype Generate
 */

package cn.yxg.yxgAppServer.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class ExampleDto {

	@NotNull
	/** Example ID. */
	private Integer id;

	@NotBlank(message="用户名不能为空")
	/** Example name. */
	private String name;

	/** Example content. */
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
