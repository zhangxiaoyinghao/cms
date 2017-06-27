/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.CourseRecommend;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public class CourseRecommendDao extends AdvancedHibernateDao<CourseRecommend> {

	public List<CourseRecommend> getCourseRecommendList(int place) {
		Criteria criteria = this.getCurrentSession().createCriteria(CourseRecommend.class);
		criteria.add(Restrictions.eq("place", place));
		criteria.addOrder(Order.asc("sequence"));
		return criteria.list();
	}

	
}