package com.qlz.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 * 
 * 参�?了guava的Throwables�?
 * 
 * @author calvin
 */
public class Exceptions {

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked( Throwable ex ) {
		if( ex instanceof RuntimeException ) {
			return (RuntimeException)ex;
		}
		else {
			return new RuntimeException( ex );
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString( Throwable ex ) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace( new PrintWriter( stringWriter ) );
		return stringWriter.toString();
	}

	/**
	 * 获取组合本异常信息与底层异常信息的异常描�? 适用于本异常为统�?��装异常类，底层异常才是根本原因的情况�?
	 */
	public static String getErrorMessageWithNestedException( Throwable ex ) {
		Throwable nestedException = ex.getCause();
		return new StringBuilder().append( ex.getMessage() ).append( " nested exception is " )
		        .append( nestedException.getClass().getName() ).append( ":" ).append( nestedException.getMessage() ).toString();
	}

	/**
	 * 获取异常的Root Cause.
	 */
	public static Throwable getRootCause( Throwable ex ) {
		Throwable cause;
		while( (cause = ex.getCause()) != null ) {
			ex = cause;
		}
		return ex;
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy( Exception ex, Class<? extends Exception>... causeExceptionClasses ) {
		Throwable cause = ex;
		while( cause != null ) {
			for( Class<? extends Exception> causeClass : causeExceptionClasses ) {
				if( causeClass.isInstance( cause ) ) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}
}
