package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.dto.HomepageDto;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.UserListDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

public interface HomepageService {

	HomepageDto getHomepage();

	UserListDto SearchUserList(User user, String keyword, Integer borderId, Integer number);



}
