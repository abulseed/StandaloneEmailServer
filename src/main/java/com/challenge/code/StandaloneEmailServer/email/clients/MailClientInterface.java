package com.challenge.code.StandaloneEmailServer.email.clients;

import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

/**
 * Abstract mail client interface.
 * 
 * @author aelsayed
 *
 */
public interface MailClientInterface {
	public SendingStatus getSendingStatus();
	public int sendEmail(EmailRequest emailBean) throws Exception;
	
	public enum SendingStatus {
		Success, Failed;
	}
}
