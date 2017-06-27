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
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.ClassificationCourseMapping;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.query.CourseQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
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
public class CourseDao extends AdvancedHibernateDao<Course> {


	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public List<Course> list(CourseQuery query, Page page) {
		// TODO Auto-generated method stub.
		Criteria criteria = this.getCurrentSession().createCriteria(Course.class,
				"course");
		createQueryCriteria(query, criteria);
		criteria.setFirstResult(page.getIndex() * page.getSize());
		criteria.setMaxResults(page.getSize());
		return criteria.list();
	}


	/**
	 * 
	 * 　获取查询结果条数
	 * 
	 * @param query
	 * @return
	 */
	public long count(CourseQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Course.class,
				"course");
		createQueryCriteria(query, criteria);
		return Long.parseLong(criteria.setProjection(Projections.rowCount())
				.uniqueResult().toString());
	}
	
	/**
	 * 
	 * 创建查询条件
	 * 
	 * @param query
	 * @param criteria
	 */
	private void createQueryCriteria(CourseQuery query, Criteria criteria) {
		if(StringUtils.isNotBlank(query.getName())){
			criteria.add(Restrictions.like("course.name", "%"+query.getName()+"%"));
		}
		if(query.getStatus()!=null){
			criteria.add(Restrictions.eq("course.status", query.getStatus()));
		}
	}

	
	public List<Course> getCoursesByCategory(Classification root) {
		Criteria criteria = this.getCurrentSession().createCriteria(Course.class).createAlias("classificationCourseMappings", "ccm");
		criteria.add(Restrictions.eq("ccm.root", root)).addOrder(Order.desc("id")).setMaxResults(6);
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public List<Course> getCourseList(String keyword, Integer borderId,
			Integer number) {
		Criteria criteria = this.getCurrentSession().createCriteria(Course.class);
		if(!StringUtils.isBlank(keyword)){
			criteria.add(Restrictions.like("name", "%"+keyword+"%"));
		}
		if(null!=borderId){
			criteria.add(Restrictions.lt("id", borderId));
		}
		criteria.setMaxResults(number);
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

	public List<Course> getCourseListByFilter(
			Classification root, Classification age,
			Classification difficulty,Classification price,
			Classification purpose,Classification subCategory,
			Integer borderId, int number) {
		
		Criteria criteria = this.getCurrentSession().createCriteria(Course.class).createAlias("classificationCourseMappings", "ccm");
		criteria.add(Restrictions.eq("ccm.root", root));
		
		if(age!=null){
			criteria.add(Restrictions.eq("ccm.classification",age));
		}
		if(difficulty!=null){
			criteria.add(Restrictions.eq("ccm.classification",difficulty));
		}
		if(price!=null){
			criteria.add(Restrictions.eq("ccm.classification",price));
		}
		if(purpose!=null){
			criteria.add(Restrictions.eq("ccm.classification",purpose));
		}
		if(subCategory!=null){
			criteria.add(Restrictions.eq("ccm.classification",subCategory));
		}
		if(null!=borderId){
			criteria.add(Restrictions.lt("id", borderId));
		}
		criteria.setMaxResults(number);
		criteria.addOrder(Order.desc("id"));
		
		return criteria.list();
	}

	
	///仍有问题
	public List<Object []> getRelatedCourse(Classification root) {
//		Criteria criteria = this.getCurrentSession().createCriteria(Course.class).createAlias("classificationCourseMappings", "ccm",JoinType.LEFT_OUTER_JOIN)
//				.createAlias("courseOrders", "co",JoinType.LEFT_OUTER_JOIN);
//		criteria.add(Restrictions.eq("ccm.root", root));
//		criteria.setProjection(Projections.distinct(Projections.projectionList()
//				.add(Projections.groupProperty("ccm.course"))
//				.add(Projections.count("co.id"),"orderNumber")))
//				.addOrder(Order.desc("orderNumber"))
//				.setFirstResult(0).setMaxResults(3);
//		
//		List<Object[]> result = criteria.list();
//		List<Course> courseList = new ArrayList<>();
//		for(Object[] re:result){
//			courseList.add((Course)re[0]);
//		}
		
		Session session = this.getCurrentSession();
		String hql = "select distinct(c.id), count(co.id) FROM Course c left JOIN c.classificationCourseMappings as ccm  LEFT JOIN c.courseOrders as co  where ccm.root=? group by c.id,ccm.id order by count(co.id) desc";
		Query query = session.createQuery(hql);
		query.setInteger(0, root.getId());
		query.setMaxResults(6);
		return query.list();

	}

	
	
}