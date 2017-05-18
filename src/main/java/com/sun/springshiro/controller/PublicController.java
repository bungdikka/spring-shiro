package com.sun.springshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login() {
		String username = "sun";
		String password = "123456";
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
		    //collect user principals and credentials in a gui specific manner
		    //such as username/password html form, X509 certificate, OpenID, etc.
		    //We'll use the username/password example here since it is the most common.
		    //(do you know what movie this is from? ;)
		    UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
		    //this is all you have to do to support 'remember me' (no config - built in!):
		    token.setRememberMe(true);
		    currentUser.login(token);
		}
		//print their identifying principal (in this case, a username): 
		System.out.println( "User [" + currentUser.getPrincipal() + "] logged in successfully." );
		return "未登录，进入登录页";
	}
}
