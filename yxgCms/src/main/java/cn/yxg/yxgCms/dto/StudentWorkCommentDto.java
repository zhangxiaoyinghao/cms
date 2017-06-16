package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class StudentWorkCommentDto {

	@NotBlank
	String content;
	
	@NotNull
	Integer studentWork;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStudentWork() {
		return studentWork;
	}

	public void setStudentWork(Integer studentWork) {
		this.studentWork = studentWork;
	}
	


}
