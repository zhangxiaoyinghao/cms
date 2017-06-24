/**
 * 
 */
package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.entity.DictCatalog;


/**
 * 编目模版和编目项影射表
 * 
 * @author lge
 *
 */
public interface DictCatalogService {
	
	/** 添加映射信息 */
	public void save(DictCatalog dictCatalog);
	
	/** 批量添加映射 */
	public void save(List<DictCatalog> dictCatalogs);
	
	/** 保存或者提交 */
	public void saveOrUpdate(List<DictCatalog> dictCatalogs);
	
	/** 更新映射表 */
	public void update(DictCatalog dictCatalog);
	
	/** 根据id删除映射表数据 */
	public void delete(int id);
}
