package cn.yxg.yxgCms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("yxgCms/teacher")
public class TeacherController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherController.class);
	
	
/**
* @author :zy
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
	 * @author :zy
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
