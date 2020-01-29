package com.twitter.next.fatcat.controllers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@RestController("cashAppAuthorizationRedirectController")
@RequestMapping(value = "/authorizationRedirct")
public class CashAppAuthorizationRedirectController {

	@RequestMapping(method = { RequestMethod.POST }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> receiveRedirect(@RequestBody String jsonAsString) {

		System.out.println("Authorization Redirect Controller: " + jsonAsString);
		JSONObject json = new JSONObject(jsonAsString);

		String redirectUrl = json.getString("redirect_url");
		String qr_url = json.getString("qr_url");

		// added to added referencing cashtag ????? ]
		// auth_code (this is sent from cash app as a parameter in the redirect uri
		// CashAppPostClientAccessToken.getToken(customerId, authorizationCode)
		// during the auth flow)
		// access_token (this is provided by cash app at the completion of the auth
		// flow)

		return new ResponseEntity<String>("success for_uer_id: ", HttpStatus.OK);
	}

}
