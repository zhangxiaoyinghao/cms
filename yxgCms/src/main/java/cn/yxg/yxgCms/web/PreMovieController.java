package cn.yxg.yxgCms.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.MovieDto;
import cn.yxg.yxgCms.entity.Movie;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.PreMovie;
import cn.yxg.yxgCms.query.ContentQuery;
import cn.yxg.yxgCms.service.MovieService;
import cn.yxg.yxgCms.service.PreCourseService;
import cn.yxg.yxgCms.service.PreMovieService;

@Controller
@RequestMapping("yxgCms/movie")
public class PreMovieController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PreMovieController.class);
	
	
	@Resource
	private PreMovieService preMovieServiceImpl;
	
	@Resource
	private PreCourseService preCourseServiceImpl;
		
	/**
	* @author :zy
	* @Title: listPage 
	* @Description: TODO
	* @param @param model
	* @param @return     
	* @return String     
	* @throws
	 */
	@RequestMapping("listPage")
	public String listPage(ModelMap model) {
		return "site.yxgCms.content.list";
	}
	/**
	 * @author :zy
	 * @Title: infoPage 
	 * @Description: TODO
	 * @param @param model
	 * @param @return     
	 * @return String     
	 * @throws
	 */
	@RequestMapping("infoPage")
	public String infoPage(ModelMap model) {
		return "site.yxgCms.content.info";
	}
	
	/**
	 * 
	 	视频视频查询
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findContents(ContentQuery cq) {
		logger.info("查询视频参数：【" + JsonConverter.format(cq) + "】");
		RestResponse restResponse = new RestResponse();
		try {
			Page page = new Page();
			page.setIndex(cq.getPage() == null ? 0 : cq.getPage());
			if(cq.getPageSize()!=null){
				page.setSize(cq.getPageSize());
			}
			List<PreMovie> movies = preMovieServiceImpl.execList(cq, page);
			// TODO
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询视频成功！");
			restResponse.getBody().put("contents", movies);
			restResponse.getBody().put("page", page);
			restResponse.getBody().put("query", cq);
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
					.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("查询视频异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 * 删除视频
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public RestResponse delContent(@PathVariable int id) {
		logger.info("删除视频参数：【" + id + "】");
		RestResponse restResponse = new RestResponse();
		try {
			preMovieServiceImpl.delete(id);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("删除视频成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("删除视频异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 * 新增视频
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse addContent(@RequestBody @Valid MovieDto dto,BindingResult bResult) {
		logger.info("新增视频参数：【" + JsonConverter.format(dto) + "】");
		RestResponse restResponse = new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
		try {
			PreMovie movie = new PreMovie();
			movie.setCreatetime(new Date());
			movie.setDescription(dto.getDescription());
			movie.setName(dto.getName());
			movie.setSequence(dto.getSequence());
			movie.setUpdatetime(new Date());
			movie.setUrl(dto.getUrl());
			if(dto.getCourse()!=null){
				PreCourse course = preCourseServiceImpl.get(dto.getCourse());
				if(course!=null){
					movie.setCourse(course);
				}
			}
			preMovieServiceImpl.add(movie);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("新增视频成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("新增视频异常！");
		}
		return restResponse;
	}
	
}
