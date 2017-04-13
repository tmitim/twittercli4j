package com.tmitim.twittercli.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.tmitim.twittercli.PrinterFactory;

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

	@Arguments(description = "Search terms")
	private List<String> args;

	@Option(name = { "-r", "--recent" }, description = "Search recent tweets")
	private boolean searchRecent;

	@Option(name = { "-p", "--popular" }, description = "Search popular tweets")
	private boolean searchPopular;

	@Option(name = { "-m", "--mixed" }, description = "Search a mix of recent/popular tweets")
	private boolean searchMixed;

	@Override
	public void run() {
		String queryString = StringUtils.join(args, " ");

		Twitter twitter = TwitterFactory.getSingleton();

		List<Status> statuses = new ArrayList<>();
		try {

			System.out.println("Searching for: " + queryString);
			if (queryString != null && !queryString.isEmpty()) {
				Query query = new Query(queryString);
				if (searchRecent) {
					query.setResultType(Query.RECENT);
				}

				if (searchPopular) {
					query.setResultType(Query.POPULAR);
				}

				if ((searchPopular && searchRecent) || searchMixed) {
					query.setResultType(Query.MIXED);
				}

				System.out.println(
						"Searching " + (query.getResultType() == null ? Query.MIXED : query.getResultType())
								+ " results");
				statuses = twitter.search(query).getTweets();
			}

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.reverse(statuses);
		PrinterFactory.getPrinter().printStatuses(statuses);
	}
}