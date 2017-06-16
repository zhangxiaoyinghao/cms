package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDetailDto {
	
	private String title;
	
	private float price = (float) 0.0;
	
	private int studentNumber;
	
	private String description;
	
	private List<Map<String,Object>> teacher = new ArrayList<>();
	
	private String age;
	
	private String purpose;
	
	private List<Map<String,Object>>  relatedCourses = new ArrayList<>();
	
	private List<Map<String,Object>>  videos = new ArrayList<>();
	
	private Boolean join = false;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Map<String, Object>> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Map<String, Object>> teacher) {
		this.teacher = teacher;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<Map<String, Object>> getRelatedCourses() {
		return relatedCourses;
	}

	public void setRelatedCourses(List<Map<String, Object>> relatedCourses) {
		this.relatedCourses = relatedCourses;
	}

	public List<Map<String, Object>> getVideos() {
		return videos;
	}

	public void setVideos(List<Map<String, Object>> videos) {
		this.videos = videos;
	}

	public Boolean getJoin() {
		return join;
	}

	public void setJoin(Boolean join) {
		this.join = join;
	}
	
}
