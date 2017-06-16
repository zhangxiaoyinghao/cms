/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleService.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.service
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.service;

import java.util.List;
import cn.yxg.yxgCms.entity.Example;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
public interface ExampleService {

	/**
	 * List example.
	 */
	public List<Example> list();

	/**
	 * Save example.
	 */
	public void save(Example example);
}