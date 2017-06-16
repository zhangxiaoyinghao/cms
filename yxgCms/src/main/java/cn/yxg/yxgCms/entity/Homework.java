package cn.yxg.yxgAppServer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "yxg_homework")
public class Homework {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="varchar(64) default '' comment '作业名称'")
	private String name;
	
	@Column(nullable = false,columnDefinition="text comment '作业图片url'")
	private String url;
	
	@Column(nullable = true,columnDefinition="text comment '作业描述信息'")
	private String description;
	
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	

	@ManyToOne
	@JoinColumn(nullable = false,name="creator")
	private Teacher creator;
	
	@OneToMany(mappedBy = "homework",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWork> studentWorks;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}



	public Teacher getCreator() {
		return creator;
	}

	public void setCreator(Teacher creator) {
		this.creator = creator;
	}

	public List<StudentWork> getStudentWorks() {
		return studentWorks;
	}

	public void setStudentWorks(List<StudentWork> studentWorks) {
		this.studentWorks = studentWorks;
	}

	
}
