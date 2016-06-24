package com.tmitim.twittercli;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;

@Cli(
		name = "twitter",
		description = "Provides a basic example CLI",
		defaultCommand = Help.class,
		commands = { Tweet.class, TimeLine.class, Help.class }
	)
public class TwitterCli {
	public static void main(String[] args) {
		com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(TwitterCli.class);
		Runnable cmd = cli.parse(args);
		cmd.run();
	}
}
