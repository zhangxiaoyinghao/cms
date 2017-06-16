/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgAppServer.dao;

import java.util.Date;
import java.util.List;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgAppServer.entity.Course;
import cn.yxg.yxgAppServer.entity.CourseRecommend;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;
import cn.yxg.yxgAppServer.entity.UserHomepage;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public class UserHomepageDao extends AdvancedHibernateDao<UserHomepage> {

	public UserHomepage getUserHomepage() {
		Criteria criteria = this.getCurrentSession().createCriteria(UserHomepage.class);
		criteria.addOrder(Order.desc("id"));
		return criteria.list().size()==0?null:(UserHomepage)criteria.list().get(0);
	}

	
	
}