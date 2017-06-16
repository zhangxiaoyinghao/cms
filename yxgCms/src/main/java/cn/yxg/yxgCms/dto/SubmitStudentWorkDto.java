package cn.yxg.yxgAppServer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class SubmitStudentWorkDto {



	String content;
	
	@NotNull
	Integer homework;
	
	@NotEmpty
	List<String> pictures;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHomework() {
		return homework;
	}

	public void setHomework(Integer homework) {
		this.homework = homework;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}


}
