package com.challenge.code.StandaloneEmailServer.email.clients.failover;

import com.challenge.code.StandaloneEmailServer.email.clients.MailGunClient;
import com.challenge.code.StandaloneEmailServer.email.clients.SendGridClient;

/**
 * Interface for failover controller.
 * 
 * @author aelsayed
 *
 */
public interface FailoverControllerInterface {
	public int handleEmailSending() throws Exception;
	public MailGunClient getMailGunClient();
	public SendGridClient getSendGridClient();
	public int getAvailableClients();
}
