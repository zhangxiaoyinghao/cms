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
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.ClassificationCourseMapping;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Dynamic;
import cn.yxg.yxgCms.entity.Teacher;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

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
public class TeacherDao extends AdvancedHibernateDao<Teacher> {

	public boolean isAdmin(User currentUser) {
		Criteria criteria = this.getCurrentSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("user", currentUser));
		criteria.add(Restrictions.eq("source", 0));
		return criteria.list().size()>0?true:false;
	}

	public long listCount(String name, Integer classification,
			Boolean auditStatus) {
		Criteria criteria = this.getCurrentSession().createCriteria(Teacher.class);
		if(StringUtils.isNotEmpty(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		if(null!= classification){
			criteria.add(Restrictions.like("classification", classification));
		}
		if(null!= auditStatus){
			criteria.add(Restrictions.eq("auditStatus", auditStatus));
		}
		
		return 0;
	}
	
	
	
}