package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.dao.ClassificationCourseMappingDao;
import cn.yxg.yxgCms.dao.ClassificationDao;
import cn.yxg.yxgCms.dao.CourseCollectionDao;
import cn.yxg.yxgCms.dao.CourseCommentDao;
import cn.yxg.yxgCms.dao.CourseDao;
import cn.yxg.yxgCms.dao.CourseOrderDao;
import cn.yxg.yxgCms.dao.CourseRecommendDao;
import cn.yxg.yxgCms.dao.DynamicCommentDao;
import cn.yxg.yxgCms.dao.DynamicDao;
import cn.yxg.yxgCms.dao.DynamicPraiseDao;
import cn.yxg.yxgCms.dao.StudentWorkPraiseDao;
import cn.yxg.yxgCms.dao.TeacherDao;
import cn.yxg.yxgCms.dao.UserConcernDao;
import cn.yxg.yxgCms.dao.UserDao;

import cn.yxg.yxgCms.dao.UserHomepageDao;
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
import cn.yxg.yxgCms.dto.UserListDto;

import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.ClassificationCourseMapping;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseCollection;
import cn.yxg.yxgCms.entity.CourseComment;
import cn.yxg.yxgCms.entity.CourseOrder;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Dynamic;
import cn.yxg.yxgCms.entity.DynamicComment;
import cn.yxg.yxgCms.entity.DynamicPraise;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.Teacher;
import cn.yxg.yxgCms.entity.UserConcern;

import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;
import cn.yxg.yxgCms.service.CourseService;
import cn.yxg.yxgCms.service.DynamicService;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DynamicServiceImpl implements DynamicService{

	@Resource
	DynamicDao dynamicDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	DynamicPraiseDao dynamicPraiseDao;
	
	@Resource
	UserConcernDao userConcernDao;
	
	@Resource
	DynamicCommentDao dynamicCommentDao;
	
	@Resource
	TeacherDao teacherDao;

	@Override
	public DynamicListDto getDynamicList(User currentUser, Integer user,
			Integer borderId, int number) {
		List<Dynamic> dynamicList = dynamicDao.getList(user==null?null:userDao.get(user),borderId,number);
		DynamicListDto dynamicListDto = new DynamicListDto();
		for(Dynamic dynamic:dynamicList){
			DynamicDto dynamicDto = new DynamicDto();
			dynamicDto.convert(dynamic, currentUser, 3,8);
			dynamicListDto.getDynamics().add(dynamicDto);
		}
		
		if(dynamicList.size()>0){
			dynamicListDto.setBorderId(dynamicList.get(dynamicList.size()-1).getId());
		}else{
			dynamicListDto.setBorderId(0);
		}
		
		return dynamicListDto;
	}

	@Override
	public UserListDto getPraiseList(User currentUser, int dynamic,
			Integer borderId, int number) {
		UserListDto userListDto = new UserListDto();
		List<DynamicPraise> dynamicPraiseList = dynamicPraiseDao.getPraiseUsers(dynamicDao.get(dynamic),borderId,number);
		for(DynamicPraise dynamicPraise:dynamicPraiseList){
			Map<String,Object> m = new HashMap<>();
			m.put("userId", dynamicPraise.getUser().getId());
			m.put("nickName", dynamicPraise.getUser().getNickname());
			m.put("avatar", dynamicPraise.getUser().getAvatar());
			m.put("content", dynamicPraise.getUser().getIntroduce());
			if(currentUser==null){
				m.put("hasConcerned", false);
			}else{
				m.put("hasConcerned",userConcernDao.hasConcerned(currentUser, dynamicPraise.getUser()));
			}
			
			userListDto.getList().add(m);
		}
		if(dynamicPraiseList.size()>0){
			userListDto.setBorderId(dynamicPraiseList.get(dynamicPraiseList.size()-1).getId());
		}else{
			userListDto.setBorderId(0);
		}
		return userListDto;
	}

	@Override
	public DynamicDto getDetail(User currentUser, int dynamic) {
		Dynamic dynamicObj = dynamicDao.get(dynamic);
		DynamicDto dynamicDto = new DynamicDto();
		dynamicDto.convert(dynamicObj, currentUser,dynamicObj.getDynamicComments().size(),8);
		return dynamicDto;
	}

	@Override
	public void addDynamic(User currentUser, String content,
			List<String> pictures) {
		Dynamic dynamic = new Dynamic();
		dynamic.setUser(currentUser);
		dynamic.setContent(content);
		dynamic.setUrl(JsonConverter.format(pictures));
		dynamicDao.save(dynamic);
	}

	@Override
	public void addDynamicComment(User currentUser,
			DynamicCommentSubmitDto dynamicCommentSubmitDto) {
		DynamicComment dynamicComment = new DynamicComment();
		dynamicComment.setDynamic(dynamicDao.get(dynamicCommentSubmitDto.getDynamic()));
		dynamicComment.setContent(dynamicCommentSubmitDto.getContent());
		dynamicComment.setUser(currentUser);
		dynamicComment.setReply(dynamicCommentSubmitDto.getReplyUserId()==null?null:userDao.get(dynamicCommentSubmitDto.getReplyUserId()));
		dynamicCommentDao.save(dynamicComment);
	}

	@Override
	public void addDynamicPraise(User currentUser, int dynamic) {
		
		Dynamic dynamicObj = dynamicDao.get(dynamic);
		
		if (dynamicPraiseDao.hasPraised(currentUser, dynamicObj)){
			return;
		}
		
		DynamicPraise dynamicPraise = new DynamicPraise();
		dynamicPraise.setUser(currentUser);
		dynamicPraise.setDynamic(dynamicObj);
		dynamicPraiseDao.save(dynamicPraise);
	}

	@Override
	public boolean deleteDynamic(User currentUser, int dynamic) {
		Dynamic dynamicObj = dynamicDao.get(dynamic);
		if(dynamicObj.getUser()!=currentUser && !teacherDao.isAdmin(currentUser)){
			return false;
		}else{
			dynamicDao.delete(dynamicObj);
			return true;
		}
		
	}

	@Override
	public boolean deleteDynamicComment(User currentUser, int comment) {
		DynamicComment dynamicCommentObj = dynamicCommentDao.get(comment);
		if(dynamicCommentObj.getUser()!=currentUser && !teacherDao.isAdmin(currentUser)){
			return false;
		}else{
			dynamicCommentDao.delete(dynamicCommentObj);
			return true;
		}
	}
	
	
}
