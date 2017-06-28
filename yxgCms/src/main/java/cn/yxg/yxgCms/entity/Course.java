package cn.yxg.yxgCms.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "yxg_course")
public class Course {
	@Id
	@GeneratedValue
	private int id;
	

	private String uuid;
	
	@Column(nullable = false,columnDefinition="varchar(64) default '' comment '课程名称'")
	private String name;
	
	@Column(nullable = false,columnDefinition="float default 0.0 comment '课程价格'")
	private float price;
	
	@Column(name="expiry_day",nullable = true,columnDefinition="int comment '有效期天数，null为永久有效'")
	private Integer expiryDay;
	
	@Column(nullable = true,columnDefinition="text comment '课程描述'")
	private String description;
	
	@Column(nullable = true,columnDefinition="text comment '课程海报'")
	private String poster;
	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "yxg_classification_course_mapping",
//			joinColumns = @JoinColumn(name = "course"),
//			inverseJoinColumns = @JoinColumn(name = "classification"))
//	@NotFound(action = NotFoundAction.IGNORE)
//	private List<Classification> classfications;
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<ClassificationCourseMapping> classificationCourseMappings;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable = false,columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
	private Date updatetime;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Movie> movies;
	
	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private List<CourseRecommend> courseRecommends;
	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "yxg_teacher_course_mapping",
//			joinColumns = @JoinColumn(name = "course"),
//			inverseJoinColumns = @JoinColumn(name = "teacher"))
//	@NotFound(action = NotFoundAction.IGNORE)
//	private List<Teacher> teachers;
//	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseCollection> courseCollections;
//	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseOrder> courseOrders;
//	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseComment> courseComments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getExpiryDay() {
		return expiryDay;
	}

	public void setExpiryDay(Integer expiryDay) {
		this.expiryDay = expiryDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}




	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	

	public Set<ClassificationCourseMapping> getClassificationCourseMappings() {
		return classificationCourseMappings;
	}

	public void setClassificationCourseMappings(Set<ClassificationCourseMapping> classificationCourseMappings) {
		this.classificationCourseMappings = classificationCourseMappings;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Returns the value of the field called 'courseRecommends'.
	 * @return Returns the courseRecommends.
	 */
	public List<CourseRecommend> getCourseRecommends() {
		return this.courseRecommends;
	}

	/**
	 * Sets the field called 'courseRecommends' to the given value.
	 * @param courseRecommends The courseRecommends to set.
	 */
	public void setCourseRecommends(List<CourseRecommend> courseRecommends) {
		this.courseRecommends = courseRecommends;
	}
}
