package com.twitter.next.fatcat.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.next.fatcat.CashTagUtils;
import com.twitter.next.fatcat.client.CashAppPostClientAccessToken;
import com.twitter.next.fatcat.client.CashAppPostClientAuthorizationLink;
import com.twitter.next.fatcat.collections.TransactionSingleton;
import com.twitter.next.fatcat.collections.UserSingleton;
import com.twitter.next.fatcat.entities.CTransaction;
import com.twitter.next.fatcat.entities.User;

/**
 * THIS IS NOT THREAD SAFE !!!!!! The User & Transaction Singleton are not
 * synchronized!!!!!
 * 
 * @author dahlquist
 *
 */

@RestController("aaapiController")
@RequestMapping(value = "/tweets")
public class AaaPiController {

	private static Logger logger = LoggerFactory.getLogger(AaaPiController.class);

	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(path = "/{twitterid}", method = { RequestMethod.GET }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> getTweets(@PathVariable("twitterid") String twitterid) {
		// logger.debug("twitterid: " + twitterid);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.POST }, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> listen(@RequestBody String jsonAsString) {

		System.out.println(jsonAsString);
		// unique UUID (generated, aka customer id) code complete on the UUID generation
		UUID uuid = UUID.randomUUID();
		logger.debug("uuid:" + uuid.toString());
		// Twitter username (needs to be added to the User entity

		JSONObject json = new JSONObject(jsonAsString);

		String forUserId = json.getString("for_user_id");
		JSONArray array = (JSONArray) json.get("direct_message_events");
		System.out.println("json array size:" + array.length());
		List<String> tags = new ArrayList<>();

		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = (JSONObject) array.get(i);

			String tweetId = obj.getString("id");
			String createTimestamp = obj.getString("created_timestamp");
			JSONObject messageCreate = (JSONObject) obj.get("message_create");
			String recipientId = ((JSONObject) messageCreate.get("target")).getString("recipient_id");
			String senderId = messageCreate.getString("sender_id");
			String text = ((JSONObject) messageCreate.get("message_data")).getString("text");
			System.out.println("TweetId: " + tweetId);
			System.out.println("createTimestamp: " + createTimestamp);
			System.out.println("messageCreate: " + messageCreate.toString());
			System.out.println("recipientId: " + recipientId);
			System.out.println("senderId: " + senderId);
			System.out.println("text: " + text);
			System.out.println("-------------------------------------------");

			CTransaction trx = new CTransaction();
			trx.setTweetId(tweetId);
			trx.setForUserId(forUserId);
			trx.setCreateTime(createTimestamp);
			trx.setFromTwitterId(senderId);
			trx.setJson(json.toString());
			trx.setState(1);
			trx.setText(text);
			trx.setToTwitterId(recipientId);

			// get the cashtag
			Optional<String> oS = CashTagUtils.getTagFromString(text);

			if (oS.isPresent()) {

				UserSingleton userSingleton = UserSingleton.getInstance();
				User user = userSingleton.get(recipientId);
				if (user == null) {
					return new ResponseEntity<String>("No mtching user to twitterId:" + recipientId,
							HttpStatus.BAD_REQUEST);
				}

				// Twitter userid
				// Add Cashtag to User By TwitterId
				String cashTag = oS.get();
				System.out.println("cashTag: " + cashTag);
				tags.add(cashTag);
				user.setCashtag(cashTag);

				TransactionSingleton ctxSingleton = TransactionSingleton.getInstance();
				ctxSingleton.put(tweetId, trx);

				// Update the current user
				userSingleton.put(recipientId, user);
				System.out.println("CTransaction: " + trx.toString());
				System.out.println("User: " + user.toString());
				@SuppressWarnings("unused")
				String authorizationLink = null;
				try {

					// Authorization Link

					// State (this identifies which step the user in at in the flow, e.g. tweeted,
					// started_auth, completed_auth, payment_complete)
					// TweetID (this is the tweet id the user initiated the flow with)
					// redirect_uri (this is sent from cash app in the auth flow) [This should be

					authorizationLink = CashAppPostClientAuthorizationLink.getLink(recipientId);

					// TODU (dalquist) : is the customerId and the UTHORIZATIONCODE CORRECT
					// HERE????????
					Optional<String> oAuthToken = CashAppPostClientAccessToken.getToken("customerId",
							"authorizationCode");
					if (oAuthToken.isPresent()) {
						String authToken = oAuthToken.get();
						user.setAccessToken(authToken);
						userSingleton.put(user.getId(), user);
					} else {
						// TODO (dahlquist) : should implement a non-happy path
					}

				} catch (URISyntaxException e) {

					e.printStackTrace();
				}
			} else {
				System.out.println("cashtag NOT FOUND!!!!!!!!");
				return new ResponseEntity<String>("ignoring this tweet No Cashtag found: " + jsonAsString,
						HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>(
				"success for_uer_id: " + forUserId + " tag count:" + tags.size() + " " + jsonAsString, HttpStatus.OK);
	}

}
