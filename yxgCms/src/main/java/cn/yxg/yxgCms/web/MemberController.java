package cn.yxg.yxgCms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.MemberDto;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.service.UserService;

/**
 * 
 * 会员管理
 */
@Controller
@RequestMapping("yxgCms/member")
public class MemberController {

	@Resource
	private UserService userService;

	/**
	 * 获得列表数据
	 * @param nickname
	 * @param username
	 * @param wechatId
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listData")
	public RestResponse listData(
			@RequestParam(value = "nickname") String nickname,
			@RequestParam(value = "username") String username,
			@RequestParam(value="wechatId") String wechatId,
			@RequestParam(value="type") Integer type,
			@RequestParam(value="index") int index,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize) {
		RestResponse response = new RestResponse();
		Page page = new Page();
		page.setIndex(index);
		page.setSize(pageSize);
		long count = userService.listCount(nickname,username,wechatId,type);
		page.setRecordCount(count);
		List<Map<String,Object>> list = userService.list(nickname,username,wechatId,type,page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("list", list);
		response.setMessage("获取列表信息成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		response.setData(map);
		return response;
	}
	
	/**
	 * 获得会员详情
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "info/{userId}")
	public RestResponse userInfo(@PathVariable(value="userId") Integer userId) {
		RestResponse response = new RestResponse();
		User user = userService.get(userId);
		if(user==null){
			response.setMessage("该用户不存在！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		response.setMessage("获取用户信息成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		response.setData(userService.userToMap(user));
		return response;
	}
	
	/**
	 * 会员修改
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update",method=RequestMethod.POST)
	public RestResponse updateUser(@RequestBody MemberDto memberDto) {
		RestResponse response = new RestResponse();
		if(null == memberDto){
			response.setMessage("参数有误！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		User user = userService.get(memberDto.getId());
		if(null== user){
			response.setMessage("对应用户不存在！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		user.setUsername(memberDto.getUsername());
		user.setNickname(memberDto.getNickName());
		user.setEnable(memberDto.getEnable());
		user.setType(memberDto.getType());
		if(memberDto.getType()==1){
			user.getTeacher().setName(memberDto.getTeacherName());
		}
		userService.update(user);
		response.setMessage("更新用户信息成功");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

}
