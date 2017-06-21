package cn.yxg.yxgCms.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryPageDto {
	List<Map<String,Object>> subCategory = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> age = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> purpose = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> difficulty = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> price = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	Integer borderId;
	
	public List<Map<String, Object>> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<Map<String, Object>> subCategory) {
		this.subCategory = subCategory;
	}
	public List<Map<String, Object>> getAge() {
		return age;
	}
	public void setAge(List<Map<String, Object>> age) {
		this.age = age;
	}
	public List<Map<String, Object>> getPurpose() {
		return purpose;
	}
	public void setPurpose(List<Map<String, Object>> purpose) {
		this.purpose = purpose;
	}
	public List<Map<String, Object>> getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(List<Map<String, Object>> difficulty) {
		this.difficulty = difficulty;
	}
	public List<Map<String, Object>> getPrice() {
		return price;
	}
	public void setPrice(List<Map<String, Object>> price) {
		this.price = price;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public Integer getBorderId() {
		return borderId;
	}
	public void setBorderId(Integer borderId) {
		this.borderId = borderId;
	}
	

}
