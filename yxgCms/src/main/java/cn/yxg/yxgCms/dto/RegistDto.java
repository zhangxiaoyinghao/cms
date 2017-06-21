/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleDto.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.dto
 * Author:  Archetype Generate
 */

package cn.yxg.yxgCms.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class RegistDto {

	@NotBlank(message="手机号不能为空")
	private String phone;
	
	@NotBlank(message="验证码不能为空")
	private String code;
	
	@NotBlank(message="密码不能为空")
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
