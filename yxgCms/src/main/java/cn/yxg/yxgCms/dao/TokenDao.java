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

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.Token;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public class TokenDao extends AdvancedHibernateDao<Token> {

	public int findByToken(String tokenStr) {
		Criteria criteria = this.getCurrentSession().createCriteria(Token.class);
		criteria.add(Restrictions.eq("token", tokenStr));
		criteria.add(Restrictions.gt("exceedTime", new Date()));
		return criteria.list().size();
	}
	
	public Token findObjectByToken(String tokenStr){
		Criteria criteria = this.getCurrentSession().createCriteria(Token.class);
		criteria.add(Restrictions.eq("token", tokenStr));
		return criteria.list().size()==0?null:(Token)criteria.list().get(0);
	}
	
}