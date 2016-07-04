package com.qlz.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class HttpCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		System.out.println("加密"+info.toString()+":"+token.toString());
		
		return (Boolean) info.getCredentials();
	}

}
