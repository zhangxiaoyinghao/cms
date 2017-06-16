package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageDto {
	List<Map<String,Object>> banners = new ArrayList<Map<String,Object>>();
	Map<String,Object> star = new HashMap<String,Object>();
	List<Map<String,Object>> greatHomeworks = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> infos = new ArrayList<Map<String,Object>>();
	Integer borderId;
	public List<Map<String, Object>> getBanners() {
		return banners;
	}
	public void setBanners(List<Map<String, Object>> banners) {
		this.banners = banners;
	}
	public Map<String, Object> getStar() {
		return star;
	}
	public void setStar(Map<String, Object> star) {
		this.star = star;
	}
	public List<Map<String, Object>> getGreatHomeworks() {
		return greatHomeworks;
	}
	public void setGreatHomeworks(List<Map<String, Object>> greatHomeworks) {
		this.greatHomeworks = greatHomeworks;
	}
	public List<Map<String, Object>> getInfos() {
		return infos;
	}
	public void setInfos(List<Map<String, Object>> infos) {
		this.infos = infos;
	}
	public Integer getBorderId() {
		return borderId;
	}
	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	}
	
}
