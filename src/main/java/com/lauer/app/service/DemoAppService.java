package com.lauer.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.lauer.app.api.dto.ApiResultDTO;

@Service
public class DemoAppService extends ApiRestService implements IDemoAppService {

	private static final Logger log = LoggerFactory.getLogger(DemoAppService.class);

	@Override
	public ApiResultDTO getBinRange(String url, MultiValueMap<String, String> params, MediaType mediaType) {
		log.info("Performing GET Request for Bin Range");
		return this.performGET(url, params, mediaType);
	}

	@Override
	public ApiResultDTO getBinRange(String url, String bodyParams, MediaType mediaType) {
		log.info("Performing POST Request for Bin Range");
		return this.performPOST(url, bodyParams, mediaType);
	}
}
