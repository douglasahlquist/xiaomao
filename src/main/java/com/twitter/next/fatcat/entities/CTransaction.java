package com.twitter.next.fatcat.entities;

public class CTransaction {

	String tweetId; // the object's Id
	String fromTwitterId;
	String toTwitterId;
	String forUserId;
	int state;
	String createTime;
	String updateTime;
	String text;
	String json;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTransaction other = (CTransaction) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (forUserId == null) {
			if (other.forUserId != null)
				return false;
		} else if (!forUserId.equals(other.forUserId))
			return false;
		if (fromTwitterId == null) {
			if (other.fromTwitterId != null)
				return false;
		} else if (!fromTwitterId.equals(other.fromTwitterId))
			return false;
		if (json == null) {
			if (other.json != null)
				return false;
		} else if (!json.equals(other.json))
			return false;
		if (state != other.state)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (toTwitterId == null) {
			if (other.toTwitterId != null)
				return false;
		} else if (!toTwitterId.equals(other.toTwitterId))
			return false;
		if (tweetId == null) {
			if (other.tweetId != null)
				return false;
		} else if (!tweetId.equals(other.tweetId))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getForUserId() {
		return forUserId;
	}

	public String getFromTwitterId() {
		return fromTwitterId;
	}

	public String getJson() {
		return json;
	}

	public int getState() {
		return state;
	}

	public String getText() {
		return text;
	}

	public String getToTwitterId() {
		return toTwitterId;
	}

	public String getTweetId() {
		return tweetId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((forUserId == null) ? 0 : forUserId.hashCode());
		result = prime * result + ((fromTwitterId == null) ? 0 : fromTwitterId.hashCode());
		result = prime * result + ((json == null) ? 0 : json.hashCode());
		result = prime * result + state;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((toTwitterId == null) ? 0 : toTwitterId.hashCode());
		result = prime * result + ((tweetId == null) ? 0 : tweetId.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setForUserId(String forUserId) {
		this.forUserId = forUserId;
	}

	public void setFromTwitterId(String fromTwitterId) {
		this.fromTwitterId = fromTwitterId;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setToTwitterId(String toTwitterId) {
		this.toTwitterId = toTwitterId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CTransaction [tweetId=" + tweetId + ", fromTwitterId=" + fromTwitterId + ", toTwitterId=" + toTwitterId
				+ ", forUserId=" + forUserId + ", state=" + state + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", text=" + text + ", json=" + json + "]";
	}

}
