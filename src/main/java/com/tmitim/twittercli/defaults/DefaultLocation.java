package com.tmitim.twittercli.defaults;

public class DefaultLocation implements twitter4j.Location {

	private static final String DEFAULT_LOCATION = "Los Angeles";
	private static final int DEFAULT_WOEID = 2442047;

	@Override
	public String getCountryCode() {
		return null;
	}

	@Override
	public String getCountryName() {
		return null;
	}

	@Override
	public String getName() {
		return DEFAULT_LOCATION;
	}

	@Override
	public int getPlaceCode() {
		return 0;
	}

	@Override
	public String getPlaceName() {
		return null;
	}

	@Override
	public String getURL() {
		return null;
	}

	@Override
	public int getWoeid() {
		return DEFAULT_WOEID;
	}
}
