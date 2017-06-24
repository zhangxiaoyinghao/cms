/* @(#) CatalogTemplateDao.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.dao
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.entity.CatalogTemplate;

/**
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class CatalogTemplateDao extends AdvancedHibernateDao<CatalogTemplate> {

	/**
	 * 得到未删除的编目模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogTemplate> listEnable() {
		Criteria criteria = this.getCurrentSession().createCriteria(
				CatalogTemplate.class);
		criteria.add(Restrictions.eq("enable", true));
		return criteria.list();
	}

	/**
	 * 根据编目模版明得到模版
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CatalogTemplate getCatalogTemplateByName(String name) {
		Criteria criteria = this.getCurrentSession().createCriteria(
				CatalogTemplate.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("enable", true));
		List<CatalogTemplate> catalogTemplates = criteria.list();
		if (catalogTemplates != null && catalogTemplates.size() > 0) {
			return catalogTemplates.get(0);
		}
		return null;
	}

	/**
	 * 批量查询编目模版
	 * 
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogTemplate> list(Integer[] ids) {
		Criteria criteria = this.getCurrentSession().createCriteria(
				CatalogTemplate.class);
		if(ids!=null){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}
}
