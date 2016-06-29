package com.tmitim.twittercli;

import java.util.List;

import twitter4j.Status;

public class Printer {

	public void printStatuses(List<Status> statuses) {
		for (Status status : statuses) {
			printStatus(status);
		}
	}

	public void printStatus(Status status) {
		System.out.println(status.getUser().getName() + " (@" + status.getUser().getScreenName() + "): ");
		System.out.println(status.getText());
		System.out.println(status.getCreatedAt());
		System.out.println(String.format("ID: %d, RT: %d, <3: %d", status.getId(), status.getRetweetCount(),
				status.getFavoriteCount()));
		System.out.println("");
	}

}
