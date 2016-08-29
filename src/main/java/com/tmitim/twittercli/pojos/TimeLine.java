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
	boolean allStatuses;
	List<Status> statuses;

	public TimeLine(Twitter twitter) {
		this.twitter = twitter;
		this.username = "";
		this.allStatuses = false;
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

	public TimeLine setAllStatuses(boolean allStatuses) {
		this.allStatuses = allStatuses;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public boolean getAllStatuses() {
		return allStatuses;
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
