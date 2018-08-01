package com.shreyas.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.app.service.IRestApiTestService;

@RestController
public class RestApiTestController {
	
	@Autowired
	private IRestApiTestService restApiTestService;

	@RequestMapping(value = "/test/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String testJsonGET(@RequestParam MultiValueMap<String, String> parameters) {
		return restApiTestService.testJsonGET(parameters);
	}
	
	@RequestMapping(value = "/test/json", consumes = MediaType.APPLICATION_XML_VALUE)
	public String testXmlGET(@RequestParam MultiValueMap<String, String> parameters) {
		return restApiTestService.testJsonGET(parameters);
	}
	
	@RequestMapping(value = "/test/json", method = RequestMethod.POST)
	public String testJsonPOST(@RequestBody String json) {
		return restApiTestService.testJsonPOST(json);
	}
}
