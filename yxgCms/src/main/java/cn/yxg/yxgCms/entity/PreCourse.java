package cn.yxg.yxgCms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "yxg_pre_course")
@JsonIgnoreProperties({"user","classificationPreCourseMappings"})
public class PreCourse {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="varchar(64) default '' comment '课程名称'")
	private String name;
	
	private String uuid;
	
	@Column(nullable = false,columnDefinition="float default 0.0 comment '课程价格'")
	private float price;
	
	@Column(name="expiry_day",nullable = true,columnDefinition="int comment '有效期天数，null为永久有效'")
	private Integer expiryDay;
	
	@Column(nullable = true,columnDefinition="text comment '课程描述'")
	private String description;
	
	@Column(nullable = true,columnDefinition="text comment '课程海报'")
	private String poster;
	
	private String catalog;
	
	@ManyToOne
	@JoinColumn(name="catalog_template_id")
	private CatalogTemplate catalogTemplate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private Integer status;
	
	@Transient
	private List<Map<String,Object>> classificationList = new ArrayList<>();
	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "yxg_classification_pre_course_mapping",
//			joinColumns = @JoinColumn(name = "course"),
//			inverseJoinColumns = @JoinColumn(name = "classification"))
//	@NotFound(action = NotFoundAction.IGNORE)
//	private List<Classification> classfications;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
	private Set<ClassificationPreCourseMapping> classificationPreCourseMappings;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable = false,columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
	private Date updatetime;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<PreMovie> movies;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "yxg_teacher_course_mapping",
			joinColumns = @JoinColumn(name = "course"),
			inverseJoinColumns = @JoinColumn(name = "teacher"))
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Teacher> teachers;
	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseCollection> courseCollections;
//	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseOrder> courseOrders;
//	
//	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<CourseComment> courseComments;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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


	public List<PreMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<PreMovie> movies) {
		this.movies = movies;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Set<ClassificationPreCourseMapping> getClassificationPreCourseMappings() {
		return classificationPreCourseMappings;
	}

	public void setClassificationPreCourseMappings(Set<ClassificationPreCourseMapping> classificationPreCourseMappings) {
		this.classificationPreCourseMappings = classificationPreCourseMappings;
	}

	public CatalogTemplate getCatalogTemplate() {
		return catalogTemplate;
	}

	public void setCatalogTemplate(CatalogTemplate catalogTemplate) {
		this.catalogTemplate = catalogTemplate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Map<String, Object>> getClassificationList() {
		Set<ClassificationPreCourseMapping> ccms = this.getClassificationPreCourseMappings();
		for (ClassificationPreCourseMapping ccm : ccms) {
			Map<String,Object> ccmMap = new HashMap<>();
			ccmMap.put("root", ccm.getRoot().getId());
			ccmMap.put("classification", ccm.getClassification().getId());
			classificationList.add(ccmMap);
		}
		return classificationList;
	}

	public void setClassificationList(List<Map<String, Object>> classificationList) {
		this.classificationList = classificationList;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
}
