/******************************************
 * Participant.java
 *
 * Stores Participant Object data
 ******************************************/

package textConnection;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Workshop {
	// Initial variable declarations
	private int workshop_ID;
	private Conference workshop_Conference;
	private String workshop_Name;
	private String workshop_Description;
	private Calendar workshop_DateTime;

	// Constructors
	public Workshop() {
		workshop_ID = -1;
		workshop_Conference = new Conference();
		workshop_Name = "N/A";
		workshop_Description = "N/A";
		workshop_DateTime = new GregorianCalendar();
	}

	public Workshop(int id, Conference conf, String name, String desc,
			Calendar datetime) {
		workshop_ID = id;
		workshop_Conference = conf;
		workshop_Name = name;
		workshop_Description = desc;
		workshop_DateTime = datetime;
	}

	// Update Methods
	// manages and changes object variables
	public void updateID(int id) {
		workshop_ID = id;
	}

	public void updateConference(Conference conf) {
		workshop_Conference = conf;
	}

	public void updateName(String name) {
		workshop_Name = name;
	}

	public void updateDescription(String desc) {
		workshop_Description = desc;
	}

	public void updateDate(Calendar date) {
		workshop_DateTime = date;
	}

	// Return Methods
	// returns parts of the object
	public int getID() {
		return workshop_ID;
	}

	public Conference getConference() {
		return workshop_Conference;
	}

	public String getName() {
		return workshop_Name;
	}

	public String getDescription() {
		return workshop_Description;
	}

	public Calendar getDate() {
		return workshop_DateTime;
	}

	@Override
	public String toString() {
		return workshop_ID + "\t" + workshop_Conference.getAbbreviation()
				+ "\t" + workshop_Conference.getLocation() + "\t"
				+ workshop_Name + "\t" + workshop_DateTime.get(Calendar.YEAR)
				+ workshop_DateTime.get(Calendar.MONTH)
				+ workshop_DateTime.get(Calendar.DATE);
	}
}