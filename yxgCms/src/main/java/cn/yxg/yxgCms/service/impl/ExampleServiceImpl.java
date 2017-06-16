/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleServiceImpl.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.service.impl
 * Author:  Archetype Generate
 */
package cn.yxg.yxgAppServer.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.yxg.yxgAppServer.service.ExampleService;
import cn.yxg.yxgAppServer.dao.ExampleDao;
import cn.yxg.yxgAppServer.entity.Course;

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