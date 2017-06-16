package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkListDto {
	List<Map<String,Object>> homeworks = new ArrayList<Map<String,Object>>();
	Integer borderId;
	public List<Map<String, Object>> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(List<Map<String, Object>> homeworks) {
		this.homeworks = homeworks;
	}
	public Integer getBorderId() {
		return borderId;
	}
	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	}

}
