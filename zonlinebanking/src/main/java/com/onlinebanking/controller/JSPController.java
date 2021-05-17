package com.onlinebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSPController {

	@RequestMapping("login")
	public String goToLogin() {
		return "login";
	}
	
	@RequestMapping("home")
	public String goToHome() {
		
		return "home";
	}
	
	@RequestMapping("logout")
	public String goToLogut() {
		return "logout";
	}

}
