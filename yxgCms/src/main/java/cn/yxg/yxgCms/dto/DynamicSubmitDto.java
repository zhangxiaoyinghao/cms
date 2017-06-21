package cn.yxg.yxgCms.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.entity.Dynamic;
import cn.yxg.yxgCms.entity.DynamicComment;
import cn.yxg.yxgCms.entity.DynamicPraise;
import cn.yxg.yxgCms.entity.User;

public class DynamicSubmitDto {
	
	private String content;
	
	@NotEmpty
	private List<String> pictures;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	
}
