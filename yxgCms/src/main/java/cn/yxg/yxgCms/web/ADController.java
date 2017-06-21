package cn.yxg.yxgCms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("yxgCms/ad")
public class ADController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ADController.class);
	
	
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
		return "site.yxgCms.ad.list";
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
		return "site.yxgCms.ad.info";
	}
	
}
