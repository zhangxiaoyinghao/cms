package cn.yxg.yxgCms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "yxg_course_order")
public class CourseOrder {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="order_code", nullable = false, columnDefinition="varchar(64) comment '订单号'")
	private String orderCode;
	
	@Column(nullable = false,columnDefinition="float default 0.0 comment '订单价格'")
	private float price;
	
	@Column(nullable = false,columnDefinition="smallint default 0 comment '订单状态，0为未支付，1为已支付，-1为已取消'")
	private int status;
	
	@Column(nullable = false,columnDefinition="datetime comment '创建时间'")
	private Date createtime = new Date();
	
	@ManyToOne
	@JoinColumn(nullable = false,name="course")
	private Course course;
	
	@ManyToOne
	@JoinColumn(nullable = false,name="user")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
