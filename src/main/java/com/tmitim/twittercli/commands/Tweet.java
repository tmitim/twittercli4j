package com.tmitim.twittercli.commands;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Tweet to twitter
 *
 */
@Command(name = "tweet", description = "Tweet your thoughts")
public class Tweet implements Runnable {

	@Arguments(description = "Additional arguments")
	private List<String> args;

	public static void main(String[] args) {
		SingleCommand<Tweet> parser = SingleCommand.singleCommand(Tweet.class);
		Tweet cmd = parser.parse(args);
		cmd.run();
	}

	@Override
	public void run() {
		Twitter twitter = TwitterFactory.getSingleton();

		try {
			Status status = twitter.updateStatus(StringUtils.join(args, " "));
			System.out.println("Successfully updated the status to [" + status.getText() + "].");

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}