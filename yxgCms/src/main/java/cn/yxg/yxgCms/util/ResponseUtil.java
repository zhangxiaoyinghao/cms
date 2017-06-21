package cn.yxg.yxgCms.util;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.enumeration.RestResponseCode;

public class ResponseUtil {
	public static RestResponse setRestResponse(RestResponseCode code, String message, Object data){
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(code.getValue());
		restResponse.setMessage(message);
		restResponse.setData(data);
		return restResponse;
	}
}
