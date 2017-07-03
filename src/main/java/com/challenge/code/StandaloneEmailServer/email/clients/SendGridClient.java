package com.challenge.code.StandaloneEmailServer.email.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * Email client for SendGrid.
 * 
 * @author aelsayed
 *
 */
public class SendGridClient implements MailClientInterface {

	private static final Logger LOG = LoggerFactory.getLogger(SendGridClient.class);

	private final String API_KEY = "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";

	private SendingStatus sendingStatus = SendingStatus.Success;

	@Override
	public int sendEmail(EmailRequest emailBean) throws Exception {
		try {
			LOG.info("SendGrid received: " + emailBean.toString());
			Email from = new Email("test@sendgrid.com");
			String subject = emailBean.getSubject();
			Email to = new Email(emailBean.getRecipient());
			Content content = new Content("text/plain", emailBean.getBody());
			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(API_KEY);
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			
			Response response = sg.api(request);
			
			LOG.info("Email sent.. ");
			LOG.info(String.valueOf(response.getStatusCode()));
			return response.getStatusCode();
		} catch (Exception ex) {
			LOG.error("SendGrid failed: " + ex.getMessage());
			this.setSendingStatus(SendingStatus.Failed);
			throw ex;
		}
	}

	@Override
	public SendingStatus getSendingStatus() {
		return sendingStatus;
	}

	public void setSendingStatus(SendingStatus sendingStatus) {
		this.sendingStatus = sendingStatus;
	}
}
