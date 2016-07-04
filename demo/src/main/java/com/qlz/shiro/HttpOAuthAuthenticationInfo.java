package com.qlz.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.MergableAuthenticationInfo;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class HttpOAuthAuthenticationInfo implements MergableAuthenticationInfo,
		AuthenticationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PrincipalCollection principals;
	protected Object credentials;

	public HttpOAuthAuthenticationInfo(Object principal, String userName,
			Object credentials, String realmName) {
		this.principals = new SimplePrincipalCollection(principal, realmName);
		this.credentials = credentials;
	}

	@Override
	public PrincipalCollection getPrincipals() {
		return principals;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
    @SuppressWarnings("unchecked")
	public void merge(AuthenticationInfo info) {
		if (info == null || info.getPrincipals() == null
				|| info.getPrincipals().isEmpty()) {
			return;
		}

		if (this.principals == null) {
			this.principals = info.getPrincipals();
		} else {
			if (!(this.principals instanceof MutablePrincipalCollection)) {
				this.principals = new SimplePrincipalCollection(this.principals);
			}
			((MutablePrincipalCollection) this.principals).addAll(info
					.getPrincipals());
		}

		Object thisCredentials = getCredentials();
		Object otherCredentials = info.getCredentials();

		if (otherCredentials == null) {
			return;
		}

		if (thisCredentials == null) {
			this.credentials = otherCredentials;
			return;
		}

		if (!(thisCredentials instanceof Collection)) {
			Set newSet = new HashSet();
			newSet.add(thisCredentials);
		}
		Collection credentialCollection = (Collection) getCredentials();
		if (otherCredentials instanceof Collection) {
			credentialCollection.addAll((Collection) otherCredentials);
		} else {
			credentialCollection.add(otherCredentials);
		}
	}

	public void setPrincipals(PrincipalCollection principals) {
		this.principals = principals;
	}

	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}

}
