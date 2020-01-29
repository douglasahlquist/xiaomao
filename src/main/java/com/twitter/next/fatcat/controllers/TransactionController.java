package com.twitter.next.fatcat.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.next.fatcat.collections.TransactionSingleton;
import com.twitter.next.fatcat.entities.CTransaction;

//import com.twitter.next.fatcat.services.UserService;

@RestController("transactionController")
public class TransactionController {

	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/transcount", method = { RequestMethod.GET }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> getTweetCount() {
		
		TransactionSingleton trxSingleton = TransactionSingleton.getInstance();
		trxSingleton.getCount();

		int count = trxSingleton.getCount();
		
		return new ResponseEntity<String>(
				"success finding trx count " + count, HttpStatus.OK);
	}
	
	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/transprint", method = { RequestMethod.GET }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> displayTweets() {
		
		TransactionSingleton trxSingleton = TransactionSingleton.getInstance();
		List<CTransaction> list = trxSingleton.getAllTransactions();
        StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<list.size() ; i++) {
			sb.append(list.get(i).toString());
			sb.append("\n\n");
		}
		
		return new ResponseEntity<String>(
				"success all transactions " + sb.toString(), HttpStatus.OK);
	}
	
	
	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/transaction/{twitterid}", method = { RequestMethod.GET }, produces = {
			"application/json;charset=UTF-8", "text/plain" })
	@ResponseBody
	public ResponseEntity<String> getByTweetId(@PathVariable("twitterid") String twitterid) {
		logger.debug("read twitterid:" + twitterid.toString());
		TransactionSingleton trxSingleton = TransactionSingleton.getInstance();
		CTransaction trx = trxSingleton.get(twitterid);
		int count = trxSingleton.getCount();
		if (trx == null) {
			logger.error("No Transaction Object found for twitterid:" + twitterid);
			return new ResponseEntity<String>(
					"error transaction for tweetid:" + twitterid.toString() + " NOT FOUND size:" + count,
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(
				"success finding trx for twitterid" + twitterid.toString() + " state:" + trx.getState(), HttpStatus.OK);
	}

}
