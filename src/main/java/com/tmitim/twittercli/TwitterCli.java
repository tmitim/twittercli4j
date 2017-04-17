package com.tmitim.twittercli;

import java.util.Arrays;
import java.util.stream.Stream;

import com.github.rvesse.airline.annotations.Cli;
import com.tmitim.twittercli.commands.DirectMessage;
import com.tmitim.twittercli.commands.Favorite;
import com.tmitim.twittercli.commands.Help;
import com.tmitim.twittercli.commands.Location;
import com.tmitim.twittercli.commands.TimeLineCommand;
import com.tmitim.twittercli.commands.Trend;
import com.tmitim.twittercli.commands.Tweet;

import com.tmitim.twittercli.commands.Search;

@Cli(
		name = "twitter",
		description = "Twitter CLI", defaultCommand = TimeLineCommand.class,
		commands = { Tweet.class, TimeLineCommand.class, Location.class, Trend.class, Search.class, DirectMessage.class,
				Favorite.class, Help.class }
	)
public class TwitterCli {
	public static void main(String[] args) {

		com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(TwitterCli.class);
		
		if (Stream.of(args).anyMatch(x -> x.equals("--version") || x.equals(("-v")))) {
			String version = TwitterCli.class.getPackage().getImplementationVersion();
			System.out.println(String.format("TwitterCLI4j: %s", version));
			return;
		}
		
		try {
			Runnable cmd = cli.parse(args);
			cmd.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
