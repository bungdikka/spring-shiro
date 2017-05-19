package com.sun.springshiro.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/hello")
	@ResponseBody
	public Object hello() {
		return "Hello World!";
	}
	
	@RequestMapping("/loginPage")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String username,String password) {
		logger.info("username="+username+" | password="+password);
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
		    //collect user principals and credentials in a gui specific manner
		    //such as username/password html form, X509 certificate, OpenID, etc.
		    //We'll use the username/password example here since it is the most common.
		    //(do you know what movie this is from? ;)
		    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		    //this is all you have to do to support 'remember me' (no config - built in!):
//		    token.setRememberMe(true);
		    currentUser.login(token);
		}
		//print their identifying principal (in this case, a username): 
		System.out.println( "User [" + currentUser.getPrincipal() + "] logged in successfully." );
		Map<String,Object> map = new HashMap<String,Object>();
		if(currentUser.isAuthenticated()){
			map.put("status", 1);
			map.put("msg", "success");
		} else {
			map.put("status", 0);
			map.put("msg", "failure");
		}
		return map;
	}
}
