package com.twitter.next.fatcat.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.twitter.next.fatcat.Constants;
import com.twitter.next.fatcat.State;

public class CashAppPostClientAuthorizationLink {

	public final static String CASHAPP_AUTHORIZATION_URL = "https://";

	/**
	 * <code>
	 * 
	 * SENDS IN REQEST JSON 
	 * String state (optional) 
	 * String customer_id (optional) as
	 * String redirect_url (optional) 
	 * String customer_descriptor (optional)
	 * 
	 * RECEIVES JSON
	 * String redirect_url 
	 * String qr_url
	 * </code>
	 * 
	 * @param customerId
	 * 
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getLink(String customerId) throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		String url = Constants.CASHAPP_AUTHORIZATION_URL;
		System.out.println("url: " + url);

		URI uri = new URI(Constants.CASHAPP_AUTHORIZATION_URL);
		HttpHeaders headers = new HttpHeaders();
		String authValue = Constants.CASHAPP_CLIENTID + " " + customerId + " " + Constants.CASHAPP_CLIENT_SECRET;
		System.out.println("authValue: " + authValue);
		headers.set("Authorization", authValue);

		JSONObject json = new JSONObject();
		json.put("state", State.START_AUTH.name());
		json.put("customer_id", customerId);
		json.put("redirect_uri", Constants.HUROKU_AUTHORIZATION_REDIRECT_URL);

		ResponseEntity<JSONObject> result = restTemplate.postForEntity(uri, json, JSONObject.class);

		// Verify request succeed
		result.getStatusCodeValue();
		JSONObject responseJson = result.getBody();
		return responseJson.getString("redirect_url");
	}

	public static void main(String[] args) throws URISyntaxException {

		BasicConfigurator.configure();
		String out = CashAppPostClientAuthorizationLink.getLink("SHOPPABLE_TWEETS");
		System.out.println("Out: " + out);

	}
}
