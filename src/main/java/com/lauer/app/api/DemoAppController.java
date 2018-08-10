package com.lauer.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lauer.app.api.endpoints.ApiEndpoints;
import com.lauer.app.api.properties.ApiDataProperties;
import com.lauer.app.service.IDemoAppService;

@RestController
public class DemoAppController {

	@Autowired
	private IDemoAppService srv;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/binrange", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiDataProperties binRangeByGETJson(@RequestParam MultiValueMap<String, String> ccnumber) {
		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE) + "/json";
		return srv.getBinRange(requestURL, ccnumber, MediaType.APPLICATION_JSON);
	}
	
	@RequestMapping(value = "/binrange", consumes = MediaType.APPLICATION_XML_VALUE)
	public ApiDataProperties binRangeByGETXml(@RequestParam MultiValueMap<String, String> ccnumbers) {
		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		return srv.getBinRange(requestURL, ccnumbers, MediaType.APPLICATION_XML);
	}
	
	@RequestMapping(value = "/binrange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiDataProperties binRangeByPOSTJson(@RequestBody String body) {
		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		return srv.getBinRange(requestURL, body, MediaType.APPLICATION_JSON);
	}	

	@RequestMapping(value = "/binrange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ApiDataProperties binRangeByPOSTXml(@RequestBody String body) {
		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		return srv.getBinRange(requestURL, body, MediaType.APPLICATION_XML);
	}	
}
