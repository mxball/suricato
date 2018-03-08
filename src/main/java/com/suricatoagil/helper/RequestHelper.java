package com.suricatoagil.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestHelper {

	
	@Autowired
	private HttpServletRequest request;
	
	
	public String getBaseURL(){
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		int startUri = url.indexOf(uri);
		
		url.delete(startUri, startUri + uri.length());
		
		return url.toString();
	}
	
}
