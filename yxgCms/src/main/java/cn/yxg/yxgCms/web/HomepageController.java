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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.ExampleDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.LoginDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.CourseRecommend;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.enumeration.RestResponseCode;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.ResponseUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgCms/homepage")
public class HomepageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomepageController.class);
	
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
	
	@RequestMapping(value="homepage",method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse homepage() {
		try{
			HomepageDto homepageDto = homepageServiceImpl.getHomepage();
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), homepageDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="search",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse search(@RequestParam(value="keyword",required=false) String keyword, @RequestParam(value="borderId",required=false) Integer borderId,  @RequestParam(value="number",required=false) Integer number) {
		try{
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}

			UserListDto userListDto = homepageServiceImpl.SearchUserList(user,keyword,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), userListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	
}
