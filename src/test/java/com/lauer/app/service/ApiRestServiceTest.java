package com.lauer.app.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.lauer.app.api.endpoints.ApiEndpoints;
import com.lauer.app.api.properties.ApiDataProperties;
import com.lauer.app.test.util.ApiTestUtils;
import com.lauer.app.test.util.BinRangeInputParams;
import com.lauer.app.util.RequestUtil;

@RunWith(SpringRunner.class)
@RestClientTest(ApiRestService.class)
public class ApiRestServiceTest {

	@Autowired
	private ApiRestService apiRestService;

	@Autowired
	private MockRestServiceServer mockServer;
	
    @Autowired
    private Environment env;

    /**
     * This method test GET Request for given parameters and expects JSON response
     */
	@Test
	public void testPerformGET() {
		
		configureMockServerForGETRequest();
		
		String binRangeURL = env.getProperty(ApiEndpoints.BIN_RANGE_JSON);
		ApiDataProperties apiDataProperties = apiRestService.performGET(binRangeURL, BinRangeInputParams.params, MediaType.APPLICATION_JSON);

		assertEquals(ApiTestUtils.BIN_RANGE_RESPONSE, apiDataProperties.getResponse());

		String requestedURL = RequestUtil.createURI(binRangeURL, BinRangeInputParams.params);
		assertEquals(requestedURL, apiDataProperties.getRequestURL());
		assertEquals(BinRangeInputParams.params.toString(), apiDataProperties.getParameters());
	}
	
    /**
     * This method test POST Request for a given JSON Body and expects JSON response
     */	
	@Test
	public void testPerformPOST() {
		
		configureMockServerForPOSTRequest();
		
		String binRangeURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		ApiDataProperties apiDataProperties = apiRestService.performPOST(binRangeURL, BinRangeInputParams.JSON_BODY, MediaType.APPLICATION_JSON);

		assertEquals(ApiTestUtils.BIN_RANGE_RESPONSE, apiDataProperties.getResponse());

		assertEquals(binRangeURL, apiDataProperties.getRequestURL());
		assertEquals(BinRangeInputParams.JSON_BODY, apiDataProperties.getParameters());
	}
	
	private void configureMockServerForGETRequest() {

		String url = env.getProperty(ApiEndpoints.BIN_RANGE_JSON);
		String requestURL = RequestUtil.createURI(url, BinRangeInputParams.params);
		Collection<List<String>> binRangeParams = BinRangeInputParams.params.values();
		
		// Fetch bin range param values
		List<String> ccnumbersList = new ArrayList<>();
		for (List<String> binRangeParamList : binRangeParams) {
			for (String binRangeParam : binRangeParamList) {
				ccnumbersList.add(binRangeParam);
			}
		}
		
		String[] ccnumbersArray = ccnumbersList.toArray(new String[0]);
		
		// Configure the mock server
		mockServer.reset();
		mockServer.expect(requestTo(requestURL))
		.andExpect(method(HttpMethod.GET))
		.andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
		.andExpect(header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
		.andExpect(header(HttpHeaders.AUTHORIZATION, env.getProperty("t360.config.authorization")))
		.andExpect(header("CLIENT_USER_ID", env.getProperty("t360.config.client_user_id")))
		.andExpect(header("intuit_tid", env.getProperty("t360.config.intuit_tid")))
		.andExpect(queryParam("ccnumber", ccnumbersArray))
		.andRespond(withSuccess(ApiTestUtils.BIN_RANGE_RESPONSE, MediaType.APPLICATION_JSON));
	}
	
	private void configureMockServerForPOSTRequest() {

		// Configure the mock server
		mockServer.reset();
		mockServer.expect(requestTo(env.getProperty(ApiEndpoints.BIN_RANGE)))
		.andExpect(method(HttpMethod.POST))
		.andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
		.andExpect(header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
		.andExpect(header(HttpHeaders.AUTHORIZATION, env.getProperty("t360.config.authorization")))
		.andExpect(header("CLIENT_USER_ID", env.getProperty("t360.config.client_user_id")))
		.andExpect(header("intuit_tid", env.getProperty("t360.config.intuit_tid")))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json(BinRangeInputParams.JSON_BODY))
		.andRespond(withSuccess(ApiTestUtils.BIN_RANGE_RESPONSE, MediaType.APPLICATION_JSON));
	}
}
