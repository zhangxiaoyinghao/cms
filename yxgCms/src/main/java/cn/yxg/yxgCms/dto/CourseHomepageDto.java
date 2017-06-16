package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseHomepageDto {
	List<Map<String,Object>> banners = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> arts = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> music = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> dance = new ArrayList<Map<String,Object>>();
	public List<Map<String, Object>> getBanners() {
		return banners;
	}
	public void setBanners(List<Map<String, Object>> banners) {
		this.banners = banners;
	}
	public List<Map<String, Object>> getArts() {
		return arts;
	}
	public void setArts(List<Map<String, Object>> arts) {
		this.arts = arts;
	}
	public List<Map<String, Object>> getMusic() {
		return music;
	}
	public void setMusic(List<Map<String, Object>> music) {
		this.music = music;
	}
	public List<Map<String, Object>> getDance() {
		return dance;
	}
	public void setDance(List<Map<String, Object>> dance) {
		this.dance = dance;
	}
	
	
}
