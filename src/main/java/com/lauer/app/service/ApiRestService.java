package com.lauer.app.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lauer.app.api.properties.ApiDataProperties;
import com.lauer.app.util.RequestUtil;

public abstract class ApiRestService {

	private static final Logger log = LoggerFactory.getLogger(ApiRestService.class);

	private static final String CLIENT_USER_ID = "CLIENT_USER_ID";
	private static final String INTUIT_TID = "intuit_tid";

	@Autowired
	private Environment env;
	
	private final RestTemplate restTemplate;
	
	public ApiRestService(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}

	public ApiDataProperties performGET(String url, MultiValueMap<String, String> params, MediaType mediaType) {

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setAccept(Collections.singletonList(mediaType));

		// Add Authorization headers
		headers.add(HttpHeaders.AUTHORIZATION, env.getProperty("t360.config.authorization"));
		headers.add(CLIENT_USER_ID, env.getProperty("t360.config.client_user_id"));
		headers.add(INTUIT_TID, env.getProperty("t360.config.intuit_tid"));

		// Create Request URI
		String requestURL = RequestUtil.createURI(url, params);
		log.info("Request created -> " + requestURL);

		// Set headers, URI and execute the GET request
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(requestURL, HttpMethod.GET, entity, String.class);

		ApiDataProperties apiDataDTO = new ApiDataProperties();
		apiDataDTO.setRequestURL(requestURL);
		apiDataDTO.setParameters(params);
		apiDataDTO.setResponse(response.getBody());

		return apiDataDTO;
	}

	public ApiDataProperties performPOST(String url, String bodyParams, MediaType mediaType) {

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setAccept(Collections.singletonList(mediaType));

		// Add Authorization headers
		headers.add(HttpHeaders.AUTHORIZATION, env.getProperty("t360.config.authorization"));
		headers.add(CLIENT_USER_ID, env.getProperty("t360.config.client_user_id"));
		headers.add(INTUIT_TID, env.getProperty("t360.config.intuit_tid"));

		// Create Request URI
		log.info("Request created -> " + url);

		// Set headers, URI and execute the POST request
		HttpEntity<String> entity = new HttpEntity<>(bodyParams, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		ApiDataProperties apiDataDTO = new ApiDataProperties();
		apiDataDTO.setRequestURL(url);
		apiDataDTO.setParameters(bodyParams);
		apiDataDTO.setResponse(response.getBody());

		return apiDataDTO;
	}
}
