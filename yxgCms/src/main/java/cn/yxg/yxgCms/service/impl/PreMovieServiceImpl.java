/* @(#) CatalogItemServiceImpl.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service.impl
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dao.MovieDao;
import cn.yxg.yxgCms.dao.PreMovieDao;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.PreMovie;
import cn.yxg.yxgCms.query.ContentQuery;
import cn.yxg.yxgCms.service.MovieService;
import cn.yxg.yxgCms.service.PreMovieService;

@Service
public class PreMovieServiceImpl implements PreMovieService {

	@Resource
	private PreMovieDao dao;

	@Override
	public List<PreMovie> execList(ContentQuery cq, Page page) {
		// TODO Auto-generated method stub
		List<PreMovie> movies = dao.list(cq, page);
		page.setRecordCount(dao.count(cq));
		return movies;
	}

	@Override
	public void detele(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}


}
