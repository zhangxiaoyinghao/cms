package cn.yxg.yxgAppServer.service.impl;

import javax.annotation.Resource;

import cn.yxg.yxgAppServer.dao.TokenDao;
import cn.yxg.yxgAppServer.dao.UserDao;
import cn.yxg.yxgAppServer.dao.VerificationCodeDao;
import cn.yxg.yxgAppServer.dto.MessageVerifyDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.VerificationCode;
import cn.yxg.yxgAppServer.service.SmsService;
import cn.yxg.yxgAppServer.service.TokenService;
import cn.yxg.yxgAppServer.service.UserService;
import cn.yxg.yxgAppServer.util.CommonUtil;
import cn.yxg.yxgAppServer.util.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService{

	@Resource
	private VerificationCodeDao verificationCodeDao;

	@Override
	public int getSendTimesInOneDay(String phone) {
		// TODO Auto-generated method stub
		return verificationCodeDao.findTimesInOneDay(phone);
	}

	@Override
	public String insertVerifyCode(String phone) {
		String code = CommonUtil.generateSMSCode();
		sendMessage(phone,code);
		
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setPhone(phone);
		verificationCode.setCode(code);
		verificationCode.setExceedTime(DateUtil.getOneMuniteLater());
		verificationCode.setContent("");
		
		verificationCodeDao.save(verificationCode);
		return code;
	}

	private void sendMessage(String phone, String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verify(String phone, String code) {
		int number = verificationCodeDao.getByPhoneAndCode(phone,code);
		return number==0?false:true;
	}



}
