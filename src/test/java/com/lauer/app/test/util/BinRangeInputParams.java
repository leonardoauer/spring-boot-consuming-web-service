package com.lauer.app.test.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class BinRangeInputParams {

	public static final MultiValueMap<String, String> params;
	static {
		params = new LinkedMultiValueMap<>();
		params.add("ccnumber", "374779000000009");
		params.add("ccnumber", "5452360000000000");
	}
	
	public static final String XML_BODY = "" + 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<tns:BINLookupQuery \n" + 
			" xsi:schemaLocation=\"http://www.intuit.com/psd/cdm/v1 PSDDataService.xsd\" \n" + 
			" xmlns:tns=\"http://www.intuit.com/psd/cdm/v1\" \n" + 
			" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + 
			"    <tns:BINLookupParameter>\n" + 
			"        <tns:CCNumber>3540000000000012</tns:CCNumber>\n" + 
			"        <tns:CCNumber>4264169851234567</tns:CCNumber>\n" + 
			"        <tns:CCNumber>374779000000009</tns:CCNumber>\n" + 
			"        <tns:CCNumber>36121500000008</tns:CCNumber>\n" + 
			"        <tns:CCNumber>4011430000000010</tns:CCNumber>\n" + 
			"    </tns:BINLookupParameter>\n" + 
			"</tns:BINLookupQuery> ";
	
	public static final String JSON_BODY = "" + 
	        "{\n" + 
			" \"BINLookupQuery\": {\n" + 
			"   \"BINLookupParameter\": {\n" + 
			"    \"CCNumber\" : \"374779000000009\",\n" + 
			"    \"CCNumber\" : \"4264169851234567\"\n" + 
			"    }\n" + 
			"  }\n" + 
			"}";
}
