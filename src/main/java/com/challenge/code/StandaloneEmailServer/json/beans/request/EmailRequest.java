package com.challenge.code.StandaloneEmailServer.json.beans.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Request bean.
 * 
 * @author aelsayed
 *
 */
public class EmailRequest {
	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
			+ "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
	private String recipient;
	
	@NotNull
	private String subject;
	
	@NotNull
	private String body;

	public EmailRequest() {
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "EmailBean [recipient=" + recipient + ", subject=" + subject + ", body=" + body + "]";
	}
}

