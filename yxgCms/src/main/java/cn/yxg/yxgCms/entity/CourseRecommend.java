package cn.yxg.yxgCms.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "yxg_course_recommend")
@JsonIgnoreProperties({"course","user"})
public class CourseRecommend {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="smallint comment '0为首页banner，1为课程页banner，2为美术推荐位，3位舞蹈推荐位，4位音乐推荐位，5为考级推荐位'")
	private int place;
	
	@Column(nullable = false,columnDefinition="smallint comment '顺序'")
	private int sequence;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course")
	private PreCourse course;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private User user;
	
	@Transient
	private String creator;
	
	@Transient
	private String poster;
	
	@Transient
	private String courseName;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public PreCourse getCourse() {
		return course;
	}

	public void setCourse(PreCourse course) {
		this.course = course;
	}

	public String getPoster() {
		return course!=null?course.getPoster():null;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getCourseName() {
		return course!=null?course.getName():null;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCreator() {
		return user!=null?user.getNickname():null;
	}


}
