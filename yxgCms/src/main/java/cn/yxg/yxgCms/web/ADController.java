package cn.yxg.yxgCms.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.UserConcern;
import cn.yxg.yxgCms.service.ADRecommendService;

@Controller
@RequestMapping("yxgCms/ad")
public class ADController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ADController.class);
	
	
	@Resource
	private ADRecommendService adRecommendServiceImpl;
	
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
		return "site.yxgCms.ad.list";
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
		return "site.yxgCms.ad.info";
	}
	
	/**
	 * 
	 	用户之星
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "/userConcern", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findUserConcern() {
		logger.info("查询用户之星接口");
		RestResponse restResponse = new RestResponse();
		try {
			UserConcern userConcern = adRecommendServiceImpl.userConcern();
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询用户之星成功！");
			restResponse.getBody().put("content", userConcern);
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("查询用户之星异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 	修改用户之星
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "userConcern/{id}/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse updateUserConcern(@PathVariable int id,@PathVariable int userId) {
		logger.info("修改用户之星参数：id="+id+",courseId="+userId);
		RestResponse restResponse = new RestResponse();
		try {
			adRecommendServiceImpl.updateUserConcern(id,userId);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("修改用户之星成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("修改用户之星异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 	广告位查询
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findContents() {
		logger.info("查询广告位接口");
		RestResponse restResponse = new RestResponse();
		try {
			List<CourseRecommend> courseRecommends = adRecommendServiceImpl.list();
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询广告位成功！");
			restResponse.getBody().put("contents", courseRecommends);
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
					.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("查询广告位异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 	修改广告位
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "{id}/{courseId}", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse updateAD(@PathVariable int id,@PathVariable int courseId) {
		logger.info("修改广告位参数：id="+id+",courseId="+courseId);
		RestResponse restResponse = new RestResponse();
		try {
			adRecommendServiceImpl.update(id,courseId);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("修改广告位成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("修改广告位异常！");
		}
		return restResponse;
	}
	
	
}
