package com.tmitim.twittercli.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.help.BashCompletion;
import com.github.rvesse.airline.help.cli.bash.CompletionBehaviour;
import com.github.rvesse.airline.model.GlobalMetadata;

@Command(name = "help", description = "Get Help")
public class Help implements Runnable {

	@Inject
	private GlobalMetadata<Runnable> global;

	@Arguments(description = "Provides the name of the commands you want to provide help for")
	@BashCompletion(behaviour = CompletionBehaviour.CLI_COMMANDS)
	private List<String> commandNames = new ArrayList<String>();

	@Override
	public void run() {
		try {
			com.github.rvesse.airline.help.Help.help(global, commandNames);
		} catch (IOException e) {
			System.err.println("Failed to output help: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}

}