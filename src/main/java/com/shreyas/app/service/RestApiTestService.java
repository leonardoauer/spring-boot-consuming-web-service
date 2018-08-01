package com.shreyas.app.service;

import java.net.URI;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shreyas.app.exception.RestApiConsumerException;

@Service
public class RestApiTestService implements IRestApiTestService {

	private static final Logger log = LoggerFactory.getLogger(RestApiTestService.class);

	@Autowired
	private Environment env;

	// URLs
	private static final String ENDPOINT_URL = "url.apitest";

	// Request need user Agent for testing againt TEST_ENDPOINT
	// https://stackoverflow.com/a/13671432/3254992
	private static final String USER_AGENT_KEY = "User-Agent";
	private static final String USER_AGENT_VAL = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

	@Override
	public String testJsonGET(MultiValueMap<String, String> parameters) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty(ENDPOINT_URL));
		uriBuilder.queryParams(parameters);
		log.info("Request created ->" + uriBuilder.toUriString());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add(USER_AGENT_KEY, USER_AGENT_VAL);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity,
				String.class);

		return response.getBody();
	}

	@Override
	public String testJsonPOST(String jsonBody) {

		String jsonResponse = null;

		try {

			// Create the request
			BodyBuilder builder = RequestEntity.post(new URI(env.getProperty(ENDPOINT_URL)));
			builder.contentType(MediaType.APPLICATION_JSON);
			builder.accept(MediaType.APPLICATION_JSON);
			builder.header(USER_AGENT_KEY, USER_AGENT_VAL);
			RequestEntity<String> request = builder.body(jsonBody);

			// Call the service
			RestTemplate restTemplate = new RestTemplate();
			jsonResponse = restTemplate.exchange(request, String.class).getBody();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RestApiConsumerException(e.getMessage(), e);
		}

		return jsonResponse;
	}
}
