package com.twitter.next.fatcat.client;

import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.twitter.next.fatcat.Constants;

public class CashAppPostClientPayment {

	public static JSONObject pay(@NotNull final String id, @NotNull final String customerId, final String alias,
			final long cents, final Boolean capture, final String shippingAddress) {

		RestTemplate restTemplate = new RestTemplate();
		JSONObject requestJson = new JSONObject();
		requestJson.append("id", id);
		requestJson.append("action", "SEND");

		// TODO (dahqluist) : valified the counterparty and id????
		requestJson.append("ammount", "{\"amount\":" + cents + ", \"currency_code\":\"USD\"");
		requestJson.append("counterparty", "{\"id\": \"" + alias + "\"}");

		ResponseEntity<JSONObject> response = restTemplate.postForEntity(Constants.CASHAPP_PAYMENT, requestJson,
				JSONObject.class);

		System.out.println("Cashapp pay response json: " + response.getBody());
		System.out.println("Cashapp pay response http code: " + response.getStatusCodeValue());
		return response.getBody();
	}

	public static void main(String[] args) {

		@NotNull
		String id = "1234";
		@NotNull
		String customerId = "1234";
		String alias = "Empty";
		long cents = 100L;
		Boolean capture = Boolean.FALSE;
		String shippingAddress = "516 Suisse Drive, San Jose, CA 95123";
		JSONObject cpcp = CashAppPostClientPayment.pay(id, customerId, alias, cents, capture, shippingAddress);
		System.out.println(cpcp.toString());

	}

}
