/**
 * 
 */
package cn.yxg.yxgCms.dto;

import java.util.List;

/**
 * 编目项ws数据传输类
 * 
 * @author lge
 *
 */
public class CatalogItemWsDto {
	
	/** 编目项id */
	private List<Integer> id;
	
	/** 编目项英文明 */
	private List<String> key;
	
	/** 编目项中文明 */
	private List<String> name;

	/**
	 * @return the id
	 */
	public List<Integer> getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(List<Integer> id) {
		this.id = id;
	}

	/**
	 * @return the key
	 */
	public List<String> getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(List<String> key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public List<String> getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(List<String> name) {
		this.name = name;
	}
	
}
