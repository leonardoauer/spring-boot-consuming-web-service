package com.lauer.app.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestUtil {

	public static String createURI(String url, MultiValueMap<String, String> params) {

		String requestURL = url;
		if (params != null && params.size() != 0) {
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
			requestURL = uriBuilder.queryParams(params).build(false).toUriString();
		}
		
		return requestURL;
	}
}
