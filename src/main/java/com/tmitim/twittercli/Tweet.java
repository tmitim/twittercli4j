package com.tmitim.twittercli;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

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

    @Option(name = { "-f", "--flag" }, description = "An option that requires no values")
    private boolean flag = false;

    @Arguments(description = "Additional arguments")
    private List<String> args;

    public static void main(String[] args) {
        SingleCommand<Tweet> parser = SingleCommand.singleCommand(Tweet.class);
        Tweet cmd = parser.parse(args);
        cmd.run();
    }

    public void run() {
        Twitter twitter = TwitterFactory.getSingleton();

		try {
			Status status = twitter.updateStatus(args.get(0));
	        System.out.println("Successfully updated the status to [" + status.getText() + "].");

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Flag was " + (this.flag ? "set" : "not set"));
        if (args != null)
            System.out.println("Arguments were " + StringUtils.join(args, ","));
    }
}