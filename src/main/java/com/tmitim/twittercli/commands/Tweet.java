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
			String tweet = StringUtils.join(args, " ");

			System.out.println("Tweet counter: " + tweet.length());
			if (tweet.length() <= 140) {
				Status status = twitter.updateStatus(tweet);
				System.out.println("Successfully updated the status to [" + status.getText() + "].");

			} else {
				System.out.println("Tweet exceeds character limit by " + (tweet.length() - 140) + " characters");
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}