package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseCommentListDto {
	
	private Integer totalNumber;
	
	private List<Map<String,Object>>  comments = new ArrayList<>();
	
	private Integer borderId;

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List<Map<String, Object>> getComments() {
		return comments;
	}

	public void setComments(List<Map<String, Object>> comments) {
		this.comments = comments;
	}

	public Integer getBorderId() {
		return borderId;
	}

	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	} 
	
}
