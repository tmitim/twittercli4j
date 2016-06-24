package com.tmitim.twittercli;

import java.util.ArrayList;
import java.util.List;

import com.github.rvesse.airline.annotations.Command;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "timeline", description = "Get your timeline")
public class TimeLine implements Runnable {

	public void run() {

		System.out.println("Pulling your timeline.");
		Twitter twitter = TwitterFactory.getSingleton();

		List<Status> statuses = new ArrayList<>();
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		for (Status status : statuses) {
			System.out.println(status.getUser().getName() + " (@" + status.getUser().getScreenName() + "): ");
			System.out.println(status.getText());
			System.out.println(status.getCreatedAt());
			System.out.println("");
		}
	}
}