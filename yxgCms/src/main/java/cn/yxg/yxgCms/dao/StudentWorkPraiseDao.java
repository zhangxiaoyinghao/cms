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
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.StudentWorkPraise;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public class StudentWorkPraiseDao extends AdvancedHibernateDao<StudentWorkPraise> {

	public List<StudentWork> getGreatStudentWorkList() {
		Criteria criteria = this.getCurrentSession().createCriteria(StudentWorkPraise.class)
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("studentWork"))
						.add(Projections.count("id"),"praiseNumber"))
						.addOrder(Order.desc("praiseNumber"))
						.setFirstResult(0).setMaxResults(6);
		
		
		List<Object[]> result = criteria.list();
		List<StudentWork> studentWorkList = new ArrayList<>();
		for(Object[] re:result){
			studentWorkList.add((StudentWork)re[0]);
		}
		return studentWorkList;
	}

	public boolean hasPraised(User currentUser, StudentWork studentWorkObj) {
		Criteria criteria = this.getCurrentSession().createCriteria(StudentWorkPraise.class);
		criteria.add(Restrictions.eq("studentWork", studentWorkObj));
		criteria.add(Restrictions.eq("user", currentUser));
		return criteria.list().size()>0?true:false;
	}

	
	
}