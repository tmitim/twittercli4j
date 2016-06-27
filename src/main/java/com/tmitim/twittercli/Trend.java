package com.tmitim.twittercli;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.defaults.DefaultLocation;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "trend", description = "Get what's trending")
public class Trend implements Runnable {

	@Option(name = { "-w", "--woied" }, description = "get a specific woeid's trends")
	private int woeid;

	@Override
	public void run() {

		if (woeid == 0) {
			woeid = new DefaultLocation().getWoeid();
		}

		System.out.println("Pulling trends...");

		try {
			Twitter twitter = TwitterFactory.getSingleton();
			Trends trends = twitter.getPlaceTrends(woeid);
			for (twitter4j.Trend t : trends.getTrends()) {
				System.out.println(t.getName());
			}

			System.out.println("Location: " + trends.getLocation().getName());

		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}
	}
}