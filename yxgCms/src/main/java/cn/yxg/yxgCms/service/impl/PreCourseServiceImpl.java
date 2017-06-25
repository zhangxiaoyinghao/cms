package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dao.ClassificationCourseMappingDao;
import cn.yxg.yxgCms.dao.ClassificationDao;
import cn.yxg.yxgCms.dao.CourseCollectionDao;
import cn.yxg.yxgCms.dao.CourseCommentDao;
import cn.yxg.yxgCms.dao.CourseDao;
import cn.yxg.yxgCms.dao.CourseOrderDao;
import cn.yxg.yxgCms.dao.CourseRecommendDao;
import cn.yxg.yxgCms.dao.PreCourseDao;
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
import cn.yxg.yxgCms.dto.PreCourseDto;
import cn.yxg.yxgCms.dto.UserListDto;

import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.entity.ClassificationCourseMapping;
import cn.yxg.yxgCms.entity.ClassificationPreCourseMapping;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.CourseCollection;
import cn.yxg.yxgCms.entity.CourseComment;
import cn.yxg.yxgCms.entity.CourseOrder;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.PreMovie;
import cn.yxg.yxgCms.entity.StudentWork;
import cn.yxg.yxgCms.entity.Teacher;
import cn.yxg.yxgCms.entity.UserConcern;

import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserHomepage;
import cn.yxg.yxgCms.query.CourseQuery;
import cn.yxg.yxgCms.service.CatalogTemplateService;
import cn.yxg.yxgCms.service.ClassificationService;
import cn.yxg.yxgCms.service.CourseService;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.PreCourseService;
import cn.yxg.yxgCms.service.PreMovieService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;
import cn.yxg.yxgCms.util.MD5Util;
import freemarker.template.utility.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreCourseServiceImpl implements PreCourseService{

	@Resource
	private PreCourseDao dao;
	
	@Resource
	private PreMovieService preMovieServiceImpl;
	
	@Resource
	private ClassificationService classificationServiceImpl;
	
	@Resource
	private CourseService courseServiceImpl;
	
	
	@Resource
	private CatalogTemplateService catalogTemplateServiceImpl;
	

	@Autowired  
    private HttpServletRequest request;
	
	@Override
	public PreCourse get(Integer course) {
		// TODO Auto-generated method stub
		return dao.get(course);
	}

	@Override
	public void add(PreCourse course) {
		// TODO Auto-generated method stub
		dao.save(course);
	}
	
	@Override
	public boolean checkMovie(PreCourseDto dto){
		for(int i=0;i<dto.getMovies().size();i++){
			PreMovie movie = preMovieServiceImpl.get(dto.getMovies().get(i));
			if(movie==null){
				return false;
			}
			if(movie.getCourse()!=null){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void execAdd(PreCourseDto dto) {
		// TODO Auto-generated method stub
		PreCourse course = new PreCourse();
		course.setCatalog(dto.getCatalog());
		course.setCreatetime(new Date());
		course.setDescription(dto.getDescription());
		course.setExpiryDay(dto.getExpiryDate());
		for(int i=0;i<dto.getMovies().size();i++){
			PreMovie movie = preMovieServiceImpl.get(dto.getMovies().get(i));
			if(movie!=null){
				movie.setSequence(i+1);
				movie.setCourse(course);
			}
		}
		course.setName(dto.getName());
		course.setPoster(dto.getPoster());
		course.setPrice(dto.getPrice());
		course.setUpdatetime(new Date());
		course.setUuid(UUID.randomUUID().toString());
		Set<ClassificationPreCourseMapping> ccms = new HashSet<>();
		for (Integer id: dto.getClassifications()) {
			ClassificationPreCourseMapping ccm = new ClassificationPreCourseMapping();
			ccm.setClassification(classificationServiceImpl.get(id));
			ccm.setCourse(course);
			ccm.setRoot(classificationServiceImpl.get(dto.getRoot()));
			ccms.add(ccm);
		}
		course.setClassificationPreCourseMappings(ccms);
		
		if(dto.getCatalogTemplateId()!=null){
			course.setCatalogTemplate(catalogTemplateServiceImpl.get(dto.getCatalogTemplateId()));
		}
		
		course.setUser((User)request.getAttribute("userInfo"));
		course.setStatus(0);
		this.add(course);
	}
	@Override
	public void execUpdate(PreCourseDto dto) {
		// TODO Auto-generated method stub
		PreCourse course = this.get(dto.getId());
		if(StringUtils.isNotBlank(dto.getCatalog())){
			course.setCatalog(dto.getCatalog());
		}
		if(StringUtils.isNotBlank(dto.getDescription())){
			course.setDescription(dto.getDescription());
		}
		if(dto.getExpiryDate()!=null){
			course.setExpiryDay(dto.getExpiryDate());
		}
		if(dto.getMovies()!=null){
			for(PreMovie movie : course.getMovies()){
				movie.setCourse(null);
				movie.setSequence(0);
			}
			for(int i=0;i<dto.getMovies().size();i++){
				PreMovie movie = preMovieServiceImpl.get(dto.getMovies().get(i));
				if(movie!=null){
					movie.setSequence(i+1);
					movie.setCourse(course);
				}
			}
		}
		if(StringUtils.isNotBlank(dto.getName())){
			course.setName(dto.getName());
		}
		if(StringUtils.isNotBlank(dto.getPoster())){
			course.setPoster(dto.getPoster());
		}
		if(dto.getPrice()!=null){
			course.setPrice(dto.getPrice());
		}
		course.setUpdatetime(new Date());
		Set<ClassificationPreCourseMapping> ccms = new HashSet<>();
		if(dto.getClassifications()!=null&&dto.getClassifications().size()>0){
			course.getClassificationPreCourseMappings().clear();
			
		}
		for (Integer id: dto.getClassifications()) {
			ClassificationPreCourseMapping ccm = new ClassificationPreCourseMapping();
			ccm.setClassification(classificationServiceImpl.get(id));
			ccm.setCourse(course);
			ccm.setRoot(classificationServiceImpl.get(dto.getRoot()));
			ccms.add(ccm);
		}
		course.getClassificationPreCourseMappings().addAll(ccms);
		
		/**
		 * 更新时编目模板无论是否修改都得传过来
		 */
		if(dto.getCatalogTemplateId()!=null){
			course.setCatalogTemplate(catalogTemplateServiceImpl.get(dto.getCatalogTemplateId()));
			course.setCatalog(dto.getCatalog());
		}else{
			course.setCatalogTemplate(null);
			course.setCatalog(null);
		}
		course.setUser((User)request.getAttribute("userInfo"));
		if(course.getStatus()==1||course.getStatus()==2){
			course.setStatus(3);
		}
		this.update(course);
	}

	@Override
	public void update(PreCourse course) {
		// TODO Auto-generated method stub
		dao.update(course);
	}

	@Override
	public List<PreCourse> execList(CourseQuery cq, Page page) {
		// TODO Auto-generated method stub
		List<PreCourse> courses = dao.list(cq, page);
		page.setRecordCount(dao.count(cq));
		return courses;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		PreCourse course = get(id);
		for(PreMovie movie : course.getMovies()){
			movie.setCourse(null);
		}
		dao.delete(id);
		courseServiceImpl.delete(course.getUuid());
	}

	@Override
	public void execDeploy(PreCourse preCourse) {
		// TODO Auto-generated method stub
		courseServiceImpl.delete(preCourse.getUuid());
		Course course = cloneCourse(preCourse);
		Set<ClassificationCourseMapping> ccms = cloneCCM(preCourse,course);
		List<Movie> movies = cloneMovie(preCourse,course);
		course.setClassificationCourseMappings(ccms);
		course.setMovies(movies);
		courseServiceImpl.save(course);
		
	}

	private List<Movie> cloneMovie(PreCourse preCourse,Course course) {
		// TODO Auto-generated method stub
		List<Movie> movies = new ArrayList<>();
		for(PreMovie preMovie:preCourse.getMovies()){
			Movie movie = new Movie();
			movie.setCourse(course);
			movie.setCreatetime(preMovie.getCreatetime());
			movie.setDescription(preMovie.getDescription());
			movie.setDuration(preMovie.getDuration());
			movie.setName(preMovie.getName());
			movie.setSequence(preMovie.getSequence());
			movie.setUpdatetime(preMovie.getUpdatetime());
			movie.setUrl(preMovie.getUrl());
			movies.add(movie);
		}
		return movies;
	}

	private Set<ClassificationCourseMapping> cloneCCM(PreCourse preCourse,Course course) {
		Set<ClassificationCourseMapping> ccms = new HashSet<>();
		for(ClassificationPreCourseMapping ccpm : preCourse.getClassificationPreCourseMappings()){
			ClassificationCourseMapping ccm = new ClassificationCourseMapping();
			ccm.setClassification(ccpm.getClassification());
			ccm.setCourse(course);
			ccm.setRoot(ccpm.getRoot());
			ccms.add(ccm);
		}
		return ccms;
	}

	private Course cloneCourse(PreCourse course) {
		// TODO Auto-generated method stub
		Course newCourse = new Course();
		newCourse.setCreatetime(course.getCreatetime());
		newCourse.setDescription(course.getDescription());
		newCourse.setExpiryDay(course.getExpiryDay());
		newCourse.setName(course.getName());
		newCourse.setPoster(course.getPoster());
		newCourse.setPrice(course.getPrice());
		newCourse.setUpdatetime(course.getUpdatetime());
		newCourse.setUuid(course.getUuid());
		return newCourse;
	}

	@Override
	public void execStop(PreCourse preCourse) {
		// TODO Auto-generated method stub
		courseServiceImpl.delete(preCourse.getUuid());
	}

	

}
