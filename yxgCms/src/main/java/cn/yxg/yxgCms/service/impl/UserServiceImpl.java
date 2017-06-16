package cn.yxg.yxgAppServer.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import cn.yxg.yxgAppServer.dao.TokenDao;
import cn.yxg.yxgAppServer.dao.UserDao;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;
import cn.yxg.yxgAppServer.service.TokenService;
import cn.yxg.yxgAppServer.service.UserService;
import cn.yxg.yxgAppServer.util.CommonUtil;
import cn.yxg.yxgAppServer.util.DateUtil;
import cn.yxg.yxgAppServer.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Resource
	private TokenDao tokenDao;
	
	@Resource
	private Properties yxgAppServerConfig;

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
		token.setExceedTime(DateUtil.getSomeDaysLater(token.getCreatetime(),Integer.parseInt(yxgAppServerConfig.get("token.exceed.days").toString())));
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

}
