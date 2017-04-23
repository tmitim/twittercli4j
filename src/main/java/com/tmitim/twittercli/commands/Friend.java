package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.tmitim.twittercli.PrinterFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.PagableResponseList;
/**
 * Get a friends list
 *
 */
@Command(name = "friends", description = "Get your friends list")
public class Friend implements Runnable {

	@Override
	public void run() {
		Twitter twitter = TwitterFactory.getSingleton();

		long cursor = -1;
		try {
			PagableResponseList<User> friends;
			do {
				friends = twitter.getFriendsList(twitter.getScreenName(), cursor);
				PrinterFactory.getPrinter().printUsers(friends);
				
				cursor = friends.getNextCursor();
			} while (friends.hasNext());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}