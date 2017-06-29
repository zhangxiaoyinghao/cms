package cn.yxg.yxgCms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.service.TeacherService;

@Controller
@RequestMapping("yxgCms/teacher")
public class TeacherController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherController.class);
	
	@Resource
	private TeacherService teacherService;
	
	
/**
* @Title: listPage 
* @Description: TODO
* @param @param model
* @param @return     
* @return String     
* @throws
 */
	@RequestMapping("listPage")
	public String listPage(ModelMap model) {
		return "site.yxgCms.teacher.list";
	}
	
	/**
	 * 获得列表数据
	 * @param username
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listData")
	public RestResponse listData(
			@RequestParam(value = "name") String name,
			@RequestParam(value="classification") Integer classification,
			@RequestParam(value="auditStatus") Boolean auditStatus,
			@RequestParam(value="index") int index,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize) {
		RestResponse response = new RestResponse();
		Page page = new Page();
		page.setIndex(index);
		page.setSize(pageSize);
		long count = teacherService.listCount(name,classification,auditStatus);
//		page.setRecordCount(count);
//		List<Map<String,Object>> list = userService.list(nickname,username,wechatId,type,page);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("page", page);
//		map.put("list", list);
//		response.setMessage("获取列表信息成功！");
//		response.setStatusCode(ResponseStatusCode.OK);
//		response.setData(map);
		return response;
	}
	
	/**
	 * @Title: infoPage 
	 * @Description: TODO
	 * @param @param model
	 * @param @return     
	 * @return String     
	 * @throws
	 */
	@RequestMapping("infoPage")
	public String infoPage(ModelMap model) {
		return "site.yxgCms.teacher.info";
	}
	
}
