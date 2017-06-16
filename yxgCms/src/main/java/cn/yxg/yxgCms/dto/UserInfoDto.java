package cn.yxg.yxgAppServer.dto;

import java.util.Date;

import cn.yxg.yxgAppServer.entity.User;

public class UserInfoDto {

	private int userId;
	
	private String username;
	
	private String nickname;
	
	private int sex;
	
	private Date birthday;
	
	private String province;
	
	private String city;
	
	private String introduce;
	
	private String avatar;
	
	private String wechatId;
	
	private String wechatName;
	
	private String token;



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void coventer(User user){
		//UserInfoDto userInfoDto = new UserInfoDto();
		
		this.setAvatar(user.getAvatar());
		this.setBirthday(user.getBirthday());
		this.setCity(user.getCity());
		this.setUserId(user.getId());
		this.setIntroduce(user.getIntroduce());
		this.setNickname(user.getNickname());
		this.setProvince(user.getProvince());
		this.setSex(user.getSex());
		this.setUsername(user.getUsername());
		this.setWechatId(user.getWechatId());
		this.setWechatName(user.getWechatName());
		
		//return userInfoDto;
	}
	
}
