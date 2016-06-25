package com.tmitim.twittercli;

import java.util.ArrayList;
import java.util.List;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "location", description = "get twitte location informationo")
public class Location implements Runnable {

	@Option(name = { "-l", "--list" }, description = "get a list of all twitter locations with weids")
	private boolean isListLocations;
	

	@Override
	public void run() {

		try {
			Twitter twitter = TwitterFactory.getSingleton();
			
			if (isListLocations) { printAllLocations(twitter); }
			
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

	}
	
	private void printAllLocations(Twitter twitter) throws TwitterException{
		for (twitter4j.Location l :twitter.getAvailableTrends()){
			printLocation(l);
		}		
	}
	
	private void printLocation(twitter4j.Location location) {
		System.out.println(location.getName() + ": " + location.getWoeid());
	}
}