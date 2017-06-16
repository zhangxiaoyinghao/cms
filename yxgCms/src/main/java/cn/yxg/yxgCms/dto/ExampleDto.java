/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDto.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dto
 * Author:  Archetype Generate
 */

package cn.yxg.yxgCms.dto;

import cn.videoworks.commons.webdev.dto.Convertable;
import cn.yxg.yxgCms.entity.Example;

public class ExampleDto implements Convertable<Example> {

	/** Example ID. */
	private int id;

	/** Example name. */
	private String name;

	/** Example content. */
	private String content;

	@Override
	public Example convert() {
		Example example = new Example();
		example.setId(id);
		example.setName(name);
		example.setContent(content);
		return example;
	}

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
