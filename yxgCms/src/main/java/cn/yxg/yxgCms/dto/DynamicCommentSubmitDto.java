package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgAppServer.entity.Dynamic;
import cn.yxg.yxgAppServer.entity.DynamicComment;
import cn.yxg.yxgAppServer.entity.DynamicPraise;
import cn.yxg.yxgAppServer.entity.User;

public class DynamicCommentSubmitDto {
	
	@NotNull
	private Integer dynamic;
	
	@NotBlank
	private String content;
	
	private Integer replyUserId;

	public Integer getDynamic() {
		return dynamic;
	}

	public void setDynamic(Integer dynamic) {
		this.dynamic = dynamic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}


}
