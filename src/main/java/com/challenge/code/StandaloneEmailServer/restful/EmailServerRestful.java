package com.challenge.code.StandaloneEmailServer.restful;

import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.code.StandaloneEmailServer.email.EmailUtility;
import com.challenge.code.StandaloneEmailServer.json.beans.request.EmailRequest;
import com.challenge.code.StandaloneEmailServer.json.beans.response.EmailResponse;

/**
 * REST service used for handling email requests and sending them.
 * 
 * @author aelsayed
 *
 */
@RestController
public class EmailServerRestful {

	private static final Logger LOG = LoggerFactory.getLogger(EmailServerRestful.class);

	@RequestMapping(path = "/SendEmail", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<EmailResponse> sendEmail(@Valid @RequestBody EmailRequest email, Errors errors) {
		EmailResponse emailResponse = new EmailResponse();
		int statusCode = Response.Status.BAD_REQUEST.getStatusCode();

		if (errors.hasErrors()) {
			emailResponse.setStatusCode(statusCode);
			emailResponse.setValidationErrors(errors.getFieldErrors().stream()
					.map(x -> x.getField() + " : " + x.getDefaultMessage()).collect(Collectors.joining(" | ")));

			return ResponseEntity.status(statusCode).body(emailResponse);
		}

		statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		try {
			LOG.info("Received: " + email.toString());

			statusCode = new EmailUtility().sendEmail(email);
			LOG.info("StatusCode: " + statusCode);

			emailResponse.setStatusCode(statusCode);
			emailResponse.setExceptionMessage("OK");
			return ResponseEntity.status(statusCode).body(emailResponse);
		} catch (Exception e) {
			emailResponse.setStatusCode(statusCode);
			emailResponse.setExceptionMessage(e.getMessage());
			return ResponseEntity.status(statusCode).body(emailResponse);
		}
	}
}
