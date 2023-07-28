package com.jp319.ZeroArtFetcher.utils.other;

public class StringArrayCheck {
	public static boolean isStringArrayValid(String[] array) {
		return array != null && array.length > 0 && allElementsNotNullOrEmpty(array);
	}
	private static boolean allElementsNotNullOrEmpty(String[] array) {
		for (String str : array) {
			if (str == null || str.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
