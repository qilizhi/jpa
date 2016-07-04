package com.qlz.shiro;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

@Component("myPermissionsFilter")
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

	public void clearAppliedPaths() {
	    this.appliedPaths.clear();
    }
}
