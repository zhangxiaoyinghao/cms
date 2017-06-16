package cn.yxg.yxgAppServer.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "yxg_course_recommend")
public class CourseRecommend {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="smallint comment '0为首页banner，1为课程页banner，2为美术推荐位，3位舞蹈推荐位，4位音乐推荐位，5为考级推荐位'")
	private int place;
	
	@Column(nullable = false,columnDefinition="smallint comment '顺序'")
	private int sequence;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false,name = "course")
	private Course course;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
