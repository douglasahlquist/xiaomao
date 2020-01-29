package com.twitter.next.fatcat.collections;

import java.util.HashMap;

import com.twitter.next.fatcat.entities.User;

/**
 * a Collection of Users where the the key is the twitterId
 * 
 * @author dahlquist
 *
 */
public class UserCollection extends HashMap<String, User> {

	private static final long serialVersionUID = 1366039265947287910L;

}
