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
import com.shreyas.app.model.BinRange;

@Service
public class BinRangeService implements IBinRangeService {

	private static final Logger log = LoggerFactory.getLogger(BinRangeService.class);

	@Autowired
	private Environment env;
	
	// Bin Range URLs
	private static final String BIN_RANGE = "url.binrange";
	private static final String BIN_RANGE_JSON = "url.binrange.json";

	@Override
	public BinRange getBinRangeXML(MultiValueMap<String, String> ccnumbers) {
		
		// Create uri query
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty(BIN_RANGE));
		uriBuilder.queryParams(ccnumbers);
		log.info("Request created -> " + uriBuilder.toUriString());

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

		// Create and execute the GET call
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BinRange> binRange = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, BinRange.class);
		
		return binRange.getBody();
	}

	@Override
	public BinRange getBinRangeJSON(MultiValueMap<String, String> ccnumbers) {
		
		// Create uri query
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty(BIN_RANGE_JSON));
		uriBuilder.queryParams(ccnumbers);
		log.info("Request created -> " + uriBuilder.toUriString());

		// Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// Create and execute the GET call
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BinRange> binRange = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, BinRange.class);
		
		return binRange.getBody();
	}

	public String getBinRangeJSON(String jsonBody) {
		
		
		String jsonResponse = null;

		try {

			// Create the request
			BodyBuilder builder = RequestEntity.post(new URI(env.getProperty(BIN_RANGE)));
			builder.contentType(MediaType.APPLICATION_JSON);
			builder.accept(MediaType.APPLICATION_JSON);
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

	@Override
	public String getBinRangeXML(String xmlBody) {
		
		String xmlResponse = null;

		try {

			// Create the request
			BodyBuilder builder = RequestEntity.post(new URI(env.getProperty(BIN_RANGE)));
			builder.contentType(MediaType.APPLICATION_XML);
			builder.accept(MediaType.APPLICATION_XML);
			RequestEntity<String> request = builder.body(xmlBody);

			// Call the service
			RestTemplate restTemplate = new RestTemplate();
			xmlResponse = restTemplate.exchange(request, String.class).getBody();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RestApiConsumerException(e.getMessage(), e);
		}

		return xmlResponse;
	}
}
