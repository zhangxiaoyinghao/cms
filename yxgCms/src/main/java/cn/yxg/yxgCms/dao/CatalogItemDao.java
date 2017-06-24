/* @(#) CatalogItemDao.java
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
import cn.yxg.yxgCms.dto.CatalogItemWsDto;
import cn.yxg.yxgCms.entity.CatalogItem;



/**
 * 编目项数据访问类。
 * 
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class CatalogItemDao extends AdvancedHibernateDao<CatalogItem> {

	/**
	 * 根据key查询编目项
	 * 
	 * @param key
	 * @return
	 */
	public CatalogItem getByKey(String key) {
		Criteria criteria = this.getCurrentSession().createCriteria(
				CatalogItem.class);
		criteria.add(Restrictions.eq("key", key));
		if(criteria.list() != null && criteria.list().size() > 0){
			return (CatalogItem) criteria.list().get(0);
		}
		return null;
	}

	/**
	 * 根据查询条件查询编目项列表
	 * 
	 * @param catalogItemWsDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogItem> list(CatalogItemWsDto catalogItemWsDto) {
		Criteria criteria = this.getCurrentSession().createCriteria(
				CatalogItem.class);
		if (catalogItemWsDto.getId() != null
				&& catalogItemWsDto.getId().size() > 0) {
			criteria.add(Restrictions.in("id", catalogItemWsDto.getId()));
		}
		if (catalogItemWsDto.getKey() != null
				&& catalogItemWsDto.getKey().size() > 0) {
			criteria.add(Restrictions.in("key", catalogItemWsDto.getKey()));
		}
		if(catalogItemWsDto.getName() != null && catalogItemWsDto.getName().size() > 0){
			criteria.add(Restrictions.in("name", catalogItemWsDto.getName()));
		}
		return criteria.list();
	}
}
