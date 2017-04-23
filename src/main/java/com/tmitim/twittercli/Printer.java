package com.tmitim.twittercli;

import java.util.List;

import twitter4j.Status;
import twitter4j.User;
import twitter4j.DirectMessage;
import twitter4j.Location;
import twitter4j.PagableResponseList;

public interface Printer {
	void printStatuses(List<Status> statuses);
	void printStatus(Status status);
	void printAvailableLocations(List<Location> locations);
	void printLocation(Location location);
	void printDirectMessages(List<DirectMessage> messages, String username);
	void printDirectMessage(DirectMessage dm, String username);
	void printUsers(PagableResponseList<User> users);
}
