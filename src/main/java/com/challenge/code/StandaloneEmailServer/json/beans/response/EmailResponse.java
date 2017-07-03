package com.challenge.code.StandaloneEmailServer.json.beans.response;

/**
 * Response bean.
 * 
 * @author aelsayed
 *
 */
public class EmailResponse {

	private int statusCode;
	private String validationErrors;
	private String exceptionMessage;

	public EmailResponse() {
	}

	public EmailResponse(int statusCode, String exceptionMsg) {
		super();
		this.statusCode = statusCode;
		this.exceptionMessage = exceptionMsg;
	}

	public String getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(String validationErrors) {
		this.validationErrors = validationErrors;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
