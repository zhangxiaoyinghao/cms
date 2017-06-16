package cn.yxg.yxgAppServer.service;

import cn.yxg.yxgAppServer.dto.MessageVerifyDto;

public interface SmsService {

	int getSendTimesInOneDay(String phone);

	String insertVerifyCode(String phone);

	boolean verify(String phone, String code);

}
