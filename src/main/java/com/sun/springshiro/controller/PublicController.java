package com.sun.springshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public Object hello() {
		return "Hello World!";
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login() {
		return "未登录，进入登录页";
	}
}
