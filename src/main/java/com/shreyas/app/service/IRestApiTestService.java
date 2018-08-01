package com.shreyas.app.service;

import org.springframework.util.MultiValueMap;

public interface IRestApiTestService {

	String testJsonGET(MultiValueMap<String, String> parameters);

	String testJsonPOST(String jsonBody);

}
