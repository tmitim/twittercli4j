package com.tmitim.twittercli.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.Printer;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "timeline", description = "Get your timeline")
public class TimeLine implements Runnable {

	@Option(name = { "-u", "--user" }, description = "get the timeline for a specific username")
	private String username;

	@Override
	public void run() {

		List<Status> statuses = new ArrayList<>();
		try {
			Twitter twitter = TwitterFactory.getSingleton();
			if ( username != null && !username.isEmpty() ) {
				System.out.println("Pulling @" + username + "'s timeline...");
				statuses = twitter.getUserTimeline(username);
			} else {
				System.out.println("Pulling your home timeline...");
				statuses = twitter.getHomeTimeline();
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

		Collections.reverse(statuses);
		new Printer().printStatuses(statuses);
	}
}