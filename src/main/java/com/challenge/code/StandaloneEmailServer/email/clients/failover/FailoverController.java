package com.challenge.code.StandaloneEmailServer.email.clients.failover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.code.StandaloneEmailServer.email.clients.ClientFacadeUtility;
import com.challenge.code.StandaloneEmailServer.email.clients.MailClientInterface;
import com.challenge.code.StandaloneEmailServer.email.clients.MailGunClient;
import com.challenge.code.StandaloneEmailServer.email.clients.SendGridClient;
import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

/**
 * Failover controller that decides which email client is available and then use
 * it for sending the email.
 * 
 * @author aelsayed
 *
 */
public class FailoverController implements FailoverControllerInterface {

	private static final Logger LOG = LoggerFactory.getLogger(FailoverController.class);

	private MailGunClient mailGunClient;
	private SendGridClient sendGridClient;
	private MailClientInterface[] availableClients;

	private EmailRequest emailRequest;

	public FailoverController(EmailRequest emailRequest) {
		this.emailRequest = emailRequest;
		mailGunClient = new MailGunClient();
		sendGridClient = new SendGridClient();
		MailClientInterface[] clients = { mailGunClient };
		availableClients = clients;
		LOG.info("Successfully initialized.");
	}

	@Override
	public int handleEmailSending() throws Exception {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < availableClients.length; i++) {
			MailClientInterface currentClient = availableClients[i];
			try {
				LOG.info("Sending email using: " + currentClient.getClass());
				ClientFacadeUtility<MailClientInterface> emailFacadeUtility = new ClientFacadeUtility<MailClientInterface>(
						currentClient);
				return emailFacadeUtility.sendEmail(emailRequest);
			} catch (Exception e) {
				builder.append(e.getMessage()).append(" , ");
				LOG.error("Sending failed.. " + currentClient.getSendingStatus() + " :: " + e.getMessage());
			}
		}
		throw new Exception(builder.toString());
	}

	@Override
	public MailGunClient getMailGunClient() {
		return mailGunClient;
	}

	@Override
	public SendGridClient getSendGridClient() {
		return sendGridClient;
	}

	@Override
	public MailClientInterface[] getAvailableClients() {
		return availableClients;
	}

	public EmailRequest getEmailRequest() {
		return emailRequest;
	}

	public void setEmailRequest(EmailRequest emailRequest) {
		this.emailRequest = emailRequest;
	}

}
