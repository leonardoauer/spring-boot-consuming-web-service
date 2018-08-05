package com.lauer.app.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lauer.app.api.dto.ApiResultDTO;

public abstract class ApiRestService {

	private static final Logger log = LoggerFactory.getLogger(ApiRestService.class);
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String CLIENT_USER_ID = "CLIENT_USER_ID";
	private static final String INTUIT_TID = "intuit_tid";
	
	@Autowired
	private Environment env;

	public ApiResultDTO performGET(String url, MultiValueMap<String, String> params, MediaType mediaType) {

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setAccept(Collections.singletonList(mediaType));
		
		// Add Authorization headers
		headers.add(AUTHORIZATION, env.getProperty("t360.config.authorization"));
		headers.add(CLIENT_USER_ID, env.getProperty("t360.config.client_user_id"));
		headers.add(INTUIT_TID, env.getProperty("t360.config.intuit_tid"));

		// Create Request URI
		String requestURL = url;
		if(params != null && params.size() != 0) {
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
			requestURL = uriBuilder.queryParams(params).toUriString();
		}
		
		log.info("Request created -> " + requestURL);

		// Set headers, URI and execute the GET request
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(requestURL, HttpMethod.GET, entity, String.class);

		ApiResultDTO apiDataDTO = new ApiResultDTO();
		apiDataDTO.setRequestURL(requestURL);
		apiDataDTO.setParameters(params);
		apiDataDTO.setResponse(response.getBody());

		return apiDataDTO;
	}

	public ApiResultDTO performPOST(String url, String bodyParams, MediaType mediaType) {

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setAccept(Collections.singletonList(mediaType));

		// Add Authorization headers
		headers.add(AUTHORIZATION, env.getProperty("t360.config.authorization"));
		headers.add(CLIENT_USER_ID, env.getProperty("t360.config.client_user_id"));
		headers.add(INTUIT_TID, env.getProperty("t360.config.intuit_tid"));

		// Create Request URI
		log.info("Request created -> " + url);

		// Set headers, URI and execute the POST request
		HttpEntity<String> entity = new HttpEntity<>(bodyParams, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		ApiResultDTO apiDataDTO = new ApiResultDTO();
		apiDataDTO.setRequestURL(url);
		apiDataDTO.setParameters(bodyParams);
		apiDataDTO.setResponse(response.getBody());

		return apiDataDTO;
	}
}
