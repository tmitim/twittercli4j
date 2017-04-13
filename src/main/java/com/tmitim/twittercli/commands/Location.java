package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.Locator;
import com.tmitim.twittercli.PrinterFactory;
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

	@Option(name = { "-s", "--search" }, description = "search for specific location's with WOEID")
	private String locationQuery;

	@Override
	public void run() {

		try {
			Twitter twitter = TwitterFactory.getSingleton();

			if (isListLocations) {
				PrinterFactory.getPrinter().printAvailableLocations(twitter.getAvailableTrends());
			}

			if (locationQuery != null && !locationQuery.isEmpty()) {
				twitter4j.Location closestLocation = new Locator().findBestLocation(locationQuery,
						twitter.getAvailableTrends());

				PrinterFactory.getPrinter().printLocation(closestLocation);
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

		printDefaultLocation();
	}

	private void printDefaultLocation() {
		System.out.println("Default Location is:");
		PrinterFactory.getPrinter().printLocation(new DefaultLocation());
	}
}