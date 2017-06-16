/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDto.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.dto
 * Author:  Archetype Generate
 */

package cn.yxg.yxgAppServer.dto;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class IdListDto {

	@NotEmpty
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
}
