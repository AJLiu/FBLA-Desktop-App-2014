/******************************************
 * Participant.java
 *
 * Stores Participant Object data
 ******************************************/

package textConnection;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Participant {
	// Initial variable declarations
	private int participant_ID;
	private Conference participant_Conference;
	private Type participant_Type;
	private String participant_FirstName;
	private String participant_LastName;
	private int participant_Chapter;

	// Constructors
	public Participant() {
		participant_ID = -1;
		participant_Conference = new Conference();
		participant_Type = new Type();
		participant_FirstName = "N/A";
		participant_LastName = "N/A";
		participant_Chapter = -1;
	}

	public Participant(int id, Conference conf, Type type, String fname,
			String lname, int chapter) {
		participant_ID = id;
		participant_Conference = conf;
		participant_Type = type;
		participant_FirstName = fname;
		participant_LastName = lname;
		participant_Chapter = chapter;
	}

	// Update Methods
	// manages and changes object variables
	public void updateID(int id) {
		participant_ID = id;
	}

	public void updateConference(Conference conf) {
		participant_Conference = conf;
	}

	public void updateType(Type type) {
		participant_Type = type;
	}

	public void updateFirstName(String fname) {
		participant_FirstName = fname;
	}

	public void updateLastName(String lname) {
		participant_LastName = lname;
	}

	public void updateChapter(int chapter) {
		participant_Chapter = chapter;
	}

	// Return Methods
	// returns parts of the object
	public int getID() {
		return participant_ID;
	}

	public Conference getConference() {
		return participant_Conference;
	}

	public Type getType() {
		return participant_Type;
	}

	public String getFirstName() {
		return participant_FirstName;
	}

	public String getLastName() {
		return participant_LastName;
	}

	public int getChapter() {
		return participant_Chapter;
	}

	@Override
	public String toString() {
		return participant_ID + "\t" + participant_Conference.getAbbreviation()
				+ "\t" + participant_Type.getAbbreviation() + "\t"
				+ participant_FirstName + " " + participant_LastName + "\t"
				+ participant_Chapter;
	}
}