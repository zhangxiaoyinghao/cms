/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.Course;
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
public class CourseOrderDao extends AdvancedHibernateDao<CourseOrder> {

	public List<Course> getRelatedCourse(Classification root) {
		Criteria criteria = this.getCurrentSession().createCriteria(CourseOrder.class).createAlias("course", "c",JoinType.RIGHT_OUTER_JOIN).createAlias("c.classificationCourseMappings", "ccm",JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq("ccm.root", root));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("ccm.course"))
						.add(Projections.count("id"),"orderNumber"))
						.addOrder(Order.desc("orderNumber"))
						.setFirstResult(0).setMaxResults(3);
		
		
		List<Object[]> result = criteria.list();
		List<Course> courseList = new ArrayList<>();
		for(Object[] re:result){
			courseList.add((Course)re[0]);
		}
		return courseList;
	}

	public boolean hasJoined(User user, Course courseObj) {
		Criteria criteria = this.getCurrentSession().createCriteria(CourseOrder.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("course", courseObj));
		return criteria.list().size()==0?false:true;
	}


	
	
}