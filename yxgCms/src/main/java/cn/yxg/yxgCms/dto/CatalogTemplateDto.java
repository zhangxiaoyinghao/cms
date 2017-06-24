/**
 * 
 */
package cn.yxg.yxgCms.dto;

import java.util.List;

import javax.validation.constraints.Size;

import cn.yxg.yxgCms.entity.CatalogTemplate;
import cn.yxg.yxgCms.entity.DictCatalog;


/**
 * @author lge
 *
 */
public class CatalogTemplateDto {

	/** 编目模板ID。 */
	private int templateId;

	/** 编目模板名称。 */
	@Size(min=0,max=50)
	private String name;

	/** 编目模版对应影射表1对多关系 */
	private List<DictCatalog> dictCatalogs;


	/**
	 * @return the templateId
	 */
	public int getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * @return the dictCatalogs
	 */
	public List<DictCatalog> getDictCatalogs() {
		return dictCatalogs;
	}

	/**
	 * @param dictCatalogs the dictCatalogs to set
	 */
	public void setDictCatalogs(List<DictCatalog> dictCatalogs) {
		this.dictCatalogs = dictCatalogs;
	}

	/**
	 * 将dto转换成entity
	 * 
	 * @return
	 */
	public CatalogTemplate  convert(){
		CatalogTemplate catalogTemplate = new CatalogTemplate();
		catalogTemplate.setId(templateId);
		catalogTemplate.setName(name);
		catalogTemplate.setDictCatalogs(dictCatalogs);
		return catalogTemplate;
	}
	
}
