/* @(#) CatalogItemService.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.dto.CatalogItemWsDto;
import cn.yxg.yxgCms.entity.CatalogItem;

/**
 * 编目项业务逻辑接口。
 *
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CatalogItemService {

	/**
	 * 根据ID查询编目项。
	 *
	 * @param id 编目项ID。
	 * @return 编目项实例。
	 */
	public CatalogItem findById(int id);

	/**
	 * 保存编目项。
	 *
	 * @param catalogItem 待保存编目项。
	 */
	public String save(CatalogItem catalogItem);

	/**
	 * 删除编目项。
	 *
	 * @param id 待删除编目项ID。
	 */
	public void delete(int id);

	/**
	 * 更新编目项内容。
	 *
	 * @param catalogItem 更新编目项内容。
	 */
	public void update(CatalogItem catalogItem);

	/**
	 * 列出所有编目项。
	 *
	 * @return 全部编目项列表。
	 */
	public List<CatalogItem> list();
	
	/**
	 * 根据ws查询条件查询编目项列表
	 * 
	 * @param catalogItemWsDto
	 * @return
	 */
	public List<CatalogItem> list(CatalogItemWsDto catalogItemWsDto);

	public CatalogItem findByKey(String pushPathKey);

}
