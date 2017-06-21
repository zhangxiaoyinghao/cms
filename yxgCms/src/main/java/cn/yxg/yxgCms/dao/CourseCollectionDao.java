/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseCollection;
import cn.yxg.yxgCms.entity.CourseOrder;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.StudentWorkPraise;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public class CourseCollectionDao extends AdvancedHibernateDao<CourseCollection> {


	public boolean hasCollected(User user, Course courseObj) {
		Criteria criteria = this.getCurrentSession().createCriteria(CourseCollection.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("course", courseObj));
		return criteria.list().size()==0?false:true;
	}

	
	
}