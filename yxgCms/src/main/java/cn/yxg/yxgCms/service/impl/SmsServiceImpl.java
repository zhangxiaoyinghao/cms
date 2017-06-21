package cn.yxg.yxgCms.service.impl;

import javax.annotation.Resource;

import cn.yxg.yxgCms.dao.TokenDao;
import cn.yxg.yxgCms.dao.UserDao;
import cn.yxg.yxgCms.dao.VerificationCodeDao;
import cn.yxg.yxgCms.dto.MessageVerifyDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.VerificationCode;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.TokenService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.CommonUtil;
import cn.yxg.yxgCms.util.DateUtil;

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
