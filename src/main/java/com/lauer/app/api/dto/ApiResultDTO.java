package com.lauer.app.api.dto;

import org.springframework.util.MultiValueMap;

public class ApiResultDTO {

	private String response;
	private String requestURL;
	private String parameters;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getParameters() {
		return parameters;
	}
	
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setParameters(MultiValueMap<String, String> parameters) {
		
		if(parameters == null || parameters.size() == 0) {
			this.parameters = "";
			return;
		}
		
		this.parameters = parameters.toString();
	}
}
