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
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.query.ContentQuery;
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
public class PreCourseDao extends AdvancedHibernateDao<PreCourse> {

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public List<PreCourse> list(CourseQuery query, Page page) {
		// TODO Auto-generated method stub.
		Criteria criteria = this.getCurrentSession().createCriteria(PreCourse.class,
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
		Criteria criteria = this.getCurrentSession().createCriteria(PreCourse.class,
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

	
}