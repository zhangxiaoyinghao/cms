package cn.yxg.yxgAppServer.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import cn.yxg.commons.util.json.JsonConverter;

public class EasemobUtil {
	
	//@Resource
	//private RestTemplate restTemplate;
	
	private static String clientId = "YXA6ulpHMGNqEeW5lmvz1Cq2qA";
	
	private static String clientSecret = "YXA6AGfWtNV4MGvZaWCmobbM3DEw8gU";
	
	private static String orgName = "shoutuikeji";
	
	private static String appName = "ttkongjian";
	
	private static String baseUrl = "http://a1.easemob.com";
	
	private static String token = "";
	
	public static Map<String, Object> getToken(){

		RestTemplate restTemplate = new RestTemplate();
		String url = baseUrl + "/"+orgName+"/"+appName + "/token";
		HttpHeaders headers =new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		
		HttpEntity request = new HttpEntity(params,headers);
		
		String response = restTemplate.postForObject(url, request, String.class);
		
		Map<String,Object> res = null;
		if (response!=null){
			res = JsonConverter.parse(response, Map.class);
			token = res.get("access_token").toString();
		}
		return res;
	}
	
	
}
