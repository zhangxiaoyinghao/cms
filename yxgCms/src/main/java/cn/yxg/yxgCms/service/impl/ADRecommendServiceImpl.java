package cn.yxg.yxgCms.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.CourseRecommendDao;
import cn.yxg.yxgCms.dao.UserConcernDao;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.UserConcern;
import cn.yxg.yxgCms.service.ADRecommendService;
import cn.yxg.yxgCms.service.PreCourseService;
import cn.yxg.yxgCms.service.UserService;

@Service
public class ADRecommendServiceImpl implements ADRecommendService{

	@Resource
	private CourseRecommendDao dao;
	
	
	@Resource
	private UserConcernDao userConcerndao;
	
	@Resource
	private PreCourseService preCourseServiceImpl;
	
	@Resource
	private UserService userServiceImpl;
	

	@Autowired  
    private HttpServletRequest request;

	@Override
	public List<CourseRecommend> list() {
		// TODO Auto-generated method stub
		String hql = "from CourseRecommend order by place,sequence";
		return dao.findByHQL(hql, 0, 0);
	}
	
	@Override
	public void execUpdate(int place, int count) {
		// TODO Auto-generated method stub
		List<CourseRecommend> crs = findByType(place);
		int maxSeq = 1;
		if(crs.size()<count){
			if(crs.size()>0){
				maxSeq = crs.get(crs.size()-1).getSequence();
			}
			for(int i=0;i<count-crs.size();i++){
				CourseRecommend cr = new CourseRecommend();
				cr.setPlace(place);
				cr.setSequence(maxSeq++);
				dao.save(cr);
			}
		}else if(crs.size()>count){
			int len = crs.size();
			for(int i=0;i<count-crs.size();i++){
				dao.delete(crs.get(--len));
			}
		}
	}
	
	private List<CourseRecommend> findByType(int place) {
		// TODO Auto-generated method stub
		String hql = "from CourseRecommend where place = ? order by sequence asc";
		return dao.findByHQL(hql,0,0,place);
	}

	@Override
	public void update(int id, int courseId) {
		// TODO Auto-generated method stub
		CourseRecommend cr = dao.get(id);
		PreCourse course = preCourseServiceImpl.get(courseId);
		cr.setCourse(course);
		cr.setUser((User)request.getAttribute("userInfo"));
		dao.update(cr);
	}

	@Override
	public UserConcern userConcern() {
		// TODO Auto-generated method stub
		return userConcerndao.list().get(0);
	}

	@Override
	public void updateUserConcern(int id, int userId) {
		// TODO Auto-generated method stub
		UserConcern userConcern = userConcerndao.get(id);
		User user = userServiceImpl.get(userId);
		userConcern.setUser(user);
		userConcerndao.update(userConcern);
	}

	
	
	
}
