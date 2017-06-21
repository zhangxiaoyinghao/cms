package cn.yxg.yxgCms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "yxg_classification")
public class Classification {
	@Id
	@GeneratedValue
	private int id;
	
	//@Column(name="parent_id",nullable = true,columnDefinition="int default null comment '父节点id'")
	//private int parentId;
	@ManyToOne
	@JoinColumn(nullable = true, name="parent")
	private Classification parent;
	
	@OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Classification> children;
	
	@Column(nullable = false,columnDefinition="varchar(20) default '' comment '节点名称'")
	private String name;
	
	@Column(nullable = true,columnDefinition="varchar(20) default '' comment '节点key'")
	private String key;
	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "yxg_classification_course_mapping",
//			joinColumns = @JoinColumn(name = "classification"),
//			inverseJoinColumns = @JoinColumn(name = "course"))
//	@NotFound(action = NotFoundAction.IGNORE)
//	private List<Course> courses;
	
	@OneToMany(mappedBy = "root",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClassificationCourseMapping> coursesUnderRoot;

	@OneToMany(mappedBy = "classification",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClassificationCourseMapping> coursesUnderNode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classification getParent() {
		return parent;
	}

	public void setParent(Classification parent) {
		this.parent = parent;
	}

	public List<Classification> getChildren() {
		return children;
	}

	public void setChildren(List<Classification> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<ClassificationCourseMapping> getCoursesUnderRoot() {
		return coursesUnderRoot;
	}

	public void setCoursesUnderRoot(
			List<ClassificationCourseMapping> coursesUnderRoot) {
		this.coursesUnderRoot = coursesUnderRoot;
	}

	public List<ClassificationCourseMapping> getCoursesUnderNode() {
		return coursesUnderNode;
	}

	public void setCoursesUnderNode(
			List<ClassificationCourseMapping> coursesUnderNode) {
		this.coursesUnderNode = coursesUnderNode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
