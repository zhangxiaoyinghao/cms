/* @(#) CatalogItemService.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.service
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.PreMovie;
import cn.yxg.yxgCms.query.ContentQuery;

public interface PreMovieService {

	List<PreMovie> execList(ContentQuery cq, Page page);

	void delete(int id);

	void add(PreMovie movie);

	PreMovie get(Integer mId);

	

}
