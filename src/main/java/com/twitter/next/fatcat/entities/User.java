package com.twitter.next.fatcat.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class User {
	@Id
	@GeneratedValue
	private String id; // TwitterId
	private String username;
	private String cashappid;
	private String cashappsecret;
	private String accessToken;

	private String cashtag;
	private Long creationtime;

	public User() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cashtag == null) {
			if (other.cashtag != null)
				return false;
		} else if (!cashtag.equals(other.cashtag))
			return false;
		if (creationtime == null) {
			if (other.creationtime != null)
				return false;
		} else if (!creationtime.equals(other.creationtime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getCashappid() {
		return cashappid;
	}

	public String getCashappsecret() {
		return cashappsecret;
	}

	public String getCashtag() {
		return cashtag;
	}

	public Long getCreationtime() {
		return creationtime;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cashtag == null) ? 0 : cashtag.hashCode());
		result = prime * result + ((creationtime == null) ? 0 : creationtime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setCashappid(String cashappid) {
		this.cashappid = cashappid;
	}

	public void setCashappsecret(String cashappsecret) {
		this.cashappsecret = cashappsecret;
	}

	public void setCashtag(String cashtag) {
		this.cashtag = cashtag;
	}

	public void setCreationtime(Long creationtime) {
		this.creationtime = creationtime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", cashtag=" + cashtag + ", creationtime=" + creationtime
				+ "]";
	}

}
