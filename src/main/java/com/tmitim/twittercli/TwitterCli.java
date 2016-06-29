package com.tmitim.twittercli;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;
import com.tmitim.twittercli.commands.Location;
import com.tmitim.twittercli.commands.TimeLine;
import com.tmitim.twittercli.commands.Trend;
import com.tmitim.twittercli.commands.Tweet;
import com.tmitim.twittercli.commands.Search;

@Cli(
		name = "twitter",
		description = "Provides a basic example CLI",
		defaultCommand = Help.class,
		commands = { Tweet.class, TimeLine.class, Location.class, Trend.class, Search.class, Help.class }
	)
public class TwitterCli {
	public static void main(String[] args) {

		com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(TwitterCli.class);
		Runnable cmd = cli.parse(args);
		cmd.run();
	}
}
