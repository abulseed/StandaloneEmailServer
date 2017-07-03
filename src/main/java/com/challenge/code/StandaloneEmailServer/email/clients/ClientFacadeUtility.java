package com.challenge.code.StandaloneEmailServer.email.clients;

import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

/**
 * Generic facade utility for executing sending email operation.
 * 
 * @author aelsayed
 *
 * @param <T> MailClientInterface mail client implementation.
 */
public class ClientFacadeUtility<T extends MailClientInterface> {
	
	private T emailClient;
	
	public ClientFacadeUtility(T client){
		this.emailClient = client;
	}
	
	public int sendEmail(EmailRequest emailRequest) throws Exception {
		return emailClient.sendEmail(emailRequest);
	}
}
