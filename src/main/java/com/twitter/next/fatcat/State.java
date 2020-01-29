package com.twitter.next.fatcat;

public enum State {
	TWEETED(1), START_AUTH(2), COMPLETED_AUTH(3), PAYMENT_COMPLETE(4);

	int s;

	State(int state) {
		this.s = state;
	}

	public int getValue() {
		return s;
	}

}
