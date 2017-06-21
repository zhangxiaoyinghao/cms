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
import cn.yxg.yxgCms.entity.Homework;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.StudentWorkPraise;
import cn.yxg.yxgCms.entity.Token;

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
public class StudentWorkDao extends AdvancedHibernateDao<StudentWork> {

	public List<StudentWork> getGreatStudentWorkList() {
		Criteria criteria = this.getCurrentSession().createCriteria(StudentWork.class).createAlias("studentWorkPraises", "swp", JoinType.LEFT_OUTER_JOIN)
					.setProjection(Projections.projectionList()
							.add(Projections.groupProperty("id"))
							.add(Projections.count("swp.id"),"praiseNumber"))
							.addOrder(Order.desc("praiseNumber"))
							.setFirstResult(0).setMaxResults(6);

		List<Object[]> result = criteria.list();
		List<StudentWork> studentWorkList = new ArrayList<>();
		for(Object[] re:result){
			studentWorkList.add(this.get((int)re[0]));
		}
		return studentWorkList;
	}

	public List<StudentWork> getList(Homework homework, Integer borderId, int number) {
		Criteria criteria = this.getCurrentSession().createCriteria(StudentWork.class).add(Restrictions.eq("homework",homework));
		
		if(null!=borderId){
			criteria.add(Restrictions.lt("id", borderId));
		}
		criteria.setMaxResults(number);
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

	public List<Integer> getAllIdsByPraise(Homework homework) {
		Criteria criteria = this.getCurrentSession().createCriteria(StudentWork.class).add(Restrictions.eq("homework",homework))
				.createAlias("studentWorkPraises", "swp", JoinType.LEFT_OUTER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("id"))
						.add(Projections.count("swp.id"),"praiseNumber"))
						.addOrder(Order.desc("praiseNumber"));
		
		List<Integer> idList = new ArrayList<>();
		List<Object[]> result = criteria.list();
		for(Object[] re:result){
			idList.add((int)re[0]);
		}
		return idList;
	}


	
	
}