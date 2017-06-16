package cn.yxg.yxgAppServer.web;

import java.util.HashMap;
import java.util.Map;

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
import cn.yxg.yxgAppServer.dto.ExampleDto;
import cn.yxg.yxgAppServer.dto.LoginDto;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.User;
import cn.yxg.yxgAppServer.enumeration.RestResponseCode;
import cn.yxg.yxgAppServer.service.SmsService;
import cn.yxg.yxgAppServer.service.UserService;
import cn.yxg.yxgAppServer.util.ResponseUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgAppServer/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Resource
	private UserService userServiceImpl;
	
	@Resource
	private SmsService smsServiceImpl;
	
	@Autowired  
    private HttpServletRequest request;
	
	@RequestMapping(value="isRegist",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse isRegist(@RequestParam(value="phone",required=true) String phone) {
		try{
			boolean result = userServiceImpl.checkUserByPhone(phone);
			Map<String,Boolean>m = new HashMap();
			m.put("isRegist", result);
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), m);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="regist",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse regist(@RequestBody @Valid RegistDto registDto) {
		try{
			if(userServiceImpl.checkUserByPhone(registDto.getPhone())){
				return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "该用户已经注册", null);
			}
			if(!smsServiceImpl.verify(registDto.getPhone(), registDto.getCode())){
				return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR1, "验证码不匹配", null);
			}
			
			UserInfoDto userInfoDto = userServiceImpl.insertUser(registDto);
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), userInfoDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse login(@RequestBody @Valid LoginDto loginDto) {
		try{
			UserInfoDto userInfoDto = userServiceImpl.saveTokenLogin(loginDto.getPhone(),loginDto.getPassword());
			if(null==userInfoDto){
				return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "用户名密码不匹配", null);
			}

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), userInfoDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="wechatLogin",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse wechatLogin(@RequestBody @Valid WechatLoginDto wechatLoginDto) {
		try{
			UserInfoDto userInfoDto = userServiceImpl.saveTokenWechatLogin(wechatLoginDto);

			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), userInfoDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="logout",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse logout() {
		try{
			String tokenStr = request.getHeader("token");
			User user = userServiceImpl.getByToken(tokenStr);
			if(user==null){
				logger.error("token:"+tokenStr+"对应的用户不存在");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}

			userServiceImpl.deleteToken(user);
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	
}
