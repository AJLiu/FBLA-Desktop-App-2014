/******************************************
 * WorkshopRegistrationConnector.java
 *
 * Reads and Manipulates the participants file
 ******************************************/

package textConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class WorkshopRegistrationConnector {

	// Data is stored in memory as an arraylist
	private ArrayList<WorkshopRegistration> workshopRegistrationList = new ArrayList<WorkshopRegistration>();
	// File Reading objects
	private String path;
	private BufferedReader in;
	private BufferedWriter bw;
	private WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");
	private ParticipantConnector participantC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");

	// Constructs the connection object
	public WorkshopRegistrationConnector(String p) {
		path = System.getProperty("user.dir") + p;
		if (!new File(path).exists()) {
			try {
				File f = new File(path);
				f.createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Truncates the array
	public void clear() {
		for (int i = 1, n = workshopRegistrationList.size(); i < n; i++) {
			delete(1);
		}
	}

	// Goes line by line and stores information into an arraylist of objects
	public void read() {
		try {
			workshopRegistrationList.clear();
			in = new BufferedReader(new FileReader(path));
			while (in.ready()) {
				// Temp Data
				String tempLine = in.readLine();
				Workshop workshop = new Workshop();
				ArrayList<Participant> participant = new ArrayList<Participant>();

				// Follows assumption that data is stored as
				// workshopID,participantID,participantID,participantID,...
				participantC.read();
				for (int i = 0, x = 1; i < tempLine.length(); i++) {
					// Looks for comma separators and stores information of each
					// line into a temp object
					if (tempLine.charAt(i) == ',') {
						// First entry, Workshop
						if (x == 1) {
							workshopC.read();
							// Searches the file for an entry where the id
							// matches the given id and stores the object to a
							// local variable
							workshop = workshopC.get((workshopC.search(1,
									tempLine.substring(0, i))[0]));
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Second entry, Participant
						} else if (x >= 2) {
							// Searches the file for an entry where the id
							// matches the given id and stores the object to a
							// local arraylist
							participant.add(participantC.get((participantC
									.search(1, tempLine.substring(0, i))[0])));
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
						}
					}
				}
				participant.add(participantC.get((participantC.search(1,
						tempLine)[0])));
				workshopRegistrationList
						.add(new WorkshopRegistration(workshop, participant
								.toArray(new Participant[participant.size()])));
			}
			in.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.setBounds(100, 100, 350, 200);
			dialog.setLocationRelativeTo(null);
			dialog.txtrField
					.setText("Something went wrong while trying to read the file! Please double check WKSHP_REGISTRATIONS.txt and ensure that every line is formatted as \"Workshop ID,Participant ID 1,Participant ID 2,...,Participant ID n\"");
			dialog.setTitle("Error:File Corrupt");
			dialog.setVisible(true);
		}
	}

	// Writes the array into the flat text at the designated path
	public void write() {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < workshopRegistrationList.size(); i++) {
				if (workshopRegistrationList.get(i).getParticipants().length != 0) {
					bw.write(""
							+ workshopRegistrationList.get(i).getWorkshop()
									.getID());
					for (Participant n : workshopRegistrationList.get(i)
							.getParticipants()) {
						bw.write("," + n.getID());
					}
					bw.newLine();
				}
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Deletes an arraylist entry at index
	public void delete(int index) {
		workshopRegistrationList.remove(index);
	}

	public void delete(Workshop workshop) {
		for (int i = 0; i < workshopRegistrationList.size(); i++) {
			if (workshopRegistrationList.get(i).getWorkshop().getID() == workshop
					.getID()) {
				workshopRegistrationList.remove(i);
				break;
			}
		}
	}

	// Changes a designated entry in the arraylist
	public void update(int index, String str) {
		ArrayList<Participant> participant = new ArrayList<Participant>();
		for (int i = 0, x = 1; i < str.length(); i++) {
			if (str.charAt(i) == ',') {
				if (x == 1) {
					workshopC.read();
					workshopRegistrationList.get(index).updateWorkshop(
							workshopC.get((workshopC.search(1,
									str.substring(0, i))[0])));
					str = str.substring(i + 1);
					i = 0;
					x++;
				} else if (x == 2) {
					participant.add(participantC.get((participantC.search(1,
							str.substring(0, i))[0])));
					str = str.substring(i + 1);
					i = 0;
					x++;
				}
			}
		}
		workshopRegistrationList.get(index).updateParticipants(
				participant.toArray(new Participant[participant.size()]));
	}

	// Inserts data into the arraylist
	public void insert(Workshop workshop, Participant[] participant) {
		boolean workshopExists = false;
		for (WorkshopRegistration i : workshopRegistrationList) {
			if (i.getWorkshop().getID() == workshop.getID()) {
				workshopExists = true;
			}
		}
		if (!workshopExists) {
			workshopRegistrationList.add(new WorkshopRegistration(workshop,
					participant));
		} else {
			insertParticipant(workshop, participant);
		}
	}

	public void insert(Workshop workshop, Participant participant) {
		boolean workshopExists = false;
		for (WorkshopRegistration i : workshopRegistrationList) {
			if (i.getWorkshop().getID() == workshop.getID()) {
				workshopExists = true;
			}
		}
		Participant[] part = { participant };
		if (!workshopExists) {
			workshopRegistrationList.add(new WorkshopRegistration(workshop,
					part));

		} else {
			insertParticipant(workshop, part);
		}
	}

	public void insertParticipant(Workshop workshop, Participant[] participant) {
		for (int i = 0; i < workshopRegistrationList.size(); i++) {
			if (workshopRegistrationList.get(i).getWorkshop().getID() == workshop
					.getID()) {
				workshopRegistrationList.get(i).addParticipant(participant);
			}
		}
	}

	public void removeParticipant(Participant participant) {
		for (int i = 0; i < workshopRegistrationList.size(); i++) {

			workshopRegistrationList.get(i).removeParticipant(participant);
		}
	}

	// Returns the type at a specified index
	public WorkshopRegistration get(int index) {
		return workshopRegistrationList.get(index);
	}

	public Participant[] get(Workshop workshop) {
		Participant[] y = new Participant[0];
		for (WorkshopRegistration x : workshopRegistrationList) {
			if (x.getWorkshop().getID() == workshop.getID()) {
				y = x.getParticipants();
			}
		}

		return y;
	}

	public Workshop[] getWorkshops(Participant x) {
		ArrayList<Workshop> workshopList = new ArrayList<Workshop>();
		for (WorkshopRegistration wkshp : workshopRegistrationList) {
			for (Participant part : wkshp.getParticipants()) {
				if (x.getID() == part.getID()) {
					workshopList.add(wkshp.getWorkshop());
				}
			}
		}

		return workshopList.toArray(new Workshop[workshopList.size()]);
	}

	// Returns the entire file as an array of objects
	public WorkshopRegistration[] getData() {
		return workshopRegistrationList
				.toArray(new WorkshopRegistration[workshopRegistrationList
						.size()]);
	}

	// Returns an array of indexes containing the specified query
	public Integer[] search(int column, String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < workshopRegistrationList.size(); i++) {
			if (column == 1
					&& (workshopRegistrationList.get(i).getWorkshop().getID()) == Integer
							.parseInt(query)) {
				result.add(i);
			}
			if (column == 2) {
				for (Participant x : workshopRegistrationList.get(i)
						.getParticipants()) {
					if ((x.getID()) == Integer.parseInt(query)) {
						result.add(i);
					}
				}
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	public Integer[] search(String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < workshopRegistrationList.size(); i++) {
			if (("" + workshopRegistrationList.get(i).getWorkshop().getID())
					.contains(query)) {
				result.add(i);
			}
			for (Participant x : workshopRegistrationList.get(i)
					.getParticipants()) {
				if (("" + x.getID()).contains(query)) {
					result.add(i);
				}
			}
		}
		return result.toArray(new Integer[result.size()]);
	}
}
