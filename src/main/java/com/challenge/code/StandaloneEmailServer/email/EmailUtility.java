package com.challenge.code.StandaloneEmailServer.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.code.StandaloneEmailServer.email.clients.failover.FailoverController;
import com.challenge.code.StandaloneEmailServer.email.clients.failover.FailoverControllerInterface;
import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

/**
 * Utility used for routing email requests to FailoverControllerInterface.
 * 
 * @author aelsayed
 *
 */
public class EmailUtility {

	private static final Logger LOG = LoggerFactory.getLogger(EmailUtility.class);

	public int sendEmail(EmailRequest emailRequest) throws Exception {
		LOG.info("Received: " + emailRequest.toString());
		FailoverControllerInterface controller = new FailoverController(emailRequest);
		return controller.handleEmailSending();
	}
}
