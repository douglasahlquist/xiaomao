package com.twitter.next.fatcat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.twitter.next.fatcat.services.UserService;

@RestController("helthController")
public class HealthController {

//final static Logger logger = Logger.getLogger(UserController.class);

	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/health", method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8",
			"text/plain" })
	@ResponseBody
	public ResponseEntity<String> health() {

		return new ResponseEntity<String>("health check ok, success", HttpStatus.OK);
	}

}
