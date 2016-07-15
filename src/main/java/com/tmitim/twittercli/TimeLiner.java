package com.tmitim.twittercli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tmitim.twittercli.preferences.TimeLinePreference;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TimeLiner {
	Twitter twitter;
	String username;
	boolean newStatuses;
	List<Status> statuses;

	public TimeLiner(Twitter twitter) {
		this.twitter = twitter;
		this.username = "";
		this.newStatuses = false;
		this.statuses = new ArrayList<>();
	}

	public TimeLiner setUsername(String username) {
		if (username != null) {
			this.username = username;
		}
		return this;
	}

	public TimeLiner setNewStatuses(boolean newStatuses) {
		this.newStatuses = newStatuses;
		return this;
	}

	public void getStatuses() {
		try {
			if (!username.isEmpty()) {
				getUserTimeline();
			} else {
				getMyTimeline();
				if (newStatuses) {
					useOnlyNewStatuses();
				}
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}
	}

	private void getMyTimeline() throws TwitterException {
		System.out.println("Pulling your home timeline...");
		statuses = twitter.getHomeTimeline();
	}

	private void useOnlyNewStatuses() {
		System.out.println("Showing only new tweets.");

		statuses = statuses.stream().filter(t -> t.getCreatedAt().getTime() > TimeLinePreference.getLastChecked())
				.collect(Collectors.toList());
	}

	private void getUserTimeline() throws TwitterException {
		System.out.println("Pulling @" + username + "'s timeline...");
		statuses = twitter.getUserTimeline(username);
	}

	public void updateLastCheck() {
		TimeLinePreference.putLastChecked(new Date());
	}

	public void clearLastCheck() {
		System.out.println("Clearing last checked time.");

		TimeLinePreference.clearCheck();
	}

	public void printStatuses() {
		Collections.reverse(statuses);
		new Printer().printStatuses(statuses);
	}
}
