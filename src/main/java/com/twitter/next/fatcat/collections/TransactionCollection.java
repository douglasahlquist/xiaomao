package com.twitter.next.fatcat.collections;

import java.io.Serializable;
import java.util.HashMap;

import com.twitter.next.fatcat.entities.CTransaction;

/**
 * A collection of Transactions where the key is the TweetId
 * 
 * @author dahlquist
 *
 */
public class TransactionCollection extends HashMap<String, CTransaction> implements Serializable {

	private static final long serialVersionUID = 4596911875418147858L;

}
