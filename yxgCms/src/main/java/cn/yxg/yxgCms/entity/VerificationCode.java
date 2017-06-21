package cn.yxg.yxgCms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "yxg_verification_code")
public class VerificationCode {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = true,columnDefinition="varchar(256) comment '短信内容'")
	private String content;
	
	@Column(nullable = false,columnDefinition="varchar(16) comment '验证码'")
	private String code;
	
	@Column(nullable = false,columnDefinition="varchar(64) comment '手机号'")
	private String phone;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@Column(name="exceed_time",nullable = false,columnDefinition="datetime comment '超时时间'")
	private Date exceedTime;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getExceedTime() {
		return exceedTime;
	}

	public void setExceedTime(Date exceedTime) {
		this.exceedTime = exceedTime;
	}

	
	
}
