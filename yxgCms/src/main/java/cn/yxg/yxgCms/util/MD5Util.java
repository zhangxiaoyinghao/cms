package cn.yxg.yxgCms.util;


import java.security.MessageDigest;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {

	private static final Logger log = LoggerFactory.getLogger(MD5Util.class);

	public final static String MD5(String pwd) {
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = pwd.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = md5String[byte0 >>> 4 & 0xf];
				str[k++] = md5String[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error("MD5 exception!", e);
			return null;
		}
	}
}
