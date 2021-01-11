package io.github.mstachniuk.graphqljavaexample.user;

import io.github.mstachniuk.graphqljavaexample.search.SearchResult;

public interface User extends SearchResult {
	String getId();
	String getName();
	String getEmail();
}
