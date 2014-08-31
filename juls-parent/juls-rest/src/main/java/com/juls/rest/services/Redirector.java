package com.juls.rest.services;

import org.springframework.stereotype.Service;

@Service
public class Redirector {
	
	private static final String domain = "http://localhost:8080"; 
			
	public static String redirectToMain(){
		return "redirect:" + domain + "/juls-rest/static/html/main.html";
	}
	
	public static String redirectToReg(){
		return "redirect:" + domain + "/juls-rest/static/html/reg.html";
	}
	
	public static String redirectToLogin(){
		return "redirect:" + domain + "/static/html/login.html";
	}
	
	public static String redirectToCabinet(){
		return "redirect:" + domain + "/static/html/cabinet.html";
	}
	
	public static String getEmailConfirmLink(String token){
		return domain + "/juls-rest/new/validate/" + token; //TODO: encode, decode token
	}
}
