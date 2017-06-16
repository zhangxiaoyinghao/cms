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
@Table(name = "yxg_studentwork")
public class StudentWork {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="text comment '作品图片url，json形式'")
	private String url;
	
	@Column(nullable = true,columnDefinition="text comment '作品描述信息'")
	private String description;
	
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	

	@ManyToOne
	@JoinColumn(nullable = false,name="uploader")
	private User uploader;
	
	
	@ManyToOne
	@JoinColumn(nullable = false,name="homework")
	private Homework homework;
	
	@OneToMany(mappedBy = "studentWork",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWorkComment> studentWorkComments;
	
	@OneToMany(mappedBy = "studentWork",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWorkPraise> studentWorkPraises;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
	}

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public List<StudentWorkComment> getStudentWorkComments() {
		return studentWorkComments;
	}

	public void setStudentWorkComments(List<StudentWorkComment> studentWorkComments) {
		this.studentWorkComments = studentWorkComments;
	}

	public List<StudentWorkPraise> getStudentWorkPraises() {
		return studentWorkPraises;
	}

	public void setStudentWorkPraises(List<StudentWorkPraise> studentWorkPraises) {
		this.studentWorkPraises = studentWorkPraises;
	}

	
}
