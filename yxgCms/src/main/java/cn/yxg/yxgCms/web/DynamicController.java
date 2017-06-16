package cn.yxg.yxgAppServer.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgAppServer.dto.DynamicCommentSubmitDto;
import cn.yxg.yxgAppServer.dto.DynamicDto;
import cn.yxg.yxgAppServer.dto.DynamicListDto;
import cn.yxg.yxgAppServer.dto.DynamicSubmitDto;
import cn.yxg.yxgAppServer.dto.ExampleDto;
import cn.yxg.yxgAppServer.dto.MessageVerifyDto;
import cn.yxg.yxgAppServer.dto.UserListDto;
import cn.yxg.yxgAppServer.entity.User;
import cn.yxg.yxgAppServer.enumeration.RestResponseCode;
import cn.yxg.yxgAppServer.service.DynamicService;
import cn.yxg.yxgAppServer.service.SmsService;
import cn.yxg.yxgAppServer.service.UserService;
import cn.yxg.yxgAppServer.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgAppServer/dynamic")
public class DynamicController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DynamicController.class);

	@Resource
	private UserService userServiceImpl;
	
	@Autowired  
    private HttpServletRequest request;
	
	@Resource
	private Properties yxgAppServerConfig;
	
	@Resource
	private DynamicService dynamicServiceImpl;
	
	
	@RequestMapping(value="getList",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse getList(@RequestParam(value="user",required=false) Integer user, @RequestParam(value="number",required=false) Integer number,@RequestParam(value="borderId",required=false) Integer borderId) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			
			DynamicListDto dynamicListDto = dynamicServiceImpl.getDynamicList(currentUser,user,borderId,number==null?Integer.parseInt(yxgAppServerConfig.get("default.page.size").toString()):number);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), dynamicListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="praiseList",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse praiseList(@RequestParam(value="dynamic",required=true) int dynamic, @RequestParam(value="number",required=false) Integer number,@RequestParam(value="borderId",required=false) Integer borderId) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			
			UserListDto userListDto = dynamicServiceImpl.getPraiseList(currentUser,dynamic,borderId,number==null?Integer.parseInt(yxgAppServerConfig.get("default.page.size").toString()):number);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), userListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="detail",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse detail(@RequestParam(value="dynamic",required=true) int dynamic) {
		try{
			User currentUser = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				currentUser = userServiceImpl.getByToken(tokenStr);
			}
			
			DynamicDto dynamicDto = dynamicServiceImpl.getDetail(currentUser,dynamic);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), dynamicDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="submitDynamic",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse submitDynamic(@RequestBody @Valid DynamicSubmitDto dynamicSubmitDto) {
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
			
			dynamicServiceImpl.addDynamic(currentUser, dynamicSubmitDto.getContent(),dynamicSubmitDto.getPictures());
			

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="dynamicComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse dynamicComment(@RequestBody @Valid DynamicCommentSubmitDto dynamicCommentSubmitDto) {
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
			
			dynamicServiceImpl.addDynamicComment(currentUser, dynamicCommentSubmitDto);
			

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}

	@RequestMapping(value="praise",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse praise(@RequestParam(value="dynamic",required=true) int dynamic) {
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
			
			dynamicServiceImpl.addDynamicPraise(currentUser, dynamic);
			

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="deleteDynamic",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse deleteDynamic(@RequestParam(value="dynamic",required=true) int dynamic) {
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
			
			if(dynamicServiceImpl.deleteDynamic(currentUser, dynamic)){
				return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
			}
			
			return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "你不是管理员，无法删除其他人的动态", null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="deleteDynamicComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse deleteDynamicComment(@RequestParam(value="comment",required=true) int comment) {
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
			
			if(dynamicServiceImpl.deleteDynamicComment(currentUser, comment)){
				return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
			}
			
			return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "你不是管理员，无法删除其他人的评论", null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	
}
