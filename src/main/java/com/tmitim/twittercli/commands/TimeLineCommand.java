package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.TimeLiner;
import com.tmitim.twittercli.pojos.TimeLine;

import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "timeline", description = "Get your timeline")
public class TimeLineCommand implements Runnable {

	@Option(name = { "-u", "--user" }, description = "get the timeline for a specific username")
	private String username;

	@Option(name = { "-n", "--new" }, description = "get only new tweets")
	private boolean newStatuses;

	@Option(name = { "-c", "--clear" }, description = "clear last checked time")
	private boolean clearLast;

	@Override
	public void run() {

		TimeLiner tl = new TimeLiner(
				new TimeLine(TwitterFactory.getSingleton()).setNewStatuses(newStatuses).setUsername(username));

		tl.pullStatuses();

		if (username == null) {
			tl.updateLastCheck();
		}

		if (clearLast) {
			tl.clearLastCheck();
		}

		tl.printStatuses();
	}
}