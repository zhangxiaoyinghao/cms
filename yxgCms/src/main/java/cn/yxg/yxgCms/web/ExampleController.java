/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleController.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.web
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.web;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.service.ExampleService;
import cn.yxg.yxgCms.dto.ExampleDto;
import cn.yxg.yxgCms.entity.Example;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Controller
@RequestMapping("yxgCms/example")
public class ExampleController {

	@Resource
	private ExampleService exampleService;

	@RequestMapping("index")
	public String index(ModelMap model) {
		List<Example> examples = exampleService.list();
		model.addAttribute("examples", examples);
		return "site.yxgCms.example.index";
	}

	@RequestMapping("save")
	@ResponseBody
	public RestResponse save(ExampleDto example) {
		exampleService.save(example.convert());

		RestResponse response = new RestResponse();
		response.setStatusCode(ResponseStatusCode.OK);
		response.setMessage("Save example successfully.");

		return response;
	}
}
