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
@Table(name = "yxg_user_concern")
public class UserConcern {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@ManyToOne
	@JoinColumn(nullable = false,name="user")
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = false,name="concerned_user")
	private User concernedUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getConcernedUser() {
		return concernedUser;
	}

	public void setConcernedUser(User concernedUser) {
		this.concernedUser = concernedUser;
	}
	
	
}
