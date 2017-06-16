package cn.yxg.yxgAppServer.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgAppServer.entity.Dynamic;
import cn.yxg.yxgAppServer.entity.DynamicComment;
import cn.yxg.yxgAppServer.entity.DynamicPraise;
import cn.yxg.yxgAppServer.entity.StudentWork;
import cn.yxg.yxgAppServer.entity.StudentWorkComment;
import cn.yxg.yxgAppServer.entity.StudentWorkPraise;
import cn.yxg.yxgAppServer.entity.User;

public class DynamicDto {
	
	private int id;
	
	private int userId;
	
	private String nickname;
	
	private String avatar;
	
	private Date createTime = new Date();
	
	private String content;
	
	private List<String> pictures;
	
	private int commentNumber;
	
	private int praiseNumber;
	
	private List<Map<String,Object>> praiseList = new ArrayList<>();
	
	private List<Map<String,Object>> commentList = new ArrayList<>();
	
	private boolean hasPraised = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(int praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public List<Map<String, Object>> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<Map<String, Object>> praiseList) {
		this.praiseList = praiseList;
	}

	public List<Map<String, Object>> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Map<String, Object>> commentList) {
		this.commentList = commentList;
	}

	public boolean isHasPraised() {
		return hasPraised;
	}

	public void setHasPraised(boolean hasPraised) {
		this.hasPraised = hasPraised;
	}
	
	public void convert(Dynamic dynamic, User currentUser, int commentShowNumber, int praiseShowNumber){
		this.setId(dynamic.getId());
		this.setUserId(dynamic.getUser().getId());
		this.setNickname(dynamic.getUser().getNickname());
		this.setAvatar(dynamic.getUser().getAvatar());
		this.setContent(dynamic.getContent());
		this.setPictures(JsonConverter.parse(dynamic.getUrl(),List.class));
		this.setCommentNumber(dynamic.getDynamicComments().size());
		this.setPraiseNumber(dynamic.getDynamicPraises().size());

		int praiseShowCount = 0;
		int commentShowCount = 0;
		for(DynamicPraise dynamicPraise:dynamic.getDynamicPraises()){
			if(praiseShowCount<praiseShowNumber){
				Map<String,Object> m = new HashMap<>();
				m.put("userId", dynamicPraise.getUser().getId());
				m.put("avatar", dynamicPraise.getUser().getAvatar());
				this.getPraiseList().add(m);
			}
			if(!this.isHasPraised() && currentUser!=null && dynamicPraise.getUser().getId()==currentUser.getId()){
				this.setHasPraised(true);
			}
			praiseShowCount++;
		}
		
		for(DynamicComment dynamicComment:dynamic.getDynamicComments()){
			if(commentShowCount>=commentShowNumber){
				break;
			}
			Map<String,Object> m = new HashMap<>();
			m.put("id", dynamicComment.getId());
			m.put("userId", dynamicComment.getUser().getId());
			m.put("nickname", dynamicComment.getUser().getNickname());
			m.put("content", dynamicComment.getContent());
			if(dynamicComment.getReply()!=null){
				m.put("replyUserId", dynamicComment.getReply().getId());
				m.put("replyNickname", dynamicComment.getReply().getNickname());
			}
			this.getCommentList().add(m);
			commentShowCount++;
		}
		
	}

	public void convert(StudentWork studentWork, User currentUser,
			int commentShowNumber, int praiseShowNumber) {
		this.setId(studentWork.getId());
		this.setUserId(studentWork.getUploader().getId());
		this.setNickname(studentWork.getUploader().getNickname());
		this.setAvatar(studentWork.getUploader().getAvatar());
		this.setContent(studentWork.getDescription());
		this.setPictures(JsonConverter.parse(studentWork.getUrl(),List.class));
		this.setCommentNumber(studentWork.getStudentWorkComments().size());
		this.setPraiseNumber(studentWork.getStudentWorkPraises().size());

		int praiseShowCount = 0;
		int commentShowCount = 0;
		for(StudentWorkPraise studentWorkPraise:studentWork.getStudentWorkPraises()){
			if(praiseShowCount<praiseShowNumber){
				Map<String,Object> m = new HashMap<>();
				m.put("userId", studentWorkPraise.getUser().getId());
				m.put("avatar", studentWorkPraise.getUser().getAvatar());
				this.getPraiseList().add(m);
			}
			if(!this.isHasPraised() && currentUser!=null && studentWorkPraise.getUser().getId()==currentUser.getId()){
				this.setHasPraised(true);
			}
			praiseShowCount++;
		}
		
		for(StudentWorkComment studentWorkComment:studentWork.getStudentWorkComments()){
			if(commentShowCount>=commentShowNumber){
				break;
			}
			Map<String,Object> m = new HashMap<>();
			m.put("id", studentWorkComment.getId());
			m.put("userId", studentWorkComment.getUser().getId());
			m.put("nickname", studentWorkComment.getUser().getNickname());
			m.put("content", studentWorkComment.getContent());

			this.getCommentList().add(m);
			commentShowCount++;
		}
		
	}
	
}
