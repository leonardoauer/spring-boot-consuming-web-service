package com.lauer.app.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.lauer.app.api.endpoints.ApiEndpoints;
import com.lauer.app.api.properties.ApiDataProperties;
import com.lauer.app.service.IDemoAppService;
import com.lauer.app.test.util.BinRangeInputParams;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoAppController.class)
public class DemoAppControllerTest {

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private IDemoAppService service;
    
    @Autowired
    private Environment env;
    
    @Test
	public void checkBinRangeXmlGet() throws Exception {

		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		
        when(service.getBinRange(requestURL, BinRangeInputParams.params, MediaType.APPLICATION_XML)).thenReturn(new ApiDataProperties());
        
        mvc.perform(get("/binrange")
        	.params(BinRangeInputParams.params)
        	.contentType(MediaType.APPLICATION_XML_VALUE)
        	.accept(MediaType.APPLICATION_XML_VALUE))
        	.andExpect(status().isOk());
        
        verify(service, times(1)).getBinRange(requestURL, BinRangeInputParams.params, MediaType.APPLICATION_XML);
        verifyNoMoreInteractions(service);
	}
    
    @Test
	public void checkBinRangeJsonGet() throws Exception {

		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE_JSON);
		
        when(service.getBinRange(requestURL, BinRangeInputParams.params, MediaType.APPLICATION_JSON)).thenReturn(new ApiDataProperties());
        
        mvc.perform(get("/binrange")
        	.params(BinRangeInputParams.params)
        	.contentType(MediaType.APPLICATION_JSON)
        	.accept(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
        
        verify(service, times(1)).getBinRange(requestURL, BinRangeInputParams.params, MediaType.APPLICATION_JSON);
        verifyNoMoreInteractions(service);
	}
    
    @Test
	public void checkBinRangeXmlPost() throws Exception {

		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		
        when(service.getBinRange(requestURL, BinRangeInputParams.XML_BODY, MediaType.APPLICATION_XML)).thenReturn(new ApiDataProperties());
        
        mvc.perform(post("/binrange")
        	.content(BinRangeInputParams.XML_BODY)
        	.contentType(MediaType.APPLICATION_XML_VALUE)
        	.accept(MediaType.APPLICATION_XML_VALUE))
        	.andExpect(status().isOk());
        
        verify(service, times(1)).getBinRange(requestURL, BinRangeInputParams.XML_BODY, MediaType.APPLICATION_XML);
        verifyNoMoreInteractions(service);
	}
    
    @Test
	public void checkBinRangeJsonPost() throws Exception {

		String requestURL = env.getProperty(ApiEndpoints.BIN_RANGE);
		
        when(service.getBinRange(requestURL, BinRangeInputParams.JSON_BODY, MediaType.APPLICATION_JSON)).thenReturn(new ApiDataProperties());
        
        mvc.perform(post("/binrange")
            .content(BinRangeInputParams.JSON_BODY)
        	.contentType(MediaType.APPLICATION_JSON_VALUE)
        	.accept(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isOk());
        
        verify(service, times(1)).getBinRange(requestURL, BinRangeInputParams.JSON_BODY, MediaType.APPLICATION_JSON);
        verifyNoMoreInteractions(service);
	}
}
