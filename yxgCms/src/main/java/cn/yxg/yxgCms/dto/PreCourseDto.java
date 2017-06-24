package cn.yxg.yxgCms.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class PreCourseDto {

	
	private Integer id;
	
	@NotBlank(message="名称不能为空")
	@Length(max=64,message="名称不合法")
	private String name;
	
	private Float price;
	
	private Integer expiryDate;
	
	@Length(max=1000,message="描述不合法")
	private String description;
	
	@Length(max=500,message="海报不合法")
	private String poster;
	
	@Length(max=5000,message="编目不合法")
	private String catalog;
	
	private Integer catalogTemplateId;
	
	@NotEmpty(message="分类不能为空")
	private List<Integer> classifications;
	
	@NotNull(message="ROOT不能为空")
	private Integer root;
	
	/**
	 * 有序movie
	 */
	@NotEmpty(message="视频不能为空")
	private List<Integer> movies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Integer expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Integer getCatalogTemplateId() {
		return catalogTemplateId;
	}

	public void setCatalogTemplateId(Integer catalogTemplateId) {
		this.catalogTemplateId = catalogTemplateId;
	}

	public List<Integer> getMovies() {
		return movies;
	}

	public void setMovies(List<Integer> movies) {
		this.movies = movies;
	}

	public List<Integer> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Integer> classifications) {
		this.classifications = classifications;
	}

	public Integer getRoot() {
		return root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
