package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.dto.CategoryPageDto;
import cn.yxg.yxgAppServer.dto.CourseCommentListDto;
import cn.yxg.yxgAppServer.dto.CourseDetailDto;
import cn.yxg.yxgAppServer.dto.CourseFilterInputDto;
import cn.yxg.yxgAppServer.dto.CourseHomepageDto;
import cn.yxg.yxgAppServer.dto.CourseListDto;
import cn.yxg.yxgAppServer.dto.HomepageDto;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.UserListDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

public interface CourseService {

	CourseHomepageDto getCourseHomepage();

	CategoryPageDto getCategoryPage(int category);

	CourseListDto getCourseList(String keyword, Integer borderId, Integer number);

	CourseListDto getCourseListByFilter(
			CourseFilterInputDto courseFilterInputDto);

	CourseDetailDto getCourseDetail(int course, User user);

	CourseCommentListDto getCourseCommentList(int course, Integer borderId,
			Integer number);

	void addCourseOrder(User user, int course);

	void addCourseComment(User user, Integer course, String content);

	void addCourseCollect(User user, int course);




}
