/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) Example.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.entity
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.entity;

import javax.persistence.*;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Entity
@Table(name = "ae_example")
public class Example {

	/** Example ID. */
	@Id
	@GeneratedValue
	private int id;

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