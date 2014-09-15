package com.juls.rest.services;

import org.springframework.stereotype.Service;

@Service
public class Redirector {
	
	private static final String domain = "http://localhost:8080"; 
			
	public static String redirectToMain(){
		return "redirect:" + domain + "/main.html";
	}
	
	public static String redirectToReg(){
		return "redirect:" + domain + "/reg.html";
	}
	
	public static String redirectToLogin(){
		return "redirect:" + domain + "/login.html";
	}
	
	public static String redirectToCabinet(){
		return "redirect:" + domain + "/cabinet.html";
	}
	
	public static String getEmailConfirmLink(String token){
		return domain + "/new/validate/" + token; //TODO: encode, decode token
	}
}
