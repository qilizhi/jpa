package com.qlz.model;

import com.qlz.constant.ExceptionCode;


public class JsonResult {
	
	private Integer code;
	
	private String msg;
	private Object result;
	public JsonResult(){
	}
	public JsonResult(ExceptionCode exceptionCode) {
		this.code = exceptionCode.getCodeNo();
		this.msg = exceptionCode.getCodeName();
	}
	
	public JsonResult(ExceptionCode exceptionCode , Object result) {
		this.code = exceptionCode.getCodeNo();
		this.msg = exceptionCode.getCodeName();
		this.result = result;
		
	}
	
	public JsonResult(Integer status , String message , Object result) {
		this.code = status;
		this.msg = message;
		this.result = result;
	

	
	
	}

	public Integer getCode() {
		return code;
	}

	public void setCode( Integer code ) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg( String msg ) {
		this.msg = msg;
	}

	
	public Object getResult() {
		return result;
	}

	public void setResult( Object result ) {
		this.result = result;
		
	}
	
}