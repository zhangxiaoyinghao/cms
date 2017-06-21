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
import cn.yxg.yxgCms.dto.DynamicListDto;
import cn.yxg.yxgCms.dto.ExampleDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.HomeworkListDto;
import cn.yxg.yxgCms.dto.IdListDto;
import cn.yxg.yxgCms.dto.LoginDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.StudentWorkCommentDto;
import cn.yxg.yxgCms.dto.SubmitStudentWorkDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.enumeration.RestResponseCode;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.HomeworkService;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.ResponseUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgCms/homework")
public class HomeworkController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeworkController.class);
	
	@Resource
	private UserService userServiceImpl;
	
	@Resource
	private SmsService smsServiceImpl;
	
	@Resource
	private HomeworkService homeworkServiceImpl;
	
	@Autowired  
    private HttpServletRequest request;
	
	@Resource
	private Properties yxgCmsConfig;
	
	
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
			return "site.yxgCms.homework.list";
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
			return "site.yxgCms.homework.info";
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="getHomeworkList",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse getHomeworkList(@RequestParam(value="number",required=false) Integer number, @RequestParam(value="borderId",required=false) Integer borderId) {
		try{
			HomeworkListDto homeworkDto = homeworkServiceImpl.getHomework(borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), homeworkDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="getStudentWorks",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse getStudentWorks(@RequestParam(value="homework",required=true) int homework, @RequestParam(value="number",required=false) Integer number, @RequestParam(value="borderId",required=false) Integer borderId) {
		try{
			
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			
			DynamicListDto dynamicListDto = homeworkServiceImpl.getStudentWorksByTime(currentUser,homework,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), dynamicListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="getStudentWorksIdByPraise",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse getStudentWorksIdByPraise(@RequestParam(value="homework",required=true) int homework) {
		try{
			
			List<Integer> studentWorksIdList = homeworkServiceImpl.getStudentWorksIdByPraise(homework);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), studentWorksIdList);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="getStudentWorksByIdList",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse getStudentWorksByIdList(@RequestBody @Valid IdListDto idListDto) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			
			DynamicListDto dynamicListDto = homeworkServiceImpl.getStudentWorksByIdList(currentUser,idListDto);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), dynamicListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="submitStudentWork",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse submitStudentWork(@RequestBody @Valid SubmitStudentWorkDto submitStudentWorkDto) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			if(currentUser==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			homeworkServiceImpl.addStudentWork(currentUser, submitStudentWorkDto);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="studentWorkComment",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse studentWorkComment(@RequestBody @Valid StudentWorkCommentDto studentWorkCommentDto) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			if(currentUser==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			homeworkServiceImpl.addStudentWorkComment(currentUser, studentWorkCommentDto);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="studentWorkPraise",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse studentWorkPraise(@RequestParam(value="studentWork",required=true) int studentWork) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			if(currentUser==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			homeworkServiceImpl.addStudentWorkPraise(currentUser, studentWork);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="deleteStudentWork",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse deleteStudentWork(@RequestParam(value="studentWork",required=true) int studentWork) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			if(currentUser==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			if(homeworkServiceImpl.deleteStudentWork(currentUser, studentWork)){
				return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
			}
			return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "你不是管理员，无法删除其他人的作品", null);
			
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="deleteStudentWorkComment",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse deleteStudentWorkComment(@RequestParam(value="studentWorkComment",required=true) int studentWorkComment) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			if(currentUser==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			if(homeworkServiceImpl.deleteStudentWorkComment(currentUser, studentWorkComment)){
				return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
			}
			return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "你不是管理员，无法删除其他人的评论", null);
			
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
}
