package cn.yxg.yxgCms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yxg_version")
public class Version {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,columnDefinition="smallint comment '0为ios，1为android'")
	private int type;
	
	@Column(nullable = false,columnDefinition="varchar(16) comment '版本号'")
	private String version;
	
	@Column(nullable = true,columnDefinition="text comment '更新描述'")
	private String info;
	
	@Column(name="is_force",nullable = false,columnDefinition="bit(1) default 0 comment '是否强制'")
	private boolean isForce;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isForce() {
		return isForce;
	}

	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	

}
