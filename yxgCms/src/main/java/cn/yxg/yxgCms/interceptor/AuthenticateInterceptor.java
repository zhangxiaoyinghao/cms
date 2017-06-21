package cn.yxg.yxgCms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.yxg.yxgCms.service.TokenService;
import javax.annotation.Resource;





/**
 * 
 * 授权验证
 *
 * @author zhangying.
 *         Created 2014-11-27.
 */
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticateInterceptor.class);
	
	@Resource
	private TokenService tokenServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
	                         HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("token");
		if(!tokenServiceImpl.checkToken(token)){
			response.setStatus(403);
			return false;
		}
		return true;
	}
}
