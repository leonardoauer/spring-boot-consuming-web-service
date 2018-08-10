package com.lauer.app.test.util;

public class ApiTestUtils {

	public static final String BIN_RANGE_RESPONSE = 
			"{\n" + 
			"  \"RestResponse\": {\n" + 
			"    \"BINLookups\": {\n" + 
			"      \"@RequestId\": \"tpGUID\",\n" + 
			"      \"@IsEncrypted\": \"true\",\n" + 
			"      \"@TotalCount\": \"2\",\n" + 
			"      \"@PageSize\": \"1\",\n" + 
			"      \"@GenDuration\": \"281\",\n" + 
			"      \"@GenDateTime\": \"2014-04-21T15:16:23.812-07:00\",\n" + 
			"      \"BINLookup\": [\n" + 
			"        {\n" + 
			"          \"StartingBIN\": 374779000000000,\n" + 
			"          \"EndingBIN\": 374779999999999,\n" + 
			"          \"CCNumber\": 374779000000009,\n" + 
			"          \"CardType\": \"Amex_Direct\",\n" + 
			"          \"CountryCode\": \"USA\"\n" + 
			"        },\n" + 
			"        {\n" + 
			"          \"StartingBIN\": 4264169850000000,\n" + 
			"          \"EndingBIN\": 4264169859999999,\n" + 
			"          \"CCNumber\": 4264169851234567,\n" + 
			"          \"CardType\": \"Visa\",\n" + 
			"          \"CountryCode\": \"USA\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  }\n" + 
			"}";
}
