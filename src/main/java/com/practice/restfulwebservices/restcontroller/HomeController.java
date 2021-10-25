package com.practice.restfulwebservices.restcontroller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public ResponseEntity<String> welcomeToSystem() {
		return new ResponseEntity<>("Welcome suraj in sprint boot rest api project", HttpStatus.OK);
	}

	@GetMapping("/employee/{age}")
	public ResponseEntity<String> getStatusForAge(@PathVariable int age, HttpServletResponse response) {

		Cookie cookie = new Cookie("age", String.valueOf(age));
		cookie.setComment("for confirmation that user sent same age");
		cookie.setMaxAge(200);
		// cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);

		return age > 27 ? ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Age not accepted for this position")
				: ResponseEntity.ok("age is accepted!");

	}

}
