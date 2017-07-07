package com.challenge.code.StandaloneEmailServer.email.clients;

import java.nio.charset.Charset;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;

/**
 * Email client for MailGun.
 * 
 * @author aelsayed
 *
 */
public class MailGunClient implements MailClientInterface {

	private static final Logger LOG = LoggerFactory.getLogger(MailGunClient.class);

	private SendingStatus sendingStatus = SendingStatus.Success;

	public MailGunClient() {
	}

	@Override
	public int sendEmail(EmailRequest emailBean) throws Exception {
		try {
			LOG.info("MailGun received: " + emailBean.toString());
			
			String API_KEY = System.getProperty("MailGunApiKey");
			String MAILGUN_URL = System.getProperty("MailGunServiceUrl");
					
			RestTemplate template = new RestTemplate();

			byte[] base64Bytes = Base64.encodeBase64(API_KEY.getBytes(Charset.forName("UTF-8")));
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("from", "test@mailgun.com");
			map.add("to", emailBean.getRecipient());
			map.add("subject", emailBean.getSubject());
			map.add("text", emailBean.getBody()); 

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic " + new String(base64Bytes, Charset.forName("UTF-8")));
			headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);

			ResponseEntity<String> response = template.exchange(MAILGUN_URL, HttpMethod.POST,new HttpEntity<>(map, headers), String.class);

			LOG.info("Email sent.. ");
			LOG.info(String.valueOf(response.getStatusCodeValue()));
			return response.getStatusCodeValue();
		} catch (Exception ex) {
			LOG.error("MailGun failed: " + ex.getMessage());
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
