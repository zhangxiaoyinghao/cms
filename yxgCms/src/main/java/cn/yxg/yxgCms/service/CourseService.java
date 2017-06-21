package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.dto.CategoryPageDto;
import cn.yxg.yxgCms.dto.CourseCommentListDto;
import cn.yxg.yxgCms.dto.CourseDetailDto;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.dto.CourseHomepageDto;
import cn.yxg.yxgCms.dto.CourseListDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

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
