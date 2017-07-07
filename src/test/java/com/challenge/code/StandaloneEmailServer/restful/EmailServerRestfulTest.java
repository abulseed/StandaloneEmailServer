package com.challenge.code.StandaloneEmailServer.restful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.challenge.code.StandaloneEmailServer.SpringBootMainApplication;
import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;
import com.challenge.code.StandaloneEmailServer.json.beans.response.EmailResponse;

@RunWith(SpringJUnit4ClassRunner.class)
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

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encodeBase64("mockUser:mockPassword".getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8")));
		headers.add("Content-Type", MediaType.APPLICATION_JSON);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.exchange("/SendEmail", HttpMethod.POST,
				new HttpEntity<>(emailRequest, headers), EmailResponse.class);

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

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encodeBase64("mockUser:mockPassword".getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8")));
		headers.add("Content-Type", MediaType.APPLICATION_JSON);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.exchange("/SendEmail", HttpMethod.POST,
				new HttpEntity<>(emailRequest, headers), EmailResponse.class);

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

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encodeBase64("mockUser:mockPassword".getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8")));
		headers.add("Content-Type", MediaType.APPLICATION_JSON);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.exchange("/SendEmail", HttpMethod.POST,
				new HttpEntity<>(emailRequest, headers), EmailResponse.class);

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

		HttpHeaders headers = new HttpHeaders();

		byte[] base64Bytes = Base64.encodeBase64("mockUser:mockPassword".getBytes(Charset.forName("UTF-8")));
		String user = new String(base64Bytes, Charset.forName("UTF-8"));

		headers.add("Authorization", "Basic " + user);
		headers.add("Content-Type", MediaType.APPLICATION_JSON);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.exchange("/SendEmailWrongURI", HttpMethod.POST,
				new HttpEntity<>(emailRequest, headers), EmailResponse.class);

		assertEquals(org.springframework.http.HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void sendEmailClientUnAuthorized() {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setRecipient("test@test.test");
		emailRequest.setSubject("test");
		emailRequest.setBody("test");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encodeBase64("mockUser1:mockPassword1".getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8")));
		headers.add("Content-Type", MediaType.APPLICATION_JSON);

		ResponseEntity<EmailResponse> responseEntity = restTemplate.exchange("/SendEmail", HttpMethod.POST,
				new HttpEntity<>(emailRequest, headers), EmailResponse.class);

		assertEquals(org.springframework.http.HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}
}