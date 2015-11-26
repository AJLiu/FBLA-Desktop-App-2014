/******************************************
 * WorkshopRegistration.java
 *
 * Stores Workshop Registration Object data
 ******************************************/

package textConnection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class WorkshopRegistration {
	// Initial variable declarations
	private Workshop workshopRegistration_Workshop;
	private Participant[] workshopRegistration_Participant;

	// Constructors
	public WorkshopRegistration() {
		Participant[] foo = { new Participant() };
		workshopRegistration_Workshop = new Workshop();
		workshopRegistration_Participant = foo;
	}

	public WorkshopRegistration(Workshop workshop, Participant[] participant) {
		workshopRegistration_Workshop = workshop;
		workshopRegistration_Participant = participant;
	}

	// Update Methods
	// manages and changes object variables
	public void updateWorkshop(Workshop workshop) {
		workshopRegistration_Workshop = workshop;
	}

	public void updateParticipants(Participant[] participant) {
		workshopRegistration_Participant = participant;
	}

	public void addParticipant(Participant[] participant) {
		ArrayList<Participant> temp = new ArrayList<Participant>(
				Arrays.asList(workshopRegistration_Participant));
		for (Participant x : participant) {
			temp.add(x);
		}
		workshopRegistration_Participant = temp.toArray(new Participant[temp
				.size()]);
	}

	public void removeParticipant(Participant participant) {
		ArrayList<Participant> temp = new ArrayList<Participant>(
				Arrays.asList(workshopRegistration_Participant));
		for (int x = 0; x < temp.size(); x++) {
			if (participant.getID() == temp.get(x).getID()) {
				temp.remove(x);
				x--;
			}
		}
		workshopRegistration_Participant = temp.toArray(new Participant[temp
				.size()]);
	}

	// Return Methods
	// returns parts of the object
	public Workshop getWorkshop() {
		return workshopRegistration_Workshop;
	}

	public Participant[] getParticipants() {
		return workshopRegistration_Participant;
	}

	@Override
	public String toString() {
		String participantStrings = "";
		for (Participant i : workshopRegistration_Participant) {
			participantStrings += i.getID() + ":" + i.getFirstName() + " "
					+ i.getLastName();
		}
		return workshopRegistration_Workshop.getID() + ":"
				+ workshopRegistration_Workshop.getName() + "\t"
				+ participantStrings;
	}
}