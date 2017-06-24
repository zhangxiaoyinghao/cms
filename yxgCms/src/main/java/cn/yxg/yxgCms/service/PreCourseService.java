package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.PreCourseDto;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.query.CourseQuery;

public interface PreCourseService {

	PreCourse get(Integer course);

	void add(PreCourse course);

	void execAdd(PreCourseDto dto);

	void execUpdate(PreCourseDto dto);

	void update(PreCourse course);

	boolean checkMovie(PreCourseDto dto);

	List<PreCourse> execList(CourseQuery cq, Page page);

	void delete(int id);

	void execDeploy(PreCourse course);

}
