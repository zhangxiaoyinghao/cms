package cn.yxg.yxgCms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.commons.webdev.vo.Page;
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
	 * 进入列表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("listPage")
	public String listPage(Model model) {
		return "site.yxgCms.member.list";
	}

	/**
	 * 获得列表数据
	 * @param nickname
	 * @param username
	 * @param wechatId
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listData", method = RequestMethod.POST)
	public RestResponse listData(
			@RequestParam(value = "nickname") String nickname,
			@RequestParam(value = "username") String username,
			@RequestParam(value="wechatId") String wechatId,
			@RequestParam(value="type") Integer type,
			@RequestParam(value="index") int index,
			@RequestParam(value="pageSize",defaultValue="10") int pagesize) {
		RestResponse response = new RestResponse();
		Page page = new Page();
		page.setIndex(index);
		page.setSize(pagesize);
		long count = userService.listCount(nickname,username,wechatId,type);
		page.setRecordCount(count);
		List<User> list = userService.list(nickname,username,wechatId,type,page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("list", list);
		response.getBody().put("info", map);
		response.setMessage("获取列表信息成功！");
		return response;
	}

}
