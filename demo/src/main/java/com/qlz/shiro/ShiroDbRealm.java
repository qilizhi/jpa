/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.qlz.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qlz.constant.Const;
import com.qlz.entities.Resource;
import com.qlz.entities.Role;
import com.qlz.entities.User;
import com.qlz.service.UserService;
import com.qlz.util.Digests;
import com.qlz.util.Encodes;
import com.qlz.util.StringUtil;

@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	@Autowired
	private UserService userService;



	/**
	 * ��֤�ص�����,��¼ʱ����.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		String password = token.getPassword() == null ? null : String.valueOf(token.getPassword());
		try {
	
			User userInfo = new User();
			userInfo.setMobile(token.getUsername());
			List<User> lsUserInfos = userService.findByExample(userInfo);
			if (lsUserInfos == null || lsUserInfos.isEmpty()) {
				return null;
			}
			userInfo = lsUserInfos.get(0);
	
			if (!checkPwd(userInfo.getSalt(), password, userInfo.getPassword())) {
				return null;
			}
			// TODO:��Ҫ����������ܴ��������֤
			ShiroUser user1 = new ShiroUser(userInfo.getMobile(), userInfo.getName(), userInfo.getUserNo(),
					userInfo.getOpenId());
			HttpOAuthAuthenticationInfo ai = new HttpOAuthAuthenticationInfo(user1, token.getUsername(), true,
					user1.getName());
			return ai;
		} catch (AuthenticationException e) {
			logger.error("��֤��¼����: " + e.getMessage());
			throw new AuthenticationException(e.getMessage());
		}
	}

	/**
	 * ��Ȩ��ѯ�ص�����, ���м�Ȩ�����������û�����Ȩ��Ϣʱ����.�����л��������£�ֻ����һ��.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// ��ȡ��¼ʱ������û���
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();

		// �����ݿ���Ƿ��д˶���
		if (shiroUser != null) {
			// Ȩ����Ϣ����info,������Ų�����û������еĽ�ɫ��role����Ȩ�ޣ�permission��
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// �û��Ľ�ɫ����
			Set<String> roles = new HashSet<String>();
			roles.add(shiroUser.role);
			// ����ϵͳ���û�����Ľ�ɫ��
			List<Role> rolesList = userService.getRolesByUserNo(shiroUser.getUserNo());
			for (Role r : rolesList) {
				roles.add(r.getName());
			}

			info.setRoles(roles);
			// �û��Ľ�ɫ��Ӧ������Ȩ�ޣ����ֻʹ�ý�ɫ�������Ȩ�ޣ���������п��Բ�Ҫ
			List<String> perms = new ArrayList<String>();
			if (info != null && info.getStringPermissions() != null)
				perms.addAll(info.getStringPermissions());
			if (perms != null && perms.size() <= 0) {
				logger.info("perms is null");
				perms.add("undefined:*");// ��Ȩ��ʱĬ�ϸ���Ȩ��
			}
			perms.add("supplierAdmin:view");
			List<Resource> resources = userService.getResourcesByUserNo(shiroUser.getUserNo());
			for (Resource r : resources) {
				perms.add(r.getPath());
			}
			info.addStringPermissions(perms);
			logger.info("�û�Ȩ�ޣ�"+info.getStringPermissions().toString());
			logger.info("�û���ɫ��"+info.getRoles().toString());
			return info;
		}
		return null;
	}

	/**
	 * ��������Ƿ�ƥ��
	 * 
	 * @param cmlSalt
	 * @param inputPwd
	 * @param cmlPwd
	 * @return
	 */
	private boolean checkPwd(String cmlSalt, String inputPwd, String cmlPwd) {
		if (StringUtil.empty(cmlSalt) || StringUtil.empty(inputPwd) || StringUtil.empty(cmlPwd)) {
			return false;
		}
		String salt = StringUtil.stringValue(cmlSalt, "");
		String hexPwd = Encodes.encodeHex(Digests.sha1(inputPwd.getBytes(), Encodes.decodeHex(salt), Const.HASH_INTERATIONS));
		return (cmlPwd != null && !StringUtil.empty(hexPwd) && cmlPwd.equals(hexPwd));
	}

	/**
	 * �����û���Ȩ��Ϣ����.
	 */
	public void clearCachedAuthorizationInfo(Object principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * ��������û���Ȩ��Ϣ����.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * �趨PasswordУ���Hash�㷨���������.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		// HashedCredentialsMatcher matcher = new
		// HashedCredentialsMatcher(Const.HASH_ALGORITHM);
		// matcher.setHashIterations(Const.HASH_INTERATIONS);

		HttpCredentialsMatcher matcher = new HttpCredentialsMatcher();
		setCredentialsMatcher(matcher);
	}

	/**
	 * �Զ���Authentication����ʹ��Subject����Я���û��ĵ�¼���⻹����Я��������Ϣ.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;

		public String loginName;

		public String name;

		public String userNo;

		public String openid;

		public String role;

		public ShiroUser(String loginName, String name, String userNo, String openid) {
			this.loginName = loginName;
			this.name = name;
			this.userNo = userNo;
		
			this.openid = openid;
		}

		public String getName() {
			return name;
		}

		public String getUserNo() {
			return userNo;
		}

		public String getRole() {
			return role;
		}

		public String getOpenid() {
			return openid;
		}

		/**
		 * �������������ΪĬ�ϵ�<shiro:principal/>���.
		 */
		@Override
		public String toString() {
			return name;
		}

		/**
		 * ����hashCode,ֻ����loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * ����equals,ֻ����loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
		
	
	}

	/**
	 * �õ���ǰ��¼�û�
	 * 
	 * @return
	 */
	public static ShiroUser getLoginUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * ȡ��Shiro�еĵ�ǰ�û�userNo.
	 */
	public static String getCurrentUserNo() {
		ShiroUser user = getLoginUser();
		if (user != null) {
			return user.getUserNo();
		}
		return null;
	}

}
