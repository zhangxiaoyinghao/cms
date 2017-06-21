package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.dto.CategoryPageDto;
import cn.yxg.yxgCms.dto.CourseCommentListDto;
import cn.yxg.yxgCms.dto.CourseDetailDto;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.dto.CourseHomepageDto;
import cn.yxg.yxgCms.dto.CourseListDto;
import cn.yxg.yxgCms.dto.DynamicCommentSubmitDto;
import cn.yxg.yxgCms.dto.DynamicDto;
import cn.yxg.yxgCms.dto.DynamicListDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

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
