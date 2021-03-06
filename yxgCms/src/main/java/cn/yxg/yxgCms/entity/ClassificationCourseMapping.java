package cn.yxg.yxgCms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "yxg_classification_course_mapping")
public class ClassificationCourseMapping {
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
	private Course course;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}
