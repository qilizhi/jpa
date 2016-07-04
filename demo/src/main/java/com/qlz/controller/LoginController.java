package com.qlz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��¼���쳣��ת����
 * 
 * @author quan
 * 
 */
@Controller
@RequestMapping( "/" )
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger( LoginController.class );
	
	
	@RequestMapping("/admin")
	public String login(){
		
		return "/admin/login";
	}
	/*@Autowired
	private ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource;

	*//**
	 * ƽ̨��¼
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping( value = "platformLogin", method = RequestMethod.GET )
	public String platformLogin( @RequestParam( required = false ) String returnUrl, Model model ) {
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser != null && shiroUser.getUserType() == EUserType.PLATFORM_USER ) {
			// ƽ̨�û���ת��ƽ̨��ҳҳ��
			return "redirect:/admin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.GUIDE_USER ) {
			// �����û���ת�����ι�����ҳҳ��
			return "redirect:/guideAdmin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.USER ) {
			// ��ͨ�û���ת��403ҳ��
			return "redirect:/error/403";
		}
		model.addAttribute( "returnUrl", returnUrl );
		return "admin/login";
	}

	*//**
	 * ƽ̨��¼
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping( value = "platformLogin", method = RequestMethod.POST )
	@ResponseBody
	public JsonResult platformLogin( HttpServletRequest request,
	        @CookieValue( value = Const.LOGIN_PARAM, required = false ) String accessToken ) {
		return loginToRedirect( request, accessToken, EUserType.PLATFORM_USER );
	}

	*//**
	 * ���ε�¼
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping( value = "guideLogin", method = RequestMethod.GET )
	public String guideAdminLogin( @RequestParam( required = false ) String returnUrl, Model model ) {
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser != null && shiroUser.getUserType() == EUserType.PLATFORM_USER ) {
			// ƽ̨�û���ת��ƽ̨��ҳҳ��
			return "redirect:/admin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.GUIDE_USER ) {
			// �����û���ת�����ι�����ҳҳ��
			return "redirect:/guideAdmin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.USER ) {
			// ��ͨ�û���ת��403ҳ��
			return "redirect:/error/403";
		}
		model.addAttribute( "returnUrl", returnUrl );
		return "guideAdmin/login";
	}

	*//**
	 * ���ε�¼
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping( value = "guideLogin", method = RequestMethod.POST )
	@ResponseBody
	public JsonResult guideAdminLogin( HttpServletRequest request,
	        @CookieValue( value = Const.LOGIN_PARAM, required = false ) String accessToken ) {
		return loginToRedirect( request, accessToken, EUserType.GUIDE_USER );
	}

	*//**
	 * ��¼
	 * 
	 * @param request
	 * @param defUrl
	 * @return
	 *//*
	private JsonResult loginToRedirect( HttpServletRequest request, String accessToken, EUserType eUserType ) {
		String loginName = StringUtil.stringValue( request.getParameter( "username" ), "" );
		String password = StringUtil.stringValue( request.getParameter( "password" ), "" );
		boolean rememberMe = request.getParameter( "remember" ) != null;
		if( StringUtil.empty( loginName ) || StringUtil.empty( password ) ) {
			return new JsonResult( ExceptionCode.FAIL );
		}
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser == null ) {
			try {
				CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken( loginName, password.toCharArray() );
				token.setRememberMe( rememberMe );
				token.setUserType( eUserType );
				token.setAccessToken( accessToken );
				SecurityUtils.getSubject().login( token );
				return new JsonResult( ExceptionCode.SUCCESSFUL );
			}
			catch( AuthenticationException e ) {
				logger.info( "��½��֤ʧ��" + e.getMessage() );
				return new JsonResult( ExceptionCode.FAIL );
			}
		}
		return new JsonResult( ExceptionCode.SUCCESSFUL );
	}

	*//**
	 * �ǳ�
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping( "/logout" )
	public String logout( Model model ) {
		try {
			ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
			// ʹ��Ȩ�޹����߽����û����˳���������¼��������ʾ��Ϣ
			SecurityUtils.getSubject().logout();
			chainDefinitionSectionMetaSource.reLoad();
			if( shiroUser != null && shiroUser.getUserType() == EUserType.PLATFORM_USER ) {
				// ƽ̨�û���ת��ƽ̨��ҳҳ��
				return "redirect:/admin";
			}
			else {
				// �����û���ת�����ι�����ҳҳ��
				return "redirect:/guideAdmin";
			}
		}
		catch( Exception e ) {
			logger.error( e.getMessage() );
			model.addAttribute( "message", "�˳�ʧ��,������." );
		}

		return "logout";
	}*/

	/**
	 * �쳣����
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( "/error/{code}" )
	public String logout( @PathVariable String code, Model model ) {
		return "error/" + code;
	}

}
