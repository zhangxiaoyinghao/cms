/* @(#) CatalogTemplateServiceImpl.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service.impl
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.dao.CatalogTemplateDao;
import cn.yxg.yxgCms.entity.CatalogTemplate;
import cn.yxg.yxgCms.entity.DictCatalog;
import cn.yxg.yxgCms.service.CatalogTemplateService;
import cn.yxg.yxgCms.service.DictCatalogService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CatalogTemplateServiceImpl implements CatalogTemplateService {

	@Resource
	private CatalogTemplateDao catalogTemplateDao;

	@Resource
	private DictCatalogService dictCatalogServiceImpl;
	
	

	@Override
	public void save(CatalogTemplate catalogTemplate) {
		catalogTemplateDao.save(catalogTemplate);
		if (catalogTemplate.getDictCatalogs() != null) {
			for (int i = 0; i < catalogTemplate.getDictCatalogs().size(); i++) {
				catalogTemplate.getDictCatalogs().get(i)
						.setCatalogTemplate(catalogTemplate);
			}
			dictCatalogServiceImpl.save(catalogTemplate.getDictCatalogs());
		}
	}

	@Override
	public void update(CatalogTemplate catalogTemplate) {
		CatalogTemplate instance = catalogTemplateDao.get(catalogTemplate
				.getId());
		 List<DictCatalog> dictCataloglist = instance.getDictCatalogs();
		for(DictCatalog dictCatalog : dictCataloglist){
			dictCatalogServiceImpl.delete(dictCatalog.getId());
		}
		
		if (StringUtils.isNotBlank(catalogTemplate.getName())) {
			instance.setName(catalogTemplate.getName());
		}

		instance.setDictCatalogs(catalogTemplate.getDictCatalogs());

		catalogTemplateDao.update(instance);
		if (catalogTemplate.getDictCatalogs() != null) {
			List<DictCatalog> dictCatalogs = catalogTemplate.getDictCatalogs();
			for (int i = 0; i < dictCatalogs.size(); i++) {
				dictCatalogs.get(i).setCatalogTemplate(instance);
				// 如果映射表id和编目项id都存在则是更新操作
				if (dictCatalogs.get(i).getId() != null
						&& dictCatalogs.get(i).getCatalogItem().getId() != null) {
					dictCatalogServiceImpl.update(dictCatalogs.get(i));

					// 如果映射表id存在，编目项id为null则是删除操作
				} else if (dictCatalogs.get(i).getId() != null
						&& dictCatalogs.get(i).getCatalogItem().getId() == null) {
					dictCatalogServiceImpl.delete(dictCatalogs.get(i).getId());

					// 如果映射表id不存在，则是新增操作
				} else {
					dictCatalogServiceImpl.save(dictCatalogs.get(i));
				}
			}
		}
	}

	@Override
	public void delete(int id) {
		CatalogTemplate instance = catalogTemplateDao.get(id);
		if (instance != null) {
			instance.setEnable(false);
		}
		catalogTemplateDao.update(instance);
	}

	@Override
	public List<CatalogTemplate> list() {
		return catalogTemplateDao.listEnable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.videoworks.videofact.service.CatalogTemplateService#get()
	 */
	@Override
	public CatalogTemplate get(Integer id) {
		return catalogTemplateDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.videoworks.videofact.service.CatalogTemplateService#
	 * getCatalogTemplateByName(java.lang.String)
	 */
	@Override
	public CatalogTemplate getCatalogTemplateByName(String name) {
		return catalogTemplateDao.getCatalogTemplateByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.videoworks.videofact.service.CatalogTemplateService#list(java.lang
	 * .Integer[])
	 */
	@Override
	public List<CatalogTemplate> list(Integer[] ids) {
		return catalogTemplateDao.list(ids);
	}

	@Override
	public List<CatalogTemplate> convertByType(List<CatalogTemplate> cts) {
		// TODO Auto-generated method stub.
		for (CatalogTemplate ct : cts) {
			List<Map<String,Object>> catalogs = new ArrayList<>();
			Map<String,Object> catalog = new HashMap<>();
			for (DictCatalog dc : ct.getDictCatalogs()) {
				String name = dc.getCatalogItem().getCategory().getName();
				if(catalog.get(name)!=null){
					List<DictCatalog> dcs = (ArrayList<DictCatalog>)catalog.get(name);
					dcs.add(dc);
				}else{
					List<DictCatalog> dcs = new ArrayList<>();
					dcs.add(dc);
					catalog.put(name,dcs);
				}
			}
//			List<String> types = JsonConverter.parse(videofactProperties.get("catalog.item.category").toString(),List.class);
//			for (String key : types) {
//				Map<String,Object> group = new HashMap<>();
//				group.put("groupName",key);
//				group.put("items",catalog.get(key));
//				catalogs.add(group);
//			}
			ct.setCatalogs(catalogs);
		}
		return cts;
	}
}
