package com.jp319.ZeroArtFetcher.utils.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilterManager {
	private static final int MAX_FILTERS = 9;
	private static String[] filters = new String[MAX_FILTERS];
	private static final Lock lock = new ReentrantLock();
	
	private FilterManager() {
		// Private constructor to prevent direct instantiation
	}
	
	// Method to set a filter
	// Method to set a filter or remove the filter if it's already present
	public static void setFilter(String filter) {
		if (filter == null || filter.isEmpty()) {
			throw new IllegalArgumentException("Filter cannot be null or empty");
		}
		lock.lock();
		try {
			int existingIndex = findFilterIndex(filter);
			if (existingIndex >= 0) {
				// Remove the existing filter from the array
				int newIndex = existingIndex;
				for (int i = existingIndex; i < filters.length - 1; i++) {
					filters[i] = filters[i + 1];
					newIndex = i;
				}
				filters[newIndex] = null;
			} else {
				int index = findEmptyIndex();
				if (index >= 0) {
					filters[index] = filter;
				} else {
					throw new IllegalStateException("Filter limit reached. Cannot add more filters.");
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	
	// Method to unset a filter
	public static void unsetFilter(String filter) {
		if (filter == null || filter.isEmpty()) {
			throw new IllegalArgumentException("Filter cannot be null or empty");
		}
		
		lock.lock();
		try {
			int index = findFilterIndex(filter);
			if (index >= 0) {
				filters[index] = null;
			}
		} finally {
			lock.unlock();
		}
	}
	
	// Method to check if a filter is already in the array
	public static boolean containsFilter(String filter) {
		lock.lock();
		try {
			return findFilterIndex(filter) >= 0;
		} finally {
			lock.unlock();
		}
	}
	
	// Method to get all the filters as an array
	public static String[] getFilters() {
		lock.lock();
		try {
			List<String> filterList = new ArrayList<>();
			for (String filter : filters) {
				if (filter != null) {
					filterList.add(filter);
				}
			}
			return filterList.toArray(new String[0]);
		} finally {
			lock.unlock();
		}
	}
	
	// Method to get all the filters concatenated as a single string
	public static String getConcatenatedFilters() {
		lock.lock();
		try {
			StringBuilder concatenatedFilters = new StringBuilder();
			for (String filter : filters) {
				if (filter != null) {
					concatenatedFilters.append(filter);
				}
			}
			return concatenatedFilters.toString();
		} finally {
			lock.unlock();
		}
	}
	
	// Helper method to find an empty index in the filters array
	private static int findEmptyIndex() {
		for (int i = 0; i < filters.length; i++) {
			if (filters[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	// Helper method to find the index of a filter in the filters array
	private static int findFilterIndex(String filter) {
		for (int i = 0; i < filters.length; i++) {
			if (filter != null && filter.equals(filters[i])) {
				return i;
			}
		}
		return -1;
	}
}
