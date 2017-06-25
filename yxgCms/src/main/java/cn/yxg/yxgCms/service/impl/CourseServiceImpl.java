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
import cn.yxg.yxgCms.dao.StudentWorkPraiseDao;
import cn.yxg.yxgCms.dao.UserConcernDao;
import cn.yxg.yxgCms.dao.UserDao;

import cn.yxg.yxgCms.dao.UserHomepageDao;
import cn.yxg.yxgCms.dto.CategoryPageDto;
import cn.yxg.yxgCms.dto.CourseCommentListDto;
import cn.yxg.yxgCms.dto.CourseDetailDto;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.dto.CourseHomepageDto;
import cn.yxg.yxgCms.dto.CourseListDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.UserListDto;

import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.ClassificationCourseMapping;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseCollection;
import cn.yxg.yxgCms.entity.CourseComment;
import cn.yxg.yxgCms.entity.CourseOrder;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.Teacher;
import cn.yxg.yxgCms.entity.UserConcern;

import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;
import cn.yxg.yxgCms.service.CourseService;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

	@Resource
	CourseRecommendDao courseRecommendDao;
	
	@Resource
	ClassificationDao classificationDao;
	
	@Resource
	CourseDao courseDao;
	
	@Resource
	CourseOrderDao courseOrderDao;
	
	@Resource
	ClassificationCourseMappingDao classificationCourseMappingDao;
	
	@Resource
	private Properties yxgCmsConfig;
	
	@Resource
	CourseCommentDao courseCommentDao;
	
	@Resource
	CourseCollectionDao courseCollectionDao;
	
	@Override
	public CourseHomepageDto getCourseHomepage() {
		CourseHomepageDto courseHomepageDto = new CourseHomepageDto();
//		List<CourseRecommend> courseRecommendList = courseRecommendDao.getCourseRecommendList(1);
//		for(CourseRecommend courseRecommend:courseRecommendList){
//			Map<String,Object> banner = new HashMap<>();
//			banner.put("courseId", courseRecommend.getCourse().getId());
//			banner.put("poster", courseRecommend.getCourse().getPoster());
//			courseHomepageDto.getBanners().add(banner);
//		}
//		List<CourseRecommend> artsRecommendList = courseRecommendDao.getCourseRecommendList(2);
//		for(CourseRecommend artsRecommend:artsRecommendList){
//			Map<String,Object> art = new HashMap<>();
//			art.put("id", artsRecommend.getCourse().getId());
//			art.put("poster", artsRecommend.getCourse().getPoster());
//			art.put("title", artsRecommend.getCourse().getName());
//			art.put("studentNumber", artsRecommend.getCourse().getCourseOrders().size());
//			courseHomepageDto.getArts().add(art);
//		}
//		List<CourseRecommend> musicRecommendList = courseRecommendDao.getCourseRecommendList(3);
//		for(CourseRecommend musicRecommend:musicRecommendList){
//			Map<String,Object> music = new HashMap<>();
//			music.put("id", musicRecommend.getCourse().getId());
//			music.put("poster", musicRecommend.getCourse().getPoster());
//			music.put("title", musicRecommend.getCourse().getName());
//			music.put("studentNumber", musicRecommend.getCourse().getCourseOrders().size());
//			courseHomepageDto.getMusic().add(music);
//		}
//		List<CourseRecommend> danceRecommendList = courseRecommendDao.getCourseRecommendList(4);
//		for(CourseRecommend danceRecommend:danceRecommendList){
//			Map<String,Object> dance = new HashMap<>();
//			dance.put("id", danceRecommend.getCourse().getId());
//			dance.put("poster", danceRecommend.getCourse().getPoster());
//			dance.put("title", danceRecommend.getCourse().getName());
//			dance.put("studentNumber", danceRecommend.getCourse().getCourseOrders().size());
//			courseHomepageDto.getDance().add(dance);
//		}
		
		return courseHomepageDto;
	}

	@Override
	public CategoryPageDto getCategoryPage(int category) {
		Classification root = classificationDao.get(category);
		CategoryPageDto categoryPageDto = new CategoryPageDto();
//		List<Classification> list = classificationDao.getNodeClassifications(root);
//		for(Classification l:list){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", l.getId());
//			m.put("value", l.getName());
//			if(l.getParent().getKey().equals("subCategory")){
//				categoryPageDto.getSubCategory().add(m);
//			}else if(l.getParent().getKey().equals("difficulty")){
//				categoryPageDto.getDifficulty().add(m);
//			}else if(l.getParent().getKey().equals("age")){
//				categoryPageDto.getAge().add(m);
//			}else if(l.getParent().getKey().equals("purpose")){
//				categoryPageDto.getPurpose().add(m);
//			}else if(l.getParent().getKey().equals("price")){
//				categoryPageDto.getPrice().add(m);
//			}
//		}
//		List<Course> courseList = courseDao.getCoursesByCategory(root);
//		for(Course course:courseList){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", course.getId());
//			m.put("poster", course.getPoster());
//			m.put("title", course.getName());
//			m.put("studentNumber", course.getCourseOrders().size());
//			categoryPageDto.getList().add(m);
//		}
//		if(courseList.size()>0){
//			categoryPageDto.setBorderId(courseList.get(courseList.size()-1).getId());
//		}else{
//			categoryPageDto.setBorderId(0);
//		}
		
		return categoryPageDto;
	}

	@Override
	public CourseListDto getCourseList(String keyword,Integer borderId,Integer number) {
		CourseListDto courseListDto = new CourseListDto();
//		List<Course> courseList = courseDao.getCourseList(keyword,borderId,number);
//		for(Course course:courseList){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", course.getId());
//			m.put("poster", course.getPoster());
//			m.put("title", course.getName());
//			m.put("studentNumber", course.getCourseOrders().size());
//			courseListDto.getList().add(m);
//		}
//		if(courseList.size()>0){
//			courseListDto.setBorderId(courseList.get(courseList.size()-1).getId());
//		}
		return courseListDto;
	}

	@Override
	public CourseListDto getCourseListByFilter(
			CourseFilterInputDto courseFilterInputDto) {
		CourseListDto courseListDto = new CourseListDto();
//		List<Course> courseList = courseDao.getCourseListByFilter(classificationDao.get(courseFilterInputDto.getCategory()),
//				courseFilterInputDto.getAge()==null?null:classificationDao.get(courseFilterInputDto.getAge()),
//				courseFilterInputDto.getDifficulty()==null?null:classificationDao.get(courseFilterInputDto.getDifficulty()),
//				courseFilterInputDto.getPrice()==null?null:classificationDao.get(courseFilterInputDto.getPrice()),
//				courseFilterInputDto.getPurpose()==null?null:classificationDao.get(courseFilterInputDto.getPurpose()),
//				courseFilterInputDto.getSubCategory()==null?null:classificationDao.get(courseFilterInputDto.getSubCategory()),
//				courseFilterInputDto.getBorderId(),
//				 courseFilterInputDto.getNumber()==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):courseFilterInputDto.getNumber());
//		for(Course course:courseList){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", course.getId());
//			m.put("poster", course.getPoster());
//			m.put("title", course.getName());
//			m.put("studentNumber", course.getCourseOrders().size());
//			courseListDto.getList().add(m);
//		}
//		if(courseList.size()>0){
//			courseListDto.setBorderId(courseList.get(courseList.size()-1).getId());
//		}else{
//			courseListDto.setBorderId(0);
//		}
		return courseListDto;

	}

	@Override
	public CourseDetailDto getCourseDetail(int course, User user) {
		CourseDetailDto courseDetailDto = new CourseDetailDto();
//		Course courseObj = courseDao.get(course);
//		courseDetailDto.setDescription(courseObj.getDescription());
//		courseDetailDto.setPrice(courseObj.getPrice());
//		courseDetailDto.setTitle(courseObj.getName());
//		courseDetailDto.setStudentNumber(courseObj.getCourseOrders().size());
//		for (Teacher teacher: courseObj.getTeachers()){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", teacher.getUser().getId());
//			m.put("name", teacher.getName());
//			m.put("title", teacher.getTitle());
//			m.put("introduction", teacher.getDescription());
//			m.put("avatar", teacher.getPoster());
//			courseDetailDto.getTeacher().add(m);
//		}
//		String ageString = "";
//		String purposeString = "";
//		Classification root = null;
//		for(ClassificationCourseMapping ccm:courseObj.getClassificationCourseMappings()){
//			if(ccm.getClassification().getParent().getKey().equals("age")){
//				ageString += ccm.getClassification().getName() + ";";
//			}
//			if(ccm.getClassification().getParent().getKey().equals("purpose")){
//				purposeString += ccm.getClassification().getName() + ";";
//			}
//			root = ccm.getRoot();
//		}
//		if(!ageString.equals("")){
//			ageString = ageString.substring(0,ageString.length()-1);
//		}
//		if(!purposeString.equals("")){
//			purposeString = purposeString.substring(0,ageString.length()-1);
//		}
//		courseDetailDto.setAge(ageString);
//		courseDetailDto.setPurpose(purposeString);
//		
//		for(Movie movie:courseObj.getMovies()){
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", movie.getId());
//			m.put("name", movie.getName());
//			m.put("url", movie.getUrl());
//			m.put("duration", movie.getDuration());
//			courseDetailDto.getVideos().add(m);
//		}
//		
//		if(root!=null){
//			for(Object[] result :courseDao.getRelatedCourse(root)){
//				Course c = courseDao.get((int)result[0]);
//				Map<String,Object> m = new HashMap<>();
//				m.put("id", c.getId());
//				m.put("poster",c.getPoster());
//				m.put("title", c.getName());
//				m.put("studentNumber", (long)result[1]);
//				courseDetailDto.getRelatedCourses().add(m);
//			}
//		}
//		if(user==null){
//			courseDetailDto.setJoin(false);
//		}else{
//			courseDetailDto.setJoin(courseOrderDao.hasJoined(user,courseObj));
//		}
		return courseDetailDto;
	}

	@Override
	public CourseCommentListDto getCourseCommentList(int course,
			Integer borderId, Integer number) {
		CourseCommentListDto courseCommentListDto = new CourseCommentListDto();
		courseCommentListDto.setTotalNumber(courseCommentDao.getCount(courseDao.get(course)));
		
		List<CourseComment> courseCommentList = courseCommentDao.getCommentList(courseDao.get(course),borderId,number);
		for(CourseComment courseComment:courseCommentList){
			Map<String,Object> m = new HashMap<>();
			m.put("userId", courseComment.getUser().getId());
			m.put("nickName", courseComment.getUser().getNickname());
			m.put("avatar", courseComment.getUser().getAvatar());
			m.put("createTime", courseComment.getCreatetime());
			m.put("content", courseComment.getContent());
			courseCommentListDto.getComments().add(m);
		}
		if(courseCommentList.size()>0){
			courseCommentListDto.setBorderId(courseCommentList.get(courseCommentList.size()-1).getId());
		}else{
			courseCommentListDto.setBorderId(0);
		}
		
		return courseCommentListDto;
	}

	@Override
	public void addCourseOrder(User user, int course) {
		Course courseObj = courseDao.get(course);
		if(courseOrderDao.hasJoined(user,courseObj)){
			return;
		}
		CourseOrder courseOrder = new CourseOrder();
		courseOrder.setCourse(courseObj);
		courseOrder.setOrderCode(CommonUtil.generateUuid());
		courseOrder.setPrice((float)0.0);
		courseOrder.setStatus(1);
		courseOrder.setUser(user);
		courseOrderDao.save(courseOrder);
	}

	@Override
	public void addCourseComment(User user, Integer course, String content) {
		CourseComment courseComment = new CourseComment();
		courseComment.setUser(user);
		courseComment.setCourse(courseDao.get(course));
		courseComment.setContent(content);
		courseCommentDao.save(courseComment);
	}

	@Override
	public void addCourseCollect(User user, int course) {
		Course courseObj = courseDao.get(course);
		
		if(courseCollectionDao.hasCollected(user, courseObj)){
			return;
		}
		
		CourseCollection courseCollection = new CourseCollection();
		courseCollection.setCourse(courseObj);
		courseCollection.setUser(user);
		courseCollectionDao.save(courseCollection);
		
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
	}

	@Override
	public void delete(String uuid) {
		// TODO Auto-generated method stub
		Course course = find(uuid);
		courseDao.delete(course);
	}
	@Override
	public Course find(String uuid) {
		// TODO Auto-generated method stub
		String hql = "from Course where uuid=?";
		List<Course> courses = courseDao.findByHQL(hql, 0, 0, uuid);
		if(courses!=null&&courses.size()>0){
			return courses.get(0);
		}
		return null;
	}



}
