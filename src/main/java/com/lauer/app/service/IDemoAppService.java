package com.lauer.app.service;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import com.lauer.app.api.properties.ApiDataProperties;

public interface IDemoAppService {

	ApiDataProperties getBinRange(String url, MultiValueMap<String, String> ccnumbers, MediaType mediaType);
	ApiDataProperties getBinRange(String url, String bodyParams, MediaType mediaType);
}
