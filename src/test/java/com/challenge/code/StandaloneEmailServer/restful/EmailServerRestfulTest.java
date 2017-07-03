package com.challenge.code.StandaloneEmailServer.restful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.code.StandaloneEmailServer.SpringBootMainApplication;
import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;
import com.challenge.code.StandaloneEmailServer.json.beans.response.EmailResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailServerRestfulTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {

	}

	@Test
	public void sendEmailClientValidAddress() {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setRecipient("aelsayed.mo@gmail.com");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		ResponseEntity<EmailResponse> responseEntity = restTemplate.postForEntity("/SendEmail", emailRequest,
				EmailResponse.class);

		EmailResponse emailResponse = responseEntity.getBody();

		assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(emailResponse);
		assertEquals(emailResponse.getStatusCode(), responseEntity.getStatusCode().value());
		assertNull(emailResponse.getValidationErrors());
		assertEquals(emailResponse.getExceptionMessage(), "OK");
	}

	@Test
	public void sendEmailClientInvalidAddress() {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setRecipient("test@test.test");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		ResponseEntity<EmailResponse> responseEntity = restTemplate.postForEntity("/SendEmail", emailRequest,
				EmailResponse.class);

		EmailResponse emailResponse = responseEntity.getBody();

		assertEquals(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertNotNull(emailResponse);
		assertEquals(emailResponse.getStatusCode(), responseEntity.getStatusCode().value());
		assertNull(emailResponse.getValidationErrors());
		assertNotEquals(emailResponse.getExceptionMessage(), "OK");
	}

	@Test
	public void sendEmailClientNullTest() {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setRecipient(null);
		emailRequest.setSubject(null);
		emailRequest.setBody(null);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.postForEntity("/SendEmail", emailRequest,
				EmailResponse.class);

		EmailResponse emailResponse = responseEntity.getBody();

		assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertNotNull(emailResponse);
		assertEquals(emailResponse.getStatusCode(), responseEntity.getStatusCode().value());
		assertNotNull(emailResponse.getValidationErrors());
		assertTrue(!emailResponse.getValidationErrors().isEmpty());
	}

	@Test
	public void sendEmailClientWrongResourcePath() {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setRecipient("test@test.test");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		ResponseEntity<EmailResponse> responseEntity = restTemplate.postForEntity("/SendEmailWrongURI", emailRequest,
				EmailResponse.class);

		assertEquals(org.springframework.http.HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}