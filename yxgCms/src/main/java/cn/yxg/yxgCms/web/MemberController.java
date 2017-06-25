package cn.yxg.yxgCms.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yxg.yxgCms.service.UserService;

/**
 * 
 *会员管理
 */
@Controller
@RequestMapping("yxgCms/member")
public class MemberController {
	
	@Resource
	private UserService userService;
	
	

}
