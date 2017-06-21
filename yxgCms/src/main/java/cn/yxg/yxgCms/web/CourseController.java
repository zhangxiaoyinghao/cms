package cn.yxg.yxgCms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.CategoryPageDto;
import cn.yxg.yxgCms.dto.CourseCommentListDto;
import cn.yxg.yxgCms.dto.CourseCommentSubmitDto;
import cn.yxg.yxgCms.dto.CourseDetailDto;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.dto.CourseHomepageDto;
import cn.yxg.yxgCms.dto.CourseListDto;
import cn.yxg.yxgCms.dto.ExampleDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.LoginDto;
import cn.yxg.yxgCms.dto.MessageVerifyDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.enumeration.RestResponseCode;
import cn.yxg.yxgCms.service.CourseService;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.ResponseUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgCms/course")
public class CourseController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);
	
	@Resource
	private UserService userServiceImpl;
	
	@Resource
	private SmsService smsServiceImpl;
	
	@Resource
	private HomepageService homepageServiceImpl;
	
	@Autowired  
    private HttpServletRequest request;
	
	@Resource
	private Properties yxgCmsConfig;
	
	@Resource
	private CourseService courseServiceImpl; 
	

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
		return "site.yxgCms.course.list";
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
		return "site.yxgCms.course.info";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="homepage",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse homepage() {
		try{
			CourseHomepageDto courseHomepageDto = courseServiceImpl.getCourseHomepage();
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseHomepageDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="listByCategory",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse listByCategory(@RequestParam(value="category",required=true) int category) {
		try{

			CategoryPageDto categoryPageDto = courseServiceImpl.getCategoryPage(category);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), categoryPageDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="search",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse search(@RequestParam(value="keyword",required=false) String keyword, @RequestParam(value="borderId",required=false) Integer borderId,  @RequestParam(value="number",required=false) Integer number) {
		try{

			CourseListDto courseListDto = courseServiceImpl.getCourseList(keyword,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse list(@RequestBody @Valid CourseFilterInputDto courseFilterInputDto) {
		try{

			CourseListDto courseListDto = courseServiceImpl.getCourseListByFilter(courseFilterInputDto);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="getCourseDetail",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse getCourseDetail(@RequestParam(value="course",required=true) int course) {
		try{
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			CourseDetailDto courseDetailDto = courseServiceImpl.getCourseDetail(course,user);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseDetailDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="getCourseComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse getCourseComment(@RequestParam(value="course",required=true) int course,@RequestParam(value="borderId",required=false) Integer borderId,  @RequestParam(value="number",required=false) Integer number) {
		try{
			
			CourseCommentListDto courseCommentListDto = courseServiceImpl.getCourseCommentList(course,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseCommentListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="join",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse join(@RequestParam(value="course",required=true) int course) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseOrder(user,course);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="submitComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse submitComment(@RequestBody @Valid CourseCommentSubmitDto courseCommentSubmitDto) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseComment(user,courseCommentSubmitDto.getCourse(),courseCommentSubmitDto.getContent());
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="collect",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse collect(@RequestParam(value="course",required=true) int course) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseCollect(user,course);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
}
