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

	@Option(name = { "-a", "--all" }, description = "get all tweets (defaults is only new tweets)")
	private boolean allStatuses;

	@Option(name = { "-c", "--clear" }, description = "clear last checked time before pulling timeline")
	private boolean clearLast;

	@Override
	public void run() {

		TimeLiner tl = new TimeLiner(
				new TimeLine(TwitterFactory.getSingleton()).setAllStatuses(allStatuses).setUsername(username));

		if (clearLast) {
			tl.clearLastCheck();
		}

		tl.pullStatuses();

		if (username == null) {
			tl.updateLastCheck();
		}

		tl.printStatuses();
	}
}