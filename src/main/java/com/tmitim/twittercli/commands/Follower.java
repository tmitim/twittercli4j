package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;
import com.tmitim.twittercli.PrinterFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.PagableResponseList;
/**
 * Get a followers list
 *
 */
@Command(name = "followers", description = "Get your followers list")
public class Follower implements Runnable {

	@Override
	public void run() {
		Twitter twitter = TwitterFactory.getSingleton();

		long cursor = -1;
		try {
			PagableResponseList<User> followers;
			do {
				followers = twitter.getFollowersList(twitter.getScreenName(), cursor);
				PrinterFactory.getPrinter().printUsers(followers);
				
				cursor = followers.getNextCursor();
			} while (followers.hasNext());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}