package cn.yxg.yxgCms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "yxg_pre_movie")
@JsonIgnoreProperties({"course"})
public class PreMovie {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="varchar(64) default '' comment '视频名称'")
	private String name;
	
	@Column(nullable = false,columnDefinition="text comment '视频url'")
	private String url;
	
	@Column(nullable = false,columnDefinition="int comment '视频时长，单位为秒'")
	private int duration;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@Column(nullable = false,columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
	private Date updatetime;
	
	@ManyToOne
	@JoinColumn(nullable = true,name="course_id")
	private PreCourse course;
	
	private int sequence;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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


	public PreCourse getCourse() {
		return course;
	}

	public void setCourse(PreCourse course) {
		this.course = course;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	
}
