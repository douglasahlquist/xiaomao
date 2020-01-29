package com.twitter.next.fatcat.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.twitter.next.fatcat.Constants;

public class CashAppPostClientAccessToken {

	public static Optional<String> getToken(final String customerId, final String authorizationCode)
			throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		// POST Header

		String url = Constants.CASHAPP_ACCESS_TOKEN;
		System.out.println("Assembling POST Request for CashApp url: " + url);
		URI uri = new URI(url);
		JSONObject json = new JSONObject();
		json.append("authorization_code", authorizationCode);
		json.append("customer_id", customerId);
		// json.append("scopes, customerId);

		HttpHeaders headers = new HttpHeaders();
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// headers.setContentType(MediaType.APPLICATION_JSON);
		String authValue = Constants.CASHAPP_CLIENTID + " " + customerId + " " + Constants.CASHAPP_CLIENT_SECRET;
		System.out.println("authValue: " + authValue);
		headers.set("Authorization", authValue);

		HttpEntity<JSONObject> request = new HttpEntity<>(json, headers);
		ResponseEntity<JSONObject> result = restTemplate.postForEntity(uri, request, JSONObject.class);

		int retCode = result.getStatusCodeValue();
		if (retCode == HttpStatus.OK.value()) {
			JSONObject responseJson = result.getBody();
			System.out.println("Access_Token: " + json.toString());
			return Optional.of(responseJson.getString("access_token"));
		}
		return Optional.empty();
	}

	public static void main(String[] args) {

	}
}
