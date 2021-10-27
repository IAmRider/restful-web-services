package com.practice.restfulwebservices.restcontroller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	private final int CRITERIA_AGE=27;
	private Logger logger=Logger.getLogger(HomeController.class);
	
	@GetMapping("/")
	public ResponseEntity<String> welcomeToSystem() {
		logger.debug("root of application endpoint called");
		return new ResponseEntity<>("Welcome suraj in sprint boot rest api project", HttpStatus.OK);
	}

	@GetMapping("/employee/{age}")
	public ResponseEntity<String> getStatusForAge(@PathVariable int age, HttpServletResponse response) {

		logger.debug("getStatusForAge method called for homecontroller");
		Cookie cookie = new Cookie("age", String.valueOf(age));
		cookie.setComment("for confirmation that user sent same age");
		cookie.setMaxAge(200);
		// cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);

		return age > CRITERIA_AGE ? ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Age not accepted for this position")
				: ResponseEntity.ok("age is accepted!");

	}

}
