/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDao.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.dao
 * Author:  Archetype Generate
 */
package cn.yxg.yxgAppServer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgAppServer.dto.CourseFilterInputDto;
import cn.yxg.yxgAppServer.entity.Classification;
import cn.yxg.yxgAppServer.entity.ClassificationCourseMapping;
import cn.yxg.yxgAppServer.entity.Course;
import cn.yxg.yxgAppServer.entity.CourseRecommend;
import cn.yxg.yxgAppServer.entity.Dynamic;
import cn.yxg.yxgAppServer.entity.DynamicComment;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

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
public class DynamicCommentDao extends AdvancedHibernateDao<DynamicComment> {


	
	
}