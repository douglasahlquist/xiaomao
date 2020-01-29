package com.twitter.next.fatcat;

import java.util.Optional;

final public class CashTagUtils {

	public static Optional<String> getTagFromString(final String text) {
		System.out.println("in getTagFromString text: " + text);
		int fIndex = text.indexOf('$');
		System.out.println("fIndex: " + fIndex);
		int sIndex = text.indexOf(" ", fIndex + 1);
		System.out.println("fIndex: " + fIndex + " sIndex: " + sIndex);
		if (fIndex != -1 && sIndex == -1) {
			return Optional.of(text.substring(fIndex));
		}
		if (fIndex == -1 || sIndex == -1) {
			return Optional.empty();
		}

		String str = text.substring(fIndex, sIndex);
		System.out.println("tag found: " + str);

		return Optional.of(str);
	}
}
