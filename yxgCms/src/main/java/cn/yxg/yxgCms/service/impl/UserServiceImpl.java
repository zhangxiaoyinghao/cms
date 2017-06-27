package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dao.TokenDao;
import cn.yxg.yxgCms.dao.UserDao;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Resource
	private TokenDao tokenDao;
	
	@Resource
	private Properties yxgCmsConfig;

	@Override
	public boolean checkUserByPhone(String phone) {
		int userNumber = userDao.findByPhone(phone);
		return userNumber==0?false:true;
	}

	
	@Override
	public UserInfoDto insertUser(RegistDto registDto) {
		User user = new User();
		user.setUsername(registDto.getPhone());
		user.setPassword(MD5Util.MD5(registDto.getPassword()));
		user.setNickname("用户"+registDto.getPhone().substring(7));
		user.setType(0);
		userDao.save(user);
		
		Token token = this.saveToken(user);
		
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.coventer(user);
		userInfoDto.setToken(token.getToken());
		
		return userInfoDto;
	}

	
	private Token saveToken(User user){
		Token token = new Token();
		token.setUser(user);
		token.setToken(CommonUtil.generateUuid());
		token.setExceedTime(DateUtil.getSomeDaysLater(token.getCreatetime(),Integer.parseInt(yxgCmsConfig.get("token.exceed.days").toString())));
		tokenDao.save(token);
		return token;
	}
	
	@Override
	public UserInfoDto saveTokenLogin(String phone, String password) {
		User user = userDao.findByPhoneAndPassword(phone,MD5Util.MD5(password));
		if(user==null){
			return null;
		}
		Token token = this.saveToken(user);
		
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.coventer(user);
		userInfoDto.setToken(token.getToken());
		
		return userInfoDto;
	}


	@Override
	public UserInfoDto saveTokenWechatLogin(WechatLoginDto wechatLoginDto) {
		User user = userDao.findByWechatId(wechatLoginDto.getWechatId());
		if(user==null){
			user = new User();
			user.setAvatar(wechatLoginDto.getAvatar());
			user.setBirthday(wechatLoginDto.getBirthday());
			user.setCity(wechatLoginDto.getCity());
			user.setIntroduce(wechatLoginDto.getIntroduce());
			user.setNickname(wechatLoginDto.getNickname());
			user.setProvince(wechatLoginDto.getProvince());
			user.setSex(wechatLoginDto.getSex());
			user.setType(0);
			user.setWechatId(wechatLoginDto.getWechatId());
			user.setWechatName(wechatLoginDto.getNickname());
			userDao.save(user);
		}
		
		Token token = this.saveToken(user);
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.coventer(user);
		userInfoDto.setToken(token.getToken());
		
		return userInfoDto;
	}


	@Override
	public User getByToken(String tokenStr) {
		if(StringUtils.isBlank(tokenStr)){
			return null;
		}
		Token token = tokenDao.findObjectByToken(tokenStr);
		return token==null?null:token.getUser();
	}


	@Override
	public void deleteToken(User user) {
		List<Token> deleteTokens = user.getTokens();
		tokenDao.delete(deleteTokens);
	}


	@Override
	public User get(int i) {
		// TODO Auto-generated method stub
		return userDao.get(i);
	}


	@Override
	public long listCount(String nickname, String username, String wechatId,
			Integer type) {
		return userDao.count(nickname,username,wechatId,type);
	}


	@Override
	public List<Map<String,Object>> list(String nickname, String username, String wechatId,
			Integer type, Page page) {
		List<User> list =  userDao.list(nickname,username,wechatId,type,page);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		if(null!= list&& list.size()>0){
			for(User user :list){
				maps.add(userToMap(user));
			}
		}
		return maps;
	}
	
	/**
	 * user 转 map
	 * @return
	 */
	@Override
	public  Map<String,Object> userToMap(User user){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != user){
			map.put("id", user.getId());
			map.put("username", user.getUsername());
			if(user.getType()==1&& null!= user.getTeacher()){
				map.put("teacherName", user.getTeacher().getName());
			}
			map.put("nickName",user.getNickname());
			map.put("updatetime", user.getUpdatetime());
			map.put("createtime",user.getCreatetime());
			map.put("type", user.getType());
			map.put("enable", user.getEnable());
		}
		return map ;
	}


	@Override
	public void update(User user) {
		userDao.update(user);
	}
}
