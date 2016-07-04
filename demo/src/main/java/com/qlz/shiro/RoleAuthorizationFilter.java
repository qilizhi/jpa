package com.qlz.shiro;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlz.util.StringUtil;



/**
 * 角色验证扩展过滤器
 * 
 * @author quan
 * 
 */
public class RoleAuthorizationFilter extends AuthorizationFilter {

	private static Logger logger = LoggerFactory.getLogger( RoleAuthorizationFilter.class );

	/**
	 * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 */
	@Override
	protected boolean onAccessDenied( ServletRequest request, ServletResponse response ) throws IOException {
		Subject subject = getSubject( request, response );
		if( subject.getPrincipal() == null ) {
			// 未登录则先保存当前路径并跳转到登录页面
			saveRequestAndRedirectToLogin( request, response );
		}
		else {
			String unauthorizedUrl = getUnauthorizedUrl();
			if( StringUtils.hasText( unauthorizedUrl ) ) {
				WebUtils.issueRedirect( request, response, unauthorizedUrl );
			}
			else {
				WebUtils.toHttp( response ).sendError( HttpServletResponse.SC_UNAUTHORIZED );
			}
		}
		return false;
	}

	@Override
	protected void redirectToLogin( ServletRequest request, ServletResponse response ) throws IOException {
		// super.redirectToLogin( request, response );
		String loginUrl = getLoginUrl();
		SavedRequest savedRequest = WebUtils.getSavedRequest( request );
		String saveReqUrl = savedRequest != null ? savedRequest.getRequestUrl() : null;
		if( !StringUtil.empty( saveReqUrl ) ) {
			// 判断如果链接中有/admin则表示访问的是平台页面
			// 判断如果链接中有/guideAdmin则表示访问的是商家后台页面
			String[] _strRegexs = new String[]{"\\/admin|\\/admin\\/","\\/guideAdmin|\\/guideAdmin\\/"};
			String[] _strUrls = new String[]{"/platformLogin?returnUrl=" + saveReqUrl,"/guideLogin?returnUrl=" + saveReqUrl};
			for( int i = 0; i < _strRegexs.length; i++ ) {
				Pattern pattern = Pattern.compile( _strRegexs[i] );
				Matcher matcher = pattern.matcher( saveReqUrl );
				if(matcher.find()) {
					// 重新赋值登录链接
					loginUrl = _strUrls[i];
					break;
				}
            }
		}
		logger.info( "Redirect : " + loginUrl );
		// 重定向
		WebUtils.issueRedirect( request, response, loginUrl );
	}

	/**
	 * 验证是否拥有某个角色
	 */
	@Override
	protected boolean isAccessAllowed( ServletRequest request, ServletResponse response, Object mappedValue ) throws Exception {

		Subject subject = getSubject( request, response );
		String[] rolesArray = (String[])mappedValue;

		if( rolesArray == null || rolesArray.length == 0 ) {
			// 无角色就返回false
			logger.info( "role is null" );
			return false;
		}

		Set<String> roles = CollectionUtils.asSet( rolesArray );
		for( String role : roles ) {
			if( subject.hasRole( role ) ) {
				return true;
			}
		}
		logger.info( "not has role !" );
		return false;
	}

}
