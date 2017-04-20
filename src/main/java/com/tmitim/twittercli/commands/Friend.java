package com.tmitim.twittercli.commands;

import com.github.rvesse.airline.annotations.Command;

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
				friends.forEach(x -> System.out.println(String.format(
					"%s - %s",
					x.getScreenName(),
					x.getName())
				));
				
				cursor = friends.getNextCursor();
			} while (friends.hasNext());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}