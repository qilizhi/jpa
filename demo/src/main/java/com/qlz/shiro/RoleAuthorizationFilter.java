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
 * ��ɫ��֤��չ������
 * 
 * @author quan
 * 
 */
public class RoleAuthorizationFilter extends AuthorizationFilter {

	private static Logger logger = LoggerFactory.getLogger( RoleAuthorizationFilter.class );

	/**
	 * ��ʾ�����ʾܾ�ʱ�Ƿ��Ѿ������ˣ��������true��ʾ��Ҫ���������������false��ʾ��������ʵ���Ѿ������ˣ���ֱ�ӷ��ؼ���
	 * 
	 * @param request
	 * @param response
	 * @return true ������������false ����Ҫ��������
	 */
	@Override
	protected boolean onAccessDenied( ServletRequest request, ServletResponse response ) throws IOException {
		Subject subject = getSubject( request, response );
		if( subject.getPrincipal() == null ) {
			// δ��¼���ȱ��浱ǰ·������ת����¼ҳ��
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
			// �ж������������/admin���ʾ���ʵ���ƽ̨ҳ��
			// �ж������������/guideAdmin���ʾ���ʵ����̼Һ�̨ҳ��
			String[] _strRegexs = new String[]{"\\/admin|\\/admin\\/","\\/guideAdmin|\\/guideAdmin\\/"};
			String[] _strUrls = new String[]{"/platformLogin?returnUrl=" + saveReqUrl,"/guideLogin?returnUrl=" + saveReqUrl};
			for( int i = 0; i < _strRegexs.length; i++ ) {
				Pattern pattern = Pattern.compile( _strRegexs[i] );
				Matcher matcher = pattern.matcher( saveReqUrl );
				if(matcher.find()) {
					// ���¸�ֵ��¼����
					loginUrl = _strUrls[i];
					break;
				}
            }
		}
		logger.info( "Redirect : " + loginUrl );
		// �ض���
		WebUtils.issueRedirect( request, response, loginUrl );
	}

	/**
	 * ��֤�Ƿ�ӵ��ĳ����ɫ
	 */
	@Override
	protected boolean isAccessAllowed( ServletRequest request, ServletResponse response, Object mappedValue ) throws Exception {

		Subject subject = getSubject( request, response );
		String[] rolesArray = (String[])mappedValue;

		if( rolesArray == null || rolesArray.length == 0 ) {
			// �޽�ɫ�ͷ���false
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
