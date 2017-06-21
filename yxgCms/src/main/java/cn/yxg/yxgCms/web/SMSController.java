package cn.yxg.yxgCms.web;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.ExampleDto;
import cn.yxg.yxgCms.dto.MessageVerifyDto;
import cn.yxg.yxgCms.enumeration.RestResponseCode;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.ResponseUtil;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("yxgCms/sms")
public class SMSController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SMSController.class);
	
	@Resource
	private SmsService smsServiceImpl;
	
	@RequestMapping(value="send",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse isRgist(@RequestParam(value="phone",required=true) String phone) {
		try{
			int times = smsServiceImpl.getSendTimesInOneDay(phone);
			if(times>=5){
				return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "您已超过一天内发送短信数量上限", null);
			}
			String code = smsServiceImpl.insertVerifyCode(phone);
			Map<String,String>m = new HashMap();
			m.put("code", code);
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), m);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="verify",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse verify(@RequestBody @Valid MessageVerifyDto messageVerifyDto) {
		try{
			if(smsServiceImpl.verify(messageVerifyDto.getPhone(),messageVerifyDto.getCode())){
				return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
			}
			return ResponseUtil.setRestResponse(RestResponseCode.OTHERERROR, "验证码和手机号不匹配或超时", null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
}
