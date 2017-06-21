package cn.yxg.yxgCms.service;

import cn.yxg.yxgCms.dto.MessageVerifyDto;

public interface SmsService {

	int getSendTimesInOneDay(String phone);

	String insertVerifyCode(String phone);

	boolean verify(String phone, String code);

}
