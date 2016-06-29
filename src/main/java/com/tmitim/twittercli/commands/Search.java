package com.tmitim.twittercli.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.tmitim.twittercli.Printer;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Tweet to twitter
 *
 */
@Command(name = "search", description = "Search twitter")
public class Search implements Runnable {

	@Arguments(description = "Additional arguments")
	private List<String> args;

	@Override
	public void run() {
		String query = StringUtils.join(args, " ");


		Twitter twitter = TwitterFactory.getSingleton();

		List<Status> statuses = new ArrayList<>();
		try {

			System.out.println("Searching for: " + query);
			if (query != null && !query.isEmpty()) {
				statuses = twitter.search(new Query(query)).getTweets();
			}

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.reverse(statuses);
		new Printer().printStatuses(statuses);
	}
}