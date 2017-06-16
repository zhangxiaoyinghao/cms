package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.dto.CategoryPageDto;
import cn.yxg.yxgAppServer.dto.CourseCommentListDto;
import cn.yxg.yxgAppServer.dto.CourseDetailDto;
import cn.yxg.yxgAppServer.dto.CourseFilterInputDto;
import cn.yxg.yxgAppServer.dto.CourseHomepageDto;
import cn.yxg.yxgAppServer.dto.CourseListDto;
import cn.yxg.yxgAppServer.dto.DynamicCommentSubmitDto;
import cn.yxg.yxgAppServer.dto.DynamicDto;
import cn.yxg.yxgAppServer.dto.DynamicListDto;
import cn.yxg.yxgAppServer.dto.HomepageDto;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.UserListDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

public interface DynamicService {

	DynamicListDto getDynamicList(User currentUser, Integer user,
			Integer borderId, int number);

	UserListDto getPraiseList(User currentUser, int dynamic, Integer borderId,
			int number);

	DynamicDto getDetail(User currentUser, int dynamic);

	void addDynamic(User currentUser, String content, List<String> pictures);

	void addDynamicComment(User currentUser,
			DynamicCommentSubmitDto dynamicCommentSubmitDto);

	void addDynamicPraise(User currentUser, int dynamic);

	boolean deleteDynamic(User currentUser, int dynamic);

	boolean deleteDynamicComment(User currentUser, int comment);



}
