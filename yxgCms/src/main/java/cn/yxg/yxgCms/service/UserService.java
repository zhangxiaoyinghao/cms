package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

public interface UserService {

	boolean checkUserByPhone(String phone);

	UserInfoDto insertUser(RegistDto registDto);

	UserInfoDto saveTokenLogin(String phone, String password);

	UserInfoDto saveTokenWechatLogin(WechatLoginDto wechatLoginDto);

	User getByToken(String tokenStr);


	void deleteToken(User user);


}
