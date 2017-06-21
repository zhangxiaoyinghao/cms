/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.dao;

import java.util.Date;
import java.util.List;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Dynamic;
import cn.yxg.yxgCms.entity.Homework;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;

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
public class HomeworkDao extends AdvancedHibernateDao<Homework> {

	public List<Homework> getHomeworks(Integer borderId, int number) {
		Criteria criteria = this.getCurrentSession().createCriteria(Homework.class);
		if(null!=borderId){
			criteria.add(Restrictions.lt("id", borderId));
		}
		criteria.setMaxResults(number);
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}


	
}