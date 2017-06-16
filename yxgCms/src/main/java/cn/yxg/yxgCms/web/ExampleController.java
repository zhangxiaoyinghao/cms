/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleController.java
 * Project: yxgAppServer
 * Package: cn.yxg.yxgAppServer.web
 * Author:  Archetype Generate
 */
package cn.yxg.yxgAppServer.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgAppServer.service.ExampleService;
import cn.yxg.yxgAppServer.dto.ExampleDto;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Controller
@RequestMapping("yxgAppServer/example")
public class ExampleController {

	@Resource
	private ExampleService exampleService;


	@RequestMapping("save")
	@ResponseBody
	public RestResponse save(ExampleDto example) {
		//exampleService.save(example.convert());

		RestResponse response = new RestResponse();
		response.setStatusCode(ResponseStatusCode.OK);
		response.setMessage("Save example successfully.");

		return response;
	}
	
	@RequestMapping(value="test/{name}/{content}",method = RequestMethod.GET)
	@ResponseBody
	public RestResponse test(@PathVariable String name, @PathVariable String content) {

		RestResponse response = new RestResponse();
		
		List<ExampleDto> dtoList = new ArrayList<>();
		ExampleDto exampleDto = new ExampleDto();
		exampleDto.setId(1);
		exampleDto.setName(name);
		exampleDto.setContent(content);
		dtoList.add(exampleDto);
		
		response.setStatusCode(ResponseStatusCode.OK);
		response.setMessage("Save example successfully.");
		//response.getBody().put("data", exampleDto);
		response.setData(exampleDto);

		return response;
	}
	
	@RequestMapping(value="test",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse test2(@RequestBody @Valid ExampleDto exampleDto) {

		RestResponse response = new RestResponse();
		
		//List<ExampleDto> dtoList = new ArrayList<>();

		
		response.setStatusCode(ResponseStatusCode.OK);
		response.setMessage("Save example successfully.");
		//response.getBody().put("data", exampleDto);
		response.setData(exampleDto);

		return response;
	}
}
