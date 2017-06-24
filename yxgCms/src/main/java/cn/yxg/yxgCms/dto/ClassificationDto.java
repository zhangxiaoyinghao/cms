package cn.yxg.yxgCms.dto;

import javax.validation.constraints.NotNull;

public class ClassificationDto {

	private int id;
	@NotNull
	private String name;
	
	/**
	 *父节点Id 
	 */
	private Integer parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
}
