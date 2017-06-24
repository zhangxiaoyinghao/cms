package cn.yxg.yxgCms.dto;

import java.util.List;
import java.util.Set;


public class ClassificationInfoDto {

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 *  名称
	 */
	private String name;
	
	
	
	/**
	 * 子级组织结构
	 */
	private Set<ClassificationDto>  childrens;
	
	

	private boolean enable;
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * 排序标志 
	 */
	private Integer ranking;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<ClassificationDto> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<ClassificationDto> childrens) {
		this.childrens = childrens;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}


	
	
}
