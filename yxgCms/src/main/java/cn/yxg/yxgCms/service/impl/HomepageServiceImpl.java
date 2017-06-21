package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.dao.CourseRecommendDao;
import cn.yxg.yxgCms.dao.StudentWorkDao;
import cn.yxg.yxgCms.dao.StudentWorkPraiseDao;
import cn.yxg.yxgCms.dao.UserConcernDao;
import cn.yxg.yxgCms.dao.UserDao;

import cn.yxg.yxgCms.dao.UserHomepageDao;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.UserListDto;

import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.UserConcern;

import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HomepageServiceImpl implements HomepageService{

	@Resource
	CourseRecommendDao courseRecommendDao;
	
	@Resource
	UserHomepageDao userHomepageDao;
	
	@Resource
	StudentWorkDao studentWorkDao;
	
	@Resource
	UserDao userDao ;
	
	@Resource
	UserConcernDao userConcernDao;
	
	@Override
	public HomepageDto getHomepage() {
		HomepageDto homepageDto = new HomepageDto();
		List<CourseRecommend> courseRecommendList = courseRecommendDao.getCourseRecommendList(0);
		for(CourseRecommend courseRecommend:courseRecommendList){
			Map<String,Object> banner = new HashMap<>();
			banner.put("courseId", courseRecommend.getCourse().getId());
			banner.put("poster", courseRecommend.getCourse().getPoster());
			banner.put("externUrl", "");
			homepageDto.getBanners().add(banner);
		}
		
		UserHomepage userHomepage = userHomepageDao.getUserHomepage();
		User user = userHomepage.getUser();
		homepageDto.getStar().put("userId", user.getId());
		homepageDto.getStar().put("nickName", user.getNickname());
		homepageDto.getStar().put("avatar",user.getAvatar());
		homepageDto.getStar().put("studentWorkNumber", user.getStudentWorks().size());
		homepageDto.getStar().put("introduce", user.getIntroduce());
		
		List<Map<String,Object>> studentWorks = new ArrayList<>();
		int count = 0;
		for(StudentWork studentwork:user.getStudentWorks()){
			Map<String,Object> sw = new HashMap<>();
			sw.put("id", studentwork.getId());
			List<String> urls = JsonConverter.parse(studentwork.getUrl(), List.class);
			if(urls.size()<=0){
				sw.put("poster", "");
			}else{
				sw.put("poster", urls.get(0));
			}
			studentWorks.add(sw);
			count++;
			if(count>=3){
				break;
			}
		}
		homepageDto.getStar().put("studentWorks", studentWorks);
		
		//List<Map<String,Object>> greatStudentWorks = new ArrayList<>();
		List<StudentWork> greatStudentWorks =  studentWorkDao.getGreatStudentWorkList();
		for(StudentWork studentWork:greatStudentWorks){
			Map<String,Object> swp = new HashMap<>();
			swp.put("id", studentWork.getId());
			
			List<String> urls = JsonConverter.parse(studentWork.getUrl(), List.class);
			if(urls.size()<=0){
				swp.put("poster", "");
			}else{
				swp.put("poster", urls.get(0));
			}
			
			swp.put("userId", studentWork.getUploader().getId());
			swp.put("title", studentWork.getDescription());
			swp.put("avatar", studentWork.getUploader().getAvatar());
			homepageDto.getGreatHomeworks().add(swp);
		}
		
		homepageDto.setBorderId(0);
		
		
		return homepageDto;
	}

	@Override
	public UserListDto SearchUserList(User user, String keyword,
			Integer borderId, Integer number) {
		List<User> userList = userDao.listByKeyword(keyword,borderId,number);
		UserListDto userListDto = new UserListDto();
		for(User u:userList){
			Map<String,Object> m = new HashMap<>();
			m.put("userId", u.getId());
			m.put("nickName", u.getNickname());
			m.put("avatar", u.getAvatar());
			m.put("content", u.getIntroduce());
			if(user==null){
				m.put("hasConcerned", false);
			}else{
				m.put("hasConcerned",userConcernDao.hasConcerned(user, u));
			}
			userListDto.getList().add(m);
		}
		if(userList.size()>0){
			userListDto.setBorderId(userList.get(userList.size()-1).getId());
		}else{
			userListDto.setBorderId(0);
		}
		return userListDto;
	}

}
