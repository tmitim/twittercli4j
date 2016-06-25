package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.defaults.DefaultLocation;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "location", description = "get twitter location information")
public class Location implements Runnable {

	@Option(name = { "-l", "--list" }, description = "get a list of all twitter locations with WOEID")
	private boolean isListLocations;

	@Override
	public void run() {

		try {
			Twitter twitter = TwitterFactory.getSingleton();

			if (isListLocations) {
				printAvailableLocations(twitter);
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

		printDefaultLocation();
	}

	private void printAvailableLocations(Twitter twitter) throws TwitterException {
		for (twitter4j.Location l :twitter.getAvailableTrends()){
			printLocation(l);
		}
	}

	private void printLocation(twitter4j.Location location) {
		System.out.println(location.getName() + ": " + location.getWoeid());
	}

	private void printDefaultLocation() {
		System.out.println("Default Location is:");
		printLocation(new DefaultLocation());
	}
}