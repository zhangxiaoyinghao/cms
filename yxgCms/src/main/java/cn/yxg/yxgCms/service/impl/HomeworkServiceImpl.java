package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.dao.CourseRecommendDao;
import cn.yxg.yxgCms.dao.HomeworkDao;
import cn.yxg.yxgCms.dao.StudentWorkCommentDao;
import cn.yxg.yxgCms.dao.StudentWorkDao;
import cn.yxg.yxgCms.dao.StudentWorkPraiseDao;
import cn.yxg.yxgCms.dao.TeacherDao;
import cn.yxg.yxgCms.dao.UserConcernDao;
import cn.yxg.yxgCms.dao.UserDao;

import cn.yxg.yxgCms.dao.UserHomepageDao;
import cn.yxg.yxgCms.dto.DynamicDto;
import cn.yxg.yxgCms.dto.DynamicListDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.HomeworkListDto;
import cn.yxg.yxgCms.dto.IdListDto;
import cn.yxg.yxgCms.dto.StudentWorkCommentDto;
import cn.yxg.yxgCms.dto.SubmitStudentWorkDto;
import cn.yxg.yxgCms.dto.UserListDto;

import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Dynamic;
import cn.yxg.yxgCms.entity.Homework;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.StudentWorkComment;
import cn.yxg.yxgCms.entity.StudentWorkPraise;
import cn.yxg.yxgCms.entity.UserConcern;

import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.HomeworkService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService{

	@Resource
	CourseRecommendDao courseRecommendDao;
	
	@Resource
	HomeworkDao homeworkDao;
	
	@Resource
	StudentWorkDao studentWorkDao;
	
	@Resource
	StudentWorkCommentDao studentWorkCommentDao;
	
	@Resource
	StudentWorkPraiseDao studentWorkPraiseDao;
	
	@Resource
	UserDao userDao ;
	
	@Resource
	TeacherDao teacherDao;

	@Override
	public HomeworkListDto getHomework(Integer borderId, int number) {
		HomeworkListDto homeworkDto = new HomeworkListDto();
		List<Homework> homeworkList = homeworkDao.getHomeworks(borderId,number);
		for(Homework homework:homeworkList){
			Map<String,Object> m = new HashMap<>();
			m.put("id", homework.getId());
			m.put("title", homework.getName());
			m.put("poster", JsonConverter.parse(homework.getUrl(),List.class));
			m.put("createTime", homework.getCreatetime());
			m.put("teacherName", homework.getCreator().getName());
			m.put("description", homework.getDescription());
			m.put("worksNumber", homework.getStudentWorks().size());
			homeworkDto.getHomeworks().add(m);
		}
		if(homeworkList.size()>0){
			homeworkDto.setBorderId(homeworkList.get(homeworkList.size()-1).getId());
		}else{
			homeworkDto.setBorderId(0);
		}
		return homeworkDto;
	}

	@Override
	public DynamicListDto getStudentWorksByTime(User currentUser, int homework, Integer borderId,
			int number) {
		
		List<StudentWork> studentWorkList = studentWorkDao.getList(homeworkDao.get(homework),borderId,number);
		DynamicListDto dynamicListDto = new DynamicListDto();
		for(StudentWork studentWork:studentWorkList){
			DynamicDto dynamicDto = new DynamicDto();
			dynamicDto.convert(studentWork, currentUser, 3,8);
			dynamicListDto.getDynamics().add(dynamicDto);
		}
		
		if(studentWorkList.size()>0){
			dynamicListDto.setBorderId(studentWorkList.get(studentWorkList.size()-1).getId());
		}else{
			dynamicListDto.setBorderId(0);
		}
		
		return dynamicListDto;
	}

	@Override
	public List<Integer> getStudentWorksIdByPraise(int homework) {
		List<Integer> studentWorksIdList = studentWorkDao.getAllIdsByPraise(homeworkDao.get(homework));
		
		return studentWorksIdList;
	}

	@Override
	public DynamicListDto getStudentWorksByIdList(User currentUser,
			IdListDto idListDto) {

		List<StudentWork> studentWorkList = new ArrayList<>();
		for(Integer id:idListDto.getIds()){
			studentWorkList.add(studentWorkDao.get(id));
		}
		
		DynamicListDto dynamicListDto = new DynamicListDto();
		for(StudentWork studentWork:studentWorkList){
			DynamicDto dynamicDto = new DynamicDto();
			dynamicDto.convert(studentWork, currentUser, 3,8);
			dynamicListDto.getDynamics().add(dynamicDto);
		}
		
		if(studentWorkList.size()>0){
			dynamicListDto.setBorderId(studentWorkList.get(studentWorkList.size()-1).getId());
		}else{
			dynamicListDto.setBorderId(0);
		}
		
		return dynamicListDto;
	}

	@Override
	public void addStudentWork(User currentUser,
			SubmitStudentWorkDto submitStudentWorkDto) {
		
		StudentWork studentWork = new StudentWork();
		studentWork.setDescription(submitStudentWorkDto.getContent());
		studentWork.setHomework(homeworkDao.get(submitStudentWorkDto.getHomework()));
		studentWork.setUploader(currentUser);
		studentWork.setUrl(JsonConverter.format(submitStudentWorkDto.getPictures()));
		
		studentWorkDao.save(studentWork);
		
	}

	@Override
	public void addStudentWorkComment(User currentUser,
			StudentWorkCommentDto studentWorkCommentDto) {
		StudentWorkComment studentWorkComment = new StudentWorkComment();
		studentWorkComment.setContent(studentWorkCommentDto.getContent());
		studentWorkComment.setStudentWork(studentWorkDao.get(studentWorkCommentDto.getStudentWork()));
		studentWorkComment.setUser(currentUser);
		studentWorkCommentDao.save(studentWorkComment);
	}

	@Override
	public void addStudentWorkPraise(User currentUser, int studentWork) {
		StudentWork studentWorkObj = studentWorkDao.get(studentWork);
		
		if (studentWorkPraiseDao.hasPraised(currentUser, studentWorkObj)){
			return;
		}
		
		StudentWorkPraise studentWorkPraise = new StudentWorkPraise();
		studentWorkPraise.setStudentWork(studentWorkObj);
		studentWorkPraise.setUser(currentUser);
		studentWorkPraiseDao.save(studentWorkPraise);
		
	}

	@Override
	public boolean deleteStudentWork(User currentUser, int studentWork) {
		StudentWork studentWorkObj = studentWorkDao.get(studentWork);
		if(studentWorkObj.getUploader()!=currentUser && !teacherDao.isAdmin(currentUser)){
			return false;
		}else{
			studentWorkDao.delete(studentWorkObj);
			return true;
		}
	}

	@Override
	public boolean deleteStudentWorkComment(User currentUser,
			int studentWorkComment) {
		
		StudentWorkComment studentWorkCommentObj = studentWorkCommentDao.get(studentWorkComment);
		if(studentWorkCommentObj.getUser()!=currentUser && !teacherDao.isAdmin(currentUser)){
			return false;
		}else{
			studentWorkCommentDao.delete(studentWorkCommentObj);
			return true;
		}
	}
	

}
