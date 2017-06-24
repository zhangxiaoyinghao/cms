/**
 * 
 */
package cn.yxg.yxgCms.web;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.CatalogTemplateDto;
import cn.yxg.yxgCms.entity.CatalogTemplate;
import cn.yxg.yxgCms.service.CatalogItemService;
import cn.yxg.yxgCms.service.CatalogTemplateService;
import cn.yxg.yxgCms.util.CommonUtil;


/**
 * 编目模版控制层
 * 
 * @author lge
 * 
 */
@RequestMapping("yxgCms/catalogTemplate")
@Controller
public class CatalogTemplateController {

	@Resource
	private CatalogTemplateService catalogTemplateServiceImpl;

	@Resource
	private CatalogItemService catalogItemServiceImpl;
	
	@Resource
	private CommonUtil commonUtil;
	
	/**
	 * 添加编目模版
	 * 
	 * @param catalogTemplateDto
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public RestResponse save(@RequestBody CatalogTemplateDto catalogTemplateDto) {
		RestResponse ajaxResponse = new RestResponse();
		
		if (catalogTemplateDto != null) {
			CatalogTemplate catalogTemplate = catalogTemplateDto.convert();
			catalogTemplate.setCreateTime(new Date());
			
			try {
				CatalogTemplate catalogTemplateExists = catalogTemplateServiceImpl.getCatalogTemplateByName(catalogTemplate.getName());
				if(catalogTemplateExists!=null && catalogTemplateExists.isEnable()){
					ajaxResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
					ajaxResponse.setMessage("该模板名已存在。");
				}else{
					catalogTemplateServiceImpl.save(catalogTemplate);
					ajaxResponse.setStatusCode(ResponseStatusCode.OK);
					ajaxResponse.setMessage("添加成功。");
				}
			} catch (Exception e) {
				ajaxResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
				ajaxResponse.setMessage("添加失败。");
			}
		}
		return ajaxResponse;
	}

	/**
	 * 更新编目模版
	 * 
	 * @param catalogTemplateDto
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public RestResponse update(@RequestBody CatalogTemplateDto catalogTemplateDto) {
		RestResponse ajaxResponse = new RestResponse();
		if (catalogTemplateDto != null) {
			CatalogTemplate catalogTemplate = catalogTemplateDto.convert();
			try {
				CatalogTemplate catalogTemplateExists = catalogTemplateServiceImpl.getCatalogTemplateByName(catalogTemplate.getName());
				if(catalogTemplateExists!=null && catalogTemplateExists.getId()!=catalogTemplate.getId() && catalogTemplateExists.getName().equals(catalogTemplate.getName()) && catalogTemplateExists.isEnable()){
					ajaxResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
					ajaxResponse.setMessage("该模板名已存在");
				}else{
					catalogTemplateServiceImpl.update(catalogTemplate);
					ajaxResponse.setStatusCode(ResponseStatusCode.OK);
					ajaxResponse.setMessage("更新成功");
				}
			} catch (Exception e) {
				ajaxResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
				ajaxResponse.setMessage("更新失败");
			}
		}
		return ajaxResponse;
	}

	/**
	 * 得到编目模版列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
//		if (commonUtil.checkAuthorization(new String[] { "vf_catalogTemplate_manage" })) {
//			return commonUtil.returnAuthorizationError();
//		}
		model.addAttribute("catalogTemplates",
				catalogTemplateServiceImpl.list());
		return "site.yxgCms.catalogTemplate.list";
	}

	/**
	 * 跳转到添加编目模版页
	 * 
	 * @return
	 */
	@RequestMapping(value = "addPage")
	public String addPage(Model model) {
		model.addAttribute("catalogItems", catalogItemServiceImpl.list());
		return "site.yxgCms.catalogTemplate.add";
	}

	/**
	 * 跳转到编目项页
	 * 
	 * @return
	 */
	@RequestMapping(value = "toCatalogItem")
	public String toCatalogItem(Model model) {
		model.addAttribute("catalogItems", catalogItemServiceImpl.list());
		return "site.yxgCms.item.list";
	}

	/**
	 * 跳转到编目模版编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editPage")
	public String editPage(Model model, Integer id,boolean look) {
		model.addAttribute("catalogTemplate",
				catalogTemplateServiceImpl.get(id));
		model.addAttribute("catalogItems", catalogItemServiceImpl.list());
		model.addAttribute("look", look);
		return "site.yxgCms.catalogTemplate.edit";
	}

	/**
	 * 删除编目模版
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ResponseBody
	public RestResponse delete(Integer id) {
		RestResponse response = new RestResponse();
		catalogTemplateServiceImpl.delete(id);
		response.setMessage("删除编目模板成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
}
