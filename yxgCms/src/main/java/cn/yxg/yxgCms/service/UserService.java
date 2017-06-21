package cn.yxg.yxgCms.service;

import java.util.List;

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


}
