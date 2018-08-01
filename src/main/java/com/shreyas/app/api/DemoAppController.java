package com.shreyas.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.app.model.BinRange;
import com.shreyas.app.service.IBinRangeService;

@RestController
public class DemoAppController {

	@Autowired
	private IBinRangeService binRangeService;
	
	/**
	 * Retrieve list of BINLookup entries in XML format for given query params. 
	 */
	@RequestMapping("/binrange/")
	public BinRange getBinRangeXML(@RequestParam MultiValueMap<String, String> ccnumbers) {
		return binRangeService.getBinRangeXML(ccnumbers);
	}

	/**
	 * Retrieve list of BINLookup entries in JSON format for given query params
	 */
	@RequestMapping("/binrange/json")
	public BinRange getBinRangeJSON(@RequestParam MultiValueMap<String, String> ccnumbers) {
		return binRangeService.getBinRangeJSON(ccnumbers);
	}
	
	/**
	 * Retrieve list of BINLookup entries for post body params in JSON format
	 */
	@RequestMapping(value = "/binrange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getBinRangeByJsonPost(@RequestBody String json) {
		return binRangeService.getBinRangeJSON(json);
	}	
	
	/**
	 * Retrieve list of BINLookup entries for post body params in XML format
	 */
	@RequestMapping(value = "/binrange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public String getBinRangeByXmlPost(@RequestBody String xml) {
		return binRangeService.getBinRangeXML(xml);
	}
}
