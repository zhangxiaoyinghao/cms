package cn.yxg.yxgAppServer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "yxg_dynamic_comment")
public class DynamicComment {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, columnDefinition="varchar(512) comment '评论内容'")
	private String content;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@ManyToOne
	@JoinColumn(nullable = false,name="dynamic")
	private Dynamic dynamic;
	
	@ManyToOne
	@JoinColumn(nullable = false,name="user")
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = true,name="reply")
	private User reply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getReply() {
		return reply;
	}

	public void setReply(User reply) {
		this.reply = reply;
	}
	
	
	
}
