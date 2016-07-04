package com.qlz.constant;


/**
 * 异常错误码枚举
 * 
 * @author quan
 * 
 */
public enum ExceptionCode {

	SUCCESSFUL( 200, "成功" ), 
	AUTHORIZATION( 403, "没有权限" ), 
	FAIL( -1, "失败" );

	private int codeNo;

	private String codeName;

	private ExceptionCode(int codeNo, String codeName) {
		this.codeNo = codeNo;
		this.codeName = codeName;
	}

	public int getCodeNo() {
		return codeNo;
	}

	public String getCodeName() {
		return codeName;
	}
}