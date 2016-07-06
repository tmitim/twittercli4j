package com.tmitim.twittercli.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.tmitim.twittercli.Printer;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Get timeline
 */
@Command(name = "dm", description = "DirectMessages")
public class DirectMessage implements Runnable {

	Twitter twitter;

	@Arguments(description = "Username + Message")
	private List<String> args;

	@Override
	public void run() {

		try {
			twitter = TwitterFactory.getSingleton();
			if (args == null) {
				List<twitter4j.DirectMessage> messages = twitter.getDirectMessages();
				messages.addAll(twitter.getSentDirectMessages());
				Collections.sort(messages, getCreatedDate());
				new Printer().printDirectMessages(messages, twitter.getScreenName());
			} else if (args.size() > 1) {
				String message = StringUtils.join(args.toArray(), " ", 1, args.size());
				twitter.sendDirectMessage(args.get(0), message);
				System.out.println("Message sent to " + args.get(0));
			}

		} catch (TwitterException e) {
			System.out.println(e.getErrorMessage());
		}
	}

	private static Comparator<twitter4j.DirectMessage> getCreatedDate() {
		Comparator<twitter4j.DirectMessage> comp = new Comparator<twitter4j.DirectMessage>() {
			@Override
			public int compare(twitter4j.DirectMessage s1, twitter4j.DirectMessage s2) {
				return s1.getCreatedAt().compareTo(s2.getCreatedAt());
			}
		};
		return comp;
	}
}