/**
 * 
 */
package cn.yxg.yxgCms.interceptor;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用于页面导航高亮需要的拦截器
 * 
 * @author zy
 * 
 */
public class RequestAnalyzerInterceptor extends HandlerInterceptorAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// Set the page forum according the request URI.
		String[] requestPath = new String[]{"","","","","","",""};
		String[] paths = request.getRequestURI().split("/");
		System.arraycopy(paths,0,requestPath,0,paths.length);
		request.setAttribute("requestPath", requestPath);
		return true;
	}

}
