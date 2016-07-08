package com.qlz.constant;


/**
 * 错误信息model
 * 
 * @author qilizhi
 * 
 */
public enum ExceptionCode {

	SUCCESSFUL( 200, "操作成功" ), 
	AUTHORIZATION( 403, "没有权限访问" ), 
	FAIL( -1, "操作失败" );

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