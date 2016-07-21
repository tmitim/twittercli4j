package com.tmitim.twittercli;

import java.util.List;

import twitter4j.Status;
import twitter4j.DirectMessage;
import twitter4j.Location;

public class Printer {

	public void printStatuses(List<Status> statuses) {
		for (Status status : statuses) {
			printStatus(status);
		}

		if (statuses.isEmpty()) {
			System.out.println("No tweets found");
		}
	}

	public void printStatus(Status status) {
		System.out.println(status.getUser().getName() + " (@" + status.getUser().getScreenName() + "): ");
		System.out.println(status.getText());
		System.out.println(status.getCreatedAt());
		System.out.println(String.format("ID: %d RT: %d <3: %d", status.getId(), status.getRetweetCount(),
				status.getFavoriteCount()));
		System.out.println("");
	}

	public void printAvailableLocations(List<Location> locations) {
		for (twitter4j.Location l : locations) {
			printLocation(l);
		}
	}

	public void printLocation(Location location) {
		System.out.println(location.getName() + ": " + location.getWoeid());
	}

	public void printDirectMessages(List<DirectMessage> messages, String username) {
		for (DirectMessage dm : messages) {
			printDirectMessage(dm, username);
		}
	}

	public void printDirectMessage(DirectMessage dm, String username) {
		if (username.equals(dm.getSenderScreenName())) {
			System.out.println(">> " + dm.getRecipientScreenName() + ": " + dm.getText());
		} else {
			System.out.println("<< " + dm.getSenderScreenName() + ": " + dm.getText());
		}
	}
}
