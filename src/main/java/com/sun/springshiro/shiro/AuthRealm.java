package com.sun.springshiro.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthRealm extends AuthorizingRealm {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		logger.info("doGetAuthorizationInfo");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.info("doGetAuthenticationInfo");
		 UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		 Map<String, Object> user = new HashMap<String, Object>();
	        String username = new String(userToken.getUsername());
	        String password = new String(userToken.getPassword());
	        user.put("username", username);
	        user.put("password", password);
		 SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.get("password").toString().trim(), getName());
         clearCachedAuthorizationInfo(info.getPrincipals());
         return info;
	}

}
