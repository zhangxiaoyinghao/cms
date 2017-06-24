package cn.yxg.yxgCms.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "yxg_pre_classification")
public class PreClassification {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(nullable = true, name="parent")
	private PreClassification parent;
	
	@OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<PreClassification> children;
	
	@Column(nullable = false,columnDefinition="varchar(20) default '' comment '节点名称'")
	private String name;
	
	@Column(nullable = true,columnDefinition="varchar(20) default '' comment '节点key'")
	private String key;

	
	@Transient
	private Integer ranking;
	
	

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PreClassification getParent() {
		return parent;
	}

	public void setParent(PreClassification parent) {
		this.parent = parent;
	}



	public Set<PreClassification> getChildren() {
		return children;
	}

	public void setChildren(Set<PreClassification> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
