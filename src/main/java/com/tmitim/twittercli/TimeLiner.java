package com.tmitim.twittercli;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tmitim.twittercli.pojos.TimeLine;
import com.tmitim.twittercli.preferences.TimeLinePreference;

import twitter4j.Status;
import twitter4j.TwitterException;

public class TimeLiner {
	TimeLine timeline;

	public TimeLiner(TimeLine timeline) {
		this.timeline = timeline;
	}

	public List<Status> pullStatuses() {
		try {
			if (!timeline.getUsername().isEmpty()) {
				pullUserTimeline();
			} else {
				pullMyTimeline();
				if (timeline.getNewStatuses()) {
					useOnlyNewStatuses();
				}
			}
		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}

		return timeline.getStatuses();
	}

	public void updateLastCheck() {
		TimeLinePreference.putLastChecked(new Date());
	}

	public void clearLastCheck() {
		System.out.println("Clearing last checked time.");

		TimeLinePreference.clearCheck();
	}

	public void printStatuses() {
		Collections.reverse(timeline.getStatuses());
		new Printer().printStatuses(timeline.getStatuses());
	}

	private void pullMyTimeline() throws TwitterException {
		System.out.println("Pulling your home timeline...");
		timeline.setStatuses(timeline.getTwitter().getHomeTimeline());
	}

	private void pullUserTimeline() throws TwitterException {
		System.out.println("Pulling @" + timeline.getUsername() + "'s timeline...");
		timeline.setStatuses(timeline.getTwitter().getUserTimeline(timeline.getUsername()));
	}

	private void useOnlyNewStatuses() {
		System.out.println("Showing only new tweets.");

		timeline.setStatuses(timeline.getStatuses().stream()
				.filter(t -> t.getCreatedAt().getTime() > TimeLinePreference.getLastChecked())
				.collect(Collectors.toList()));
	}
}