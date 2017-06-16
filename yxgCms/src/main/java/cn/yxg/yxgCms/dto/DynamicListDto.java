package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicListDto {
	
	private List<DynamicDto> dynamics = new ArrayList<>();
	
	private Integer borderId;

	public List<DynamicDto> getDynamics() {
		return dynamics;
	}

	public void setDynamics(List<DynamicDto> dynamics) {
		this.dynamics = dynamics;
	}

	public Integer getBorderId() {
		return borderId;
	}

	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	}

	
}
