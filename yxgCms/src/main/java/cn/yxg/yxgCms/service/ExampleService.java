/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleService.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.service
 * Author:  Archetype Generate
 */
package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.entity.Course;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
public interface ExampleService {

	/**
	 * List example.
	 */
	public List<Course> list();

	/**
	 * Save example.
	 */
	public void save(Course example);
}