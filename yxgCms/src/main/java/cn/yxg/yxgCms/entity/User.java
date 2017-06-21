package cn.yxg.yxgCms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "yxg_user")
public class User {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = true,columnDefinition="varchar(64) comment '用户id，手机号'")
	private String username;
	
	@Column(nullable = true,columnDefinition="varchar(128) comment '用户密码，md5'")
	private String password;
	
	@Column(nullable = false,columnDefinition="varchar(64) comment '用户昵称'")
	private String nickname;
	
	@Column(nullable = true,columnDefinition="smallint comment '用户性别，1为男，0为女'")
	private int sex;
	
	@Column(nullable = true,columnDefinition="datetime comment '出生日期'")
	private Date birthday;
	
	@Column(nullable = true,columnDefinition="varchar(32) comment '省'")
	private String province;
	
	@Column(nullable = true,columnDefinition="varchar(32) comment '市'")
	private String city;
	
	@Column(nullable = true,columnDefinition="varchar(256) comment '用户简介（签名）'")
	private String introduce;
	
	@Column(nullable = true,columnDefinition="text comment '用户头像'")
	private String avatar;
	
	@Column(name="wechat_id",nullable = true,columnDefinition="varchar(64) comment '微信id'")
	private String wechatId;
	
	@Column(name="wechat_name",nullable = true,columnDefinition="varchar(64) comment '微信昵称'")
	private String wechatName;
	
	@Column(nullable = false,columnDefinition="smallint default 0 comment '用户类型，0为普通用户，1为老师'")
	private int type;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@Column(nullable = false,columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
	private Date updatetime;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CourseCollection> courseCollections;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CourseOrder> courseOrders;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserConcern> concernUsers;
	
	@OneToMany(mappedBy = "concernedUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserConcern> fansUsers;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CourseComment> courseComments;
	
	@OneToMany(mappedBy = "uploader",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWork> studentWorks;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWorkComment> studentWorkComments;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentWorkPraise> studentWorkPraises;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Dynamic> dynamics;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DynamicComment> dynamicComments;
	
	@OneToMany(mappedBy = "reply",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DynamicComment> replyDynamicComments;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DynamicPraise> dynamicPraises;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Feedback> feedbacks;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Token> tokens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public List<CourseCollection> getCourseCollections() {
		return courseCollections;
	}

	public void setCourseCollections(List<CourseCollection> courseCollections) {
		this.courseCollections = courseCollections;
	}

	public List<CourseOrder> getCourseOrders() {
		return courseOrders;
	}

	public void setCourseOrders(List<CourseOrder> courseOrders) {
		this.courseOrders = courseOrders;
	}

	public List<UserConcern> getConcernUsers() {
		return concernUsers;
	}

	public void setConcernUsers(List<UserConcern> concernUsers) {
		this.concernUsers = concernUsers;
	}

	public List<UserConcern> getFansUsers() {
		return fansUsers;
	}

	public void setFansUsers(List<UserConcern> fansUsers) {
		this.fansUsers = fansUsers;
	}

	public List<CourseComment> getCourseComments() {
		return courseComments;
	}

	public void setCourseComments(List<CourseComment> courseComments) {
		this.courseComments = courseComments;
	}

	public List<StudentWork> getStudentWorks() {
		return studentWorks;
	}

	public void setStudentWorks(List<StudentWork> studentWorks) {
		this.studentWorks = studentWorks;
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

	public List<Dynamic> getDynamics() {
		return dynamics;
	}

	public void setDynamics(List<Dynamic> dynamics) {
		this.dynamics = dynamics;
	}

	public List<DynamicComment> getDynamicComments() {
		return dynamicComments;
	}

	public void setDynamicComments(List<DynamicComment> dynamicComments) {
		this.dynamicComments = dynamicComments;
	}

	public List<DynamicComment> getReplyDynamicComments() {
		return replyDynamicComments;
	}

	public void setReplyDynamicComments(List<DynamicComment> replyDynamicComments) {
		this.replyDynamicComments = replyDynamicComments;
	}

	public List<DynamicPraise> getDynamicPraises() {
		return dynamicPraises;
	}

	public void setDynamicPraises(List<DynamicPraise> dynamicPraises) {
		this.dynamicPraises = dynamicPraises;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
}
