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
import cn.yxg.yxgCms.dto.ClassificationDto;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Token;

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
public class ClassificationDao extends AdvancedHibernateDao<Classification> {

	public List<Classification> getNodeClassifications(Classification root) {
		Criteria criteria = this.getCurrentSession().createCriteria(Classification.class).createAlias("parent", "pa");
		criteria.add(Restrictions.eq("pa.parent", root));

		return criteria.list();
	}

	public List<Classification> get(ClassificationDto dto) {
		// TODO Auto-generated method stub
		Criteria criteria = this.getCurrentSession().createCriteria(Classification.class).createAlias("parent", "pa");
		criteria.add(Restrictions.like("pa.name", "%"+dto.getName()+"%"));
		if(dto.getParentId()!=null){
			criteria.createCriteria("pa.parent", "par");
			criteria.add(Restrictions.eq("par.id", dto.getParentId()));
		}

		return criteria.list();
	}

	
	
}