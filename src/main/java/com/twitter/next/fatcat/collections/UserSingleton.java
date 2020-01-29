package com.twitter.next.fatcat.collections;

import com.twitter.next.fatcat.entities.User;

public class UserSingleton {

	private UserCollection map = null;

	private static UserSingleton instance = null;

	private UserSingleton() {
		map = new UserCollection();
	}

	public static UserSingleton getInstance() {
		if (instance == null) {
			instance = new UserSingleton();
		}
		return instance;
	}

	public User get(String id) {
		return map.get(id);
	}

	public void put(String id, User user) {
		map.put(id, user);
	}

	public void save() {

	}

	public void read() {

	}

}
