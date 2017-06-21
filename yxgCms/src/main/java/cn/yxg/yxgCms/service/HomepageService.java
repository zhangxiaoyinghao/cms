package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

public interface HomepageService {

	HomepageDto getHomepage();

	UserListDto SearchUserList(User user, String keyword, Integer borderId, Integer number);



}
