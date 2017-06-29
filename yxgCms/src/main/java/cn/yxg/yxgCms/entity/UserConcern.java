package cn.yxg.yxgCms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "yxg_user_concern")
@JsonIgnoreProperties({"user","concernedUser"})
public class UserConcern {
	@Id
	@GeneratedValue
	private int id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@ManyToOne
	@JoinColumn(nullable = false,name="user")
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = false,name="concerned_user")
	private User concernedUser;
	
	@Transient
	private String creator;
	
	@Transient
	private String userName;
	
	@Transient
	private String userPoster;
	
	

	public String getCreator() {
		return user!=null?user.getNickname():null;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUserName() {
		return concernedUser!=null?concernedUser.getNickname():null;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPoster() {
		return null;
	}

	public void setUserPoster(String userPoster) {
		this.userPoster = userPoster;
	}

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
