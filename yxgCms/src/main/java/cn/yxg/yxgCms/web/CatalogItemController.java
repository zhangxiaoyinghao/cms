/* @(#) CatalogItemController.java
 * Project:	appserver
 * Package: cn.videoworks.videofact.web
 * Author:	Luochuan
 * Date:	12/23/13
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 */
package cn.yxg.yxgCms.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.CatalogItemDto;
import cn.yxg.yxgCms.dto.CatalogItemWsDto;
import cn.yxg.yxgCms.entity.CatalogItem;
import cn.yxg.yxgCms.service.CatalogItemService;




/**
 * 编目项访问控制类。
 * 
 * @author LuoChuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@RequestMapping("yxgCms/catalogItem")
public class CatalogItemController {

	@Resource
	private CatalogItemService catalogItemService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return "site.yxgCms.item.edit";
	}

	/**
	 * 保存编目项
	 * 
	 * @param catalogItemDto
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Model model, @Valid CatalogItemDto catalogItemDto) {
		model.addAttribute("result",
				catalogItemService.save(catalogItemDto.convert()));
		return "site.yxgCms.item.list";
	}

	/**
	 * 跳转到编目项编辑页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, int id) {

		CatalogItem catalogItem = catalogItemService.findById(id);
		model.addAttribute("catalogItem", catalogItem);
		return "site.yxgCms.item.edit";
	}

	/**
	 * 更新编目项
	 * 
	 * @param catalogItemDto
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse update(@Valid @RequestBody CatalogItemDto catalogItemDto) {
		RestResponse response = new RestResponse();
		try{

			catalogItemService.update(catalogItemDto.convert());

			response.setStatusCode(ResponseStatusCode.OK);
			response.setMessage("更新编目项成功");	
		}catch(Exception e){
			response.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			response.setMessage("更新编目项失败");
		}
		return response;
	}

	/**
	 * 查询编目项列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model) {

		List<CatalogItem> catalogItems = catalogItemService.list();

		model.addAttribute("catalogItems", catalogItems);

		return "site.yxgCms.item.list";
	}

	/**
	 * 跳转到添加编目项页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
//		List<String> types = JsonConverter.parse(videofactProperties.get("catalog.item.category").toString(),List.class);
//		model.addAttribute("listTypes", types);
		
		return "site.yxgCms.item.add";
	}

	/**
	 * 根据id删除编目项
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(Integer id) {
		catalogItemService.delete(id);
		return "site.yxgCms.item.list";
	}

	/**
	 * 根据查询条件
	 * 
	 * @param catalogItemWsDto
	 * @return
	 */
	@RequestMapping(value = "getCatalogItems")
	@ResponseBody
	public RestResponse getCatalogItems(
			@RequestBody CatalogItemWsDto catalogItemWsDto) {
		RestResponse ajaxResponse = new RestResponse();
		if (catalogItemWsDto != null) {
			Map<String, Object> data = new HashMap<String, Object>();
			ajaxResponse.setStatusCode(ResponseStatusCode.OK);
			ajaxResponse.setMessage("执行成功");
			data.put("catalogItemList",
					catalogItemService.list(catalogItemWsDto));
			ajaxResponse.setData(data);
		}else{
			ajaxResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			ajaxResponse.setMessage("失败，请求参数有误");
		}
		return ajaxResponse;
	}
}
