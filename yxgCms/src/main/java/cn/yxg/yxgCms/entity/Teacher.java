package cn.yxg.yxgCms.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "yxg_teacher")
public class Teacher {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="varchar(20) comment '教师真实姓名'")
	private String name;
	
	@Column(nullable = true,columnDefinition="varchar(20) comment '头衔'")
	private String title;
	
	@Column(nullable = true,columnDefinition="text comment '描述'")
	private String description;
	
	@Column(nullable = true,columnDefinition="text comment '教师海报'")
	private String poster;
	
	@Column(nullable = true,name="audit_status",columnDefinition="bit comment '0:未审核通过 1：审核通过'")
	private boolean auditStatus;
	
	@Column(nullable = true,name="enable",columnDefinition="默认为1，1为启用，0为禁用")
	private boolean enable;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false,name = "user")
	private User user;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@Column(nullable = false,columnDefinition="smallint default 0 comment '来源：0为官方老师，1为用户申请的老师'")
	private int source;
	
	@Column(nullable = false, name="classification",columnDefinition="smallint comment '1代表美术，2代表音乐，3代表舞蹈'")
	private Integer classification;
	
	@OneToMany(mappedBy = "creator",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Homework> homeworks;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "yxg_teacher_course_mapping",
			joinColumns = @JoinColumn(name = "teacher"),
			inverseJoinColumns = @JoinColumn(name = "course"))
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Course> courses;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public boolean getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(boolean auditStatus) {
		this.auditStatus = auditStatus;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public Integer getClassification() {
		return classification;
	}

	public void setClassification(Integer classification) {
		this.classification = classification;
	}

	

}
