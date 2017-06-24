/**
 * 
 */
package cn.yxg.yxgCms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.DictCatalogDao;
import cn.yxg.yxgCms.entity.DictCatalog;
import cn.yxg.yxgCms.service.DictCatalogService;
/**
 * @author lge
 *
 */
@Service
public class DictCatalogServiceImpl implements DictCatalogService {

	@Resource
	private DictCatalogDao catalogDao;
	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.DictCatalogService#save(cn.videoworks.videofact.entity.DictCatalog)
	 */
	@Override
	public void save(DictCatalog dictCatalog) {
		catalogDao.save(dictCatalog);
	}
	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.DictCatalogService#save(java.util.List)
	 */
	@Override
	public void save(List<DictCatalog> dictCatalogs) {
		catalogDao.save(dictCatalogs);
	}
	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.DictCatalogService#saveOrUpdate(cn.videoworks.videofact.entity.DictCatalog)
	 */
	@Override
	public void saveOrUpdate(List<DictCatalog> dictCatalogs) {
		catalogDao.saveOrUpdate(dictCatalogs);
	}
	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.DictCatalogService#update(cn.videoworks.videofact.entity.DictCatalog)
	 */
	@Override
	public void update(DictCatalog dictCatalog) {
		DictCatalog persistentDictCatalog = catalogDao.get(dictCatalog.getId());
		if(persistentDictCatalog != null){
			persistentDictCatalog.setCatalogItem(dictCatalog.getCatalogItem());
			persistentDictCatalog.setCatalogTemplate(dictCatalog.getCatalogTemplate());
			persistentDictCatalog.setRequired(dictCatalog.isRequired());
//			persistentDictCatalog.setType(dictCatalog.getType());
			catalogDao.update(persistentDictCatalog);
		}
	}
	/* (non-Javadoc)
	 * @see cn.videoworks.videofact.service.DictCatalogService#delete(int)
	 */
	@Override
	public void delete(int id) {
		catalogDao.delete(id);
	}

}
