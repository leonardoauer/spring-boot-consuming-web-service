package com.lauer.app.service;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import com.lauer.app.api.dto.ApiResultDTO;

public interface IDemoAppService {

	ApiResultDTO getBinRange(String url, MultiValueMap<String, String> ccnumbers, MediaType mediaType);
	ApiResultDTO getBinRange(String url, String bodyParams, MediaType mediaType);
}
