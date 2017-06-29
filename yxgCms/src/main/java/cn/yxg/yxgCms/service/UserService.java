package cn.yxg.yxgCms.service;

import java.util.List;
import java.util.Map;

import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

public interface UserService {

	boolean checkUserByPhone(String phone);

	UserInfoDto insertUser(RegistDto registDto);

	UserInfoDto saveTokenLogin(String phone, String password);

	UserInfoDto saveTokenWechatLogin(WechatLoginDto wechatLoginDto);

	User getByToken(String tokenStr);


	void deleteToken(User user);

	User get(int i);

	long listCount(String nickname, String username, String wechatId,
			Integer type);

	List<Map<String,Object>> list(String nickname, String username, String wechatId,
			Integer type, Page page);

	public  Map<String,Object> userToMap(User user);

	void update(User user);
}	
