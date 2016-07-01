package com.tmitim.twittercli;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.tmitim.twittercli.defaults.DefaultLocation;

import twitter4j.Location;

public class Locator {
	public Location findBestLocation(String locationQuery, List<Location> locations) {
		twitter4j.Location closestLocation = new DefaultLocation();
		int bestDistance = Integer.MAX_VALUE;
		for (twitter4j.Location location : locations) {
			int distance = StringUtils.getLevenshteinDistance(location.getName(), locationQuery);
			if (distance < bestDistance) {
				closestLocation = location;
				bestDistance = distance;
			}
		}

		return closestLocation;
	}
}
