package com.qlz.constant;


/**
 * �쳣������ö��
 * 
 * @author quan
 * 
 */
public enum ExceptionCode {

	SUCCESSFUL( 200, "�ɹ�" ), 
	AUTHORIZATION( 403, "û��Ȩ��" ), 
	FAIL( -1, "ʧ��" );

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