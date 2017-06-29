package cn.yxg.yxgCms.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.TeacherDao;
import cn.yxg.yxgCms.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Resource
	private TeacherDao teacherDao;

	@Override
	public long listCount(String name, Integer classification,
			Boolean auditStatus) {
		return teacherDao.listCount(name,classification,auditStatus);
	}

}
