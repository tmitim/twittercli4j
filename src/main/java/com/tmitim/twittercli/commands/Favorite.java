package com.tmitim.twittercli.commands;

import java.util.Collections;
import java.util.List;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.PrinterFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Status;
/**
 * Like a tweet
 *
 */
@Command(name = "favorite", description = "Favorite a tweet (tweetId)")
public class Favorite implements Runnable {

	@Option(name = { "-u", "--unfavorite" }, description = "unfavorite a tweet")
	private long unfavoriteId;

	@Option(name = { "-f", "--favorite" }, description = "favorite a tweet")
	private long favoriteId;

	@Option(name = { "-l", "--list" }, description = "List your favorite tweets")
	private boolean listFlag;

	@Option(name = { "-lu", "--listuser" }, description = "List a user's favorite tweets")
	private String favoriteUsername;

	Twitter twitter;

	@Override
	public void run() {
		twitter = TwitterFactory.getSingleton();

		try {
			if (favoriteId != 0) {
				favorite();
			} else if (unfavoriteId != 0) {
				unfavorite();
			} else if (listFlag) {
				PrinterFactory.getPrinter().printStatuses(reverseStatuses(twitter.getFavorites()));
			} else if (favoriteUsername != null) {
				PrinterFactory.getPrinter().printStatuses(reverseStatuses(twitter.getFavorites(favoriteUsername)));
			} else {
				printInstructions();
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	private void favorite() throws TwitterException {
		Status status = twitter.createFavorite(favoriteId);
		System.out.println("Favorited this tweet");
		PrinterFactory.getPrinter().printStatus(status);
	}

	private void unfavorite() throws TwitterException {
		Status status = twitter.destroyFavorite(unfavoriteId);
		System.out.println("Unfavorited this tweet");
		PrinterFactory.getPrinter().printStatus(status);
	}

	private void printInstructions() {
		System.out.println("\t-f <tweetId>, --favorite <tweetId>");
		System.out.println("\t\tFavorite a tweet");
		System.out.println("\t-u <tweetId>, --unfavorite <tweetId>");
		System.out.println("\t\tUnfavorite a tweet");
		System.out.println("\t-l, --list");
		System.out.println("\t\tList your favorite tweets");
		System.out.println("\t-lu, --listuser");
		System.out.println("\t\tList a user's favorite tweets");
	}

	private List<Status> reverseStatuses(List<Status> statuses) {
		Collections.reverse(statuses);
		return statuses;
	}
}