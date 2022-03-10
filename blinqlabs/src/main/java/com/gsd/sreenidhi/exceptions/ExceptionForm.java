package com.gsd.sreenidhi.exceptions;

public class ExceptionForm {
	String ExceptionLocalizedMessage;
	String ExceptionMessage;
	String ExceptionCause;
	String ExceptionStackTrace;
	int ExceptionHashCode;
	
	public String getExceptionLocalizedMessage() {
		return ExceptionLocalizedMessage;
	}
	public void setExceptionLocalizedMessage(String exceptionLocalizedMessage) {
		ExceptionLocalizedMessage = exceptionLocalizedMessage;
	}
	public String getExceptionMessage() {
		return ExceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		ExceptionMessage = exceptionMessage;
	}
	public String getExceptionCause() {
		return ExceptionCause;
	}
	public void setExceptionCause(String exceptionCause) {
		ExceptionCause = exceptionCause;
	}
	public String getExceptionStackTrace() {
		return ExceptionStackTrace;
	}
	public void setExceptionStackTrace(String exceptionStackTrace) {
		ExceptionStackTrace = exceptionStackTrace;
	}
	public int getExceptionHashCode() {
		return ExceptionHashCode;
	}
	public void setExceptionHashCode(int exceptionHashCode) {
		ExceptionHashCode = exceptionHashCode;
	}
	
	
}
