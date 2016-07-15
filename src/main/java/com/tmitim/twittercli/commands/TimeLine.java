package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.TimeLiner;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "timeline", description = "Get your timeline")
public class TimeLine implements Runnable {

	@Option(name = { "-u", "--user" }, description = "get the timeline for a specific username")
	private String username;

	@Option(name = { "-n", "--new" }, description = "get only new tweets")
	private boolean newStatuses;

	@Option(name = { "-c", "--clear" }, description = "clear last checked time")
	private boolean clearLast;

	@Override
	public void run() {

		TimeLiner tl = new TimeLiner(TwitterFactory.getSingleton())
				.setNewStatuses(newStatuses).setUsername(username);

		tl.getStatuses();

		if (username == null) {
			tl.updateLastCheck();
		}

		if (clearLast) {
			tl.clearLastCheck();
		}

		tl.printStatuses();
	}
}