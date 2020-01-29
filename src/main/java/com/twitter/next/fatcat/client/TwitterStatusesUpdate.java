package com.twitter.next.fatcat.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.twitter.next.fatcat.Constants;

public class TwitterStatusesUpdate {

	// TODO (dahlquist) : need to add argument to specify what is to update
	public static int update() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		String url = Constants.TWITTER_UPDATE_STATUS_URL;
		URI uri = new URI(url);
		JSONObject json = new JSONObject();

		// TODO (dalhquist) : need to populate the json

		ResponseEntity<JSONObject> result = restTemplate.postForEntity(uri, json, JSONObject.class);

		// Verify request succeed
		result.getStatusCodeValue();
		JSONObject responseJson = result.getBody();
		System.out.println("Redirect_url: " + responseJson.getString("redirect_url: " + responseJson.toString()));

		return result.getStatusCodeValue();
	}

}
