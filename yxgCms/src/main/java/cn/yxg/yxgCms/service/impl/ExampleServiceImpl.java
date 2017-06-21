/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleServiceImpl.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.service.impl
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.yxg.yxgCms.service.ExampleService;
import cn.yxg.yxgCms.dao.ExampleDao;
import cn.yxg.yxgCms.entity.Course;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Service
public class ExampleServiceImpl implements ExampleService {

	@Resource
	private ExampleDao exampleDao;

	@Override
	public List<Course> list() {
		return exampleDao.list();
	}

	@Override
	public void save(Course example) {
		exampleDao.save(example);
	}
}