package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.Printer;
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
				new Printer().printAvailableLocations(twitter.getAvailableTrends());
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

		printDefaultLocation();
	}

	private void printDefaultLocation() {
		System.out.println("Default Location is:");
		new Printer().printLocation(new DefaultLocation());
	}
}