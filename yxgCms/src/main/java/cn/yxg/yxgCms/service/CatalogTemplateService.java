/* @(#) CatalogTemplateService.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service;


import java.util.List;

import cn.yxg.yxgCms.entity.CatalogTemplate;

/**
 * 编目模板业务逻辑接口。
 *
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CatalogTemplateService {

	/**
	 * 保存编目模板。
	 *
	 * @param catalogTemplate 待保存编目模板
	 */
	public void save(CatalogTemplate catalogTemplate);

	/**
	 * 更新编目模板/
	 *
	 * @param catalogTemplate 待更新编目模板
	 */
	public void update(CatalogTemplate catalogTemplate);

	/**
	 * 根据ID删除编目模板。
	 * 		该方法为假删除，修改为不可用
	 *
	 * @param id 待删除编目模板ID。
	 */
	public void delete(int id);

	/**
	 * 获取全部编目模板列表
	 *
	 * @return 全部编目模板列表
	 */
	public List<CatalogTemplate> list();
	
	/**
	 * 批量查询编目模版
	 * 
	 * @param ids
	 * @return
	 */
	public List<CatalogTemplate> list(Integer[] ids);
	
	/**
	 * 根据id得到编目模版
	 * 
	 * @return
	 */
	public CatalogTemplate get(Integer id);
	
	/**
	 * 根据模版名得到编目模版
	 * 
	 * @param name
	 * @return
	 */
	public CatalogTemplate getCatalogTemplateByName(String name);

	/**
	 * 转换编码模板
	 *
	 * @param list
	 * @return
	 */
	public List<CatalogTemplate> convertByType(List<CatalogTemplate> list);
}
