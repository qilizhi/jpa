package com.qlz.constant;

/**
 * 常量设置
 */
public final class Const {

	public static final int DISPLAY_STYLE_NORMAL = 0;
	public static final int DISPLAY_STYLE_MINI = 1;

	public static final String PAGE_NO = "1";

	public static final String PAGE_SIZE = "10";

	public static final String SUCCESS = "操作成功";
	public static final String FAILURE = "操作失败";
	public static final String ERROR_MESSAGE = "错误消息";

	public static final String UNKOWN_EXCEPTION = "发生未知异常";
	public static final String GET_EXCEPTION = "查询出现异常,请重新尝试一次";
	public static final String CREATE_EXCEPTION = "创建出现异常,请重新尝试一次";
	public static final String UPDATE_EXCEPTION = "更新出现异常,请重新尝试一次";
	public static final String DELETE_EXCEPTION = "删除出现异常,请重新尝试一次";
	public static final String APP_EXCEPTION = "抱歉,程序内部出现意外异常错误!";

	/* 菜单选中效果css类设置 */
	public static final String MENU_FIRST = "open";
	public static final String MENU_SUB = "active";

	/* 阿里云图片前缀 */
	public static final String IMAGEPREFIX = "http://mlx.oss.mlxing.com/";

	/**
	 * token
	 */
	public static final String TOKEN_PARAM = "accessToken";

	/**
	 * 登录名称(cookie)
	 */
	public static final String LOGIN_PARAM = "_name";

	/**
	 * API接口参数登录帐户名称
	 */
	public static final String API_LOGIN_NAME = "loginName";

	/**
	 * API接口参数登录帐户密码
	 */
	public static final String API_PARAM_PASSWORD = "password";

	/**
	 * 美丽行网站的API接口返回成功标识码
	 */
	public static final int API_RESPONSE_OK = 0;

	/**
	 * hash加密迭代次数。
	 * 
	 */
	public static final int HASH_INTERATIONS = 1024;

	/**
	 * 盐的长度
	 */
	public static final int SALT_SIZE = 8;
	// 哈希算法 SHA-1
	public static final String HASH_ALGORITHM = "SHA-1";
}