package cn.yxg.yxgCms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.ConfDao;
import cn.yxg.yxgCms.entity.Conf;
import cn.yxg.yxgCms.service.ConfService;

@Service
public class ConfServiceImpl implements ConfService{

	@Resource
	private ConfDao dao;

	@Override
	public Conf findByKey(String key) {
		// TODO Auto-generated method stub
		String hql = "from Conf where key = ?";
		List<Conf> confs = dao.findByHQL(hql,0, 0, key);
		if(confs!=null&&confs.size()>0){
			return confs.get(0);
		}
		return null;
	}
	
	
}
