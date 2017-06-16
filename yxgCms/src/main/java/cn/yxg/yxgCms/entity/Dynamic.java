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
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "yxg_dynamic")
public class Dynamic {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="text comment '动态图片url，json形式'")
	private String url;
	
	@Column(nullable = true,columnDefinition="text comment '动态内容'")
	private String content;
	
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	

	@ManyToOne
	@JoinColumn(nullable = false,name="user")
	private User user;
	
	@OrderBy(value = "createtime asc")
	@OneToMany(mappedBy = "dynamic",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DynamicComment> dynamicComments;
	
	@OrderBy(value = "createtime desc")
	@OneToMany(mappedBy = "dynamic",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DynamicPraise> dynamicPraises;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DynamicComment> getDynamicComments() {
		return dynamicComments;
	}

	public void setDynamicComments(List<DynamicComment> dynamicComments) {
		this.dynamicComments = dynamicComments;
	}

	public List<DynamicPraise> getDynamicPraises() {
		return dynamicPraises;
	}

	public void setDynamicPraises(List<DynamicPraise> dynamicPraises) {
		this.dynamicPraises = dynamicPraises;
	}
	
	
}
