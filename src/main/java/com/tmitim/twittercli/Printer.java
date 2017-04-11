package com.tmitim.twittercli;

import java.util.List;

import twitter4j.Status;
import twitter4j.DirectMessage;
import twitter4j.Location;

public interface Printer {
	void printStatuses(List<Status> statuses);
	void printStatus(Status status);
	void printAvailableLocations(List<Location> locations);
	void printLocation(Location location);
	void printDirectMessages(List<DirectMessage> messages, String username);
	void printDirectMessage(DirectMessage dm, String username);
}
