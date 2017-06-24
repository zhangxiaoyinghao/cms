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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.query.ContentQuery;

@Repository
public class MovieDao extends AdvancedHibernateDao<Movie> {
	
	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public List<Movie> list(ContentQuery query, Page page) {
		// TODO Auto-generated method stub.
		Criteria criteria = this.getCurrentSession().createCriteria(Movie.class,
				"movie");
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
	public long count(ContentQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Movie.class,
				"movie");
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
	private void createQueryCriteria(ContentQuery query, Criteria criteria) {
		if(StringUtils.isNotBlank(query.getName())){
			criteria.add(Restrictions.like("movie.name", "%"+query.getName()+"%"));
		}
		if(query.getStatus()!=null){
			criteria.add(Restrictions.eq("movie.status", query.getStatus()));
		}
	}
}