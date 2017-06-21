/*
 * Copyright © 2010-2013 by Beijing VideoWorks Technology Co., Ltd. All rights reserved.
 * /

/* @(#) ExampleController.java
 * Project: yxgCms
 * Package: cn.yxg.yxgCms.web
 * Author:  Archetype Generate
 */
package cn.yxg.yxgCms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Archetype Generate
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 */
@Controller
@RequestMapping("yxgCms")
public class ExampleController {

	@RequestMapping("")
	public String home(ModelMap model) {
		return "site.yxgCms.index.index";
	}
	
	
}
