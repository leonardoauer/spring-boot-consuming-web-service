package com.shreyas.app.service;

import org.springframework.util.MultiValueMap;

import com.shreyas.app.model.BinRange;

/**
 * This service returns BIN Range information for card numbers.
 * 
 * @author lauer
 */
public interface IBinRangeService {
	
	/**
	 * Retrieve list of BINLookup entries for several credit card numbers in XML
	 * Format
	 */
	BinRange getBinRangeXML(MultiValueMap<String, String> ccnumbers);

	/**
	 * Retrieve list of BINLookup entries for several credit card numbers in JSON
	 * Format
	 */
	BinRange getBinRangeJSON(MultiValueMap<String, String> ccnumbers);

	/**
	 * Retrieve list of BINLookup entries for post body params in JSON format
	 */
	String getBinRangeJSON(String jsonBody);
	
	/**
	 * Retrieve list of BINLookup entries for post body params in XML format
	 */
	String getBinRangeXML(String xmlBody);
}
