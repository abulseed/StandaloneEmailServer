package com.challenge.code.StandaloneEmailServer.email.clients.failover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

public class FailoverControllerTest {

	private FailoverControllerInterface failoverControllerInterface;

	@Test(expected = Exception.class)
	public void testEmailHandlingForInvalidEmails() throws Exception {
		EmailRequest emailRequest;
		emailRequest = new EmailRequest();
		emailRequest.setRecipient("test@test.test");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		failoverControllerInterface = new FailoverController(emailRequest);
		assertNotNull(failoverControllerInterface);
		assertNotNull(failoverControllerInterface.getMailGunClient());
		assertNotNull(failoverControllerInterface.getSendGridClient());
		assertTrue(failoverControllerInterface.getAvailableClients().length > 0);
		int statusCode = failoverControllerInterface.handleEmailSending();
		assertEquals(statusCode, Response.Status.OK.getStatusCode());
	}
	
	@Test
	public void testEmailHandlingForValidEmails() throws Exception {
		EmailRequest emailRequest;
		emailRequest = new EmailRequest();
		emailRequest.setRecipient("aelsayed.mo@gmail.com");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		failoverControllerInterface = new FailoverController(emailRequest);
		assertNotNull(failoverControllerInterface);
		assertNotNull(failoverControllerInterface.getMailGunClient());
		assertNotNull(failoverControllerInterface.getSendGridClient());
		assertTrue(failoverControllerInterface.getAvailableClients().length > 0);
		
		int statusCode = failoverControllerInterface.handleEmailSending();
		assertEquals(statusCode, Response.Status.OK.getStatusCode());
	}
}
