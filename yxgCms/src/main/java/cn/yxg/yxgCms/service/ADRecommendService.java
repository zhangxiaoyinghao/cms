package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.UserConcern;

public interface ADRecommendService {

	List<CourseRecommend> list();

	void update(int id, int courseId);

	UserConcern userConcern();

	void updateUserConcern(int id, int userId);

	void execUpdate(int type, int count);

}
