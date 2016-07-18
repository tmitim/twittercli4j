package com.tmitim.twittercli.pojos;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * TimeLine pojo
 *
 * @author timothy
 *
 */
public class TimeLine {
	Twitter twitter;
	String username;
	boolean newStatuses;
	List<Status> statuses;

	public TimeLine(Twitter twitter) {
		this.twitter = twitter;
		this.username = "";
		this.newStatuses = false;
		this.statuses = new ArrayList<>();
	}

	public TimeLine setUsername(String username) {
		if (username != null) {
			this.username = username;
		} else {
			this.username = "";
		}
		return this;
	}

	public TimeLine setNewStatuses(boolean newStatuses) {
		this.newStatuses = newStatuses;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public boolean getNewStatuses() {
		return newStatuses;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public TimeLine setStatuses(List<Status> statuses) {
		this.statuses = statuses;
		return this;
	}

	public List<Status> getStatuses() {
		return statuses;
	}
}
