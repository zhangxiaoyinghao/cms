/* @(#) CatalogItemServiceImpl.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service.impl
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.CatalogItemDao;
import cn.yxg.yxgCms.dto.CatalogItemWsDto;
import cn.yxg.yxgCms.entity.CatalogItem;
import cn.yxg.yxgCms.service.CatalogItemService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * 生产任务与客户
 *
 * @author zy
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CatalogItemServiceImpl implements CatalogItemService {

	@Resource
	private CatalogItemDao catalogItemDao;

	@Override
	public CatalogItem findById(int id) {
		return catalogItemDao.get(id);
	}

	@Override
	public String save(CatalogItem catalogItem) {
		catalogItem.setCreateTime(Calendar.getInstance().getTime());
		CatalogItem result = catalogItemDao.getByKey(catalogItem.getKey());
		if(result != null){
			return "failed";
		}else{
			catalogItemDao.save(catalogItem);
			return "success";
		}
	}

	@Override
	public void delete(int id) {
		catalogItemDao.delete(id);
	}

	@Override
	public void update(CatalogItem catalogItem) {
		CatalogItem instance = catalogItemDao.get(catalogItem.getId());
		if (StringUtils.isNotBlank(catalogItem.getName())) {
			instance.setName(catalogItem.getName());
		}
		if (catalogItem.getType() != null) {
			instance.setType(catalogItem.getType());
		}
//		if (StringUtils.isNotBlank(catalogItem.getContent())) {
			instance.setContent(catalogItem.getContent());
//		}
//		if (StringUtils.isNotBlank(catalogItem.getDescription())) {
			instance.setDescription(catalogItem.getDescription());
//		}
			instance.setDefaultValue(catalogItem.getDefaultValue());
		catalogItemDao.update(instance);
	}

	@Override
	public List<CatalogItem> list() {
		return catalogItemDao.list();
	}

	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.CatalogItemService#list(cn.videoworks.videofact.dto.CatalogItemWsDto)
	 */
	@Override
	public List<CatalogItem> list(CatalogItemWsDto catalogItemWsDto) {
		return catalogItemDao.list(catalogItemWsDto);
	}

	@Override
	public CatalogItem findByKey(String pushPathKey) {
		// TODO Auto-generated method stub
		return catalogItemDao.getByKey(pushPathKey);
	}
}
