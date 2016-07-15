package com.tmitim.twittercli.preferences;

import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class TimeLinePreference {

	private static final String LAST_CREATED_DATE = "last_created_date";

	public static Long getLastChecked() {
		Preferences prefs = Preferences.userNodeForPackage(TimeLinePreference.class);

		return prefs.getLong(LAST_CREATED_DATE, 0);
	}

	public static Long putLastChecked(Date lastChecked) {

		Preferences prefs = Preferences.userNodeForPackage(TimeLinePreference.class);
		prefs.putLong(LAST_CREATED_DATE, lastChecked.getTime());

		return prefs.getLong(LAST_CREATED_DATE, 0);
	}

	public static void clearCheck() {
		Preferences prefs = Preferences.userNodeForPackage(TimeLinePreference.class);
		try {
			prefs.clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
}
