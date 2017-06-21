package cn.yxg.yxgCms.util;

import java.util.Random;



import org.springframework.stereotype.Component;
import java.util.UUID;


@Component
public class CommonUtil {  
	
	
	/**
	 * 
	 * 随机生成验证码
	 *
	 * @return
	 */
	public static String generateSMSCode(){
		Random random = new Random();
		StringBuilder result = new StringBuilder();
		for(int i=0;i<4;i++){
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	
	public static String generateUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
	
}  
