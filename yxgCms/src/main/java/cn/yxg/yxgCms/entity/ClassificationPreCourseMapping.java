package cn.yxg.yxgCms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "yxg_classification_pre_course_mapping")
@JsonIgnoreProperties({"course"})
public class ClassificationPreCourseMapping {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name="root")
	private Classification root;
	
	@ManyToOne
	@JoinColumn(nullable = false, name="classification")
	private Classification classification;
	
	@ManyToOne
	@JoinColumn(nullable = false, name="course")
	private PreCourse course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classification getRoot() {
		return root;
	}

	public void setRoot(Classification root) {
		this.root = root;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public PreCourse getCourse() {
		return course;
	}

	public void setCourse(PreCourse course) {
		this.course = course;
	}
	
	
}
