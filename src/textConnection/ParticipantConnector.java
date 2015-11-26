/******************************************
 * ParticipantConnector.java
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
import java.util.Arrays;
import java.util.Comparator;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class ParticipantConnector {

	// Data is stored in memory as an arraylist
	private ArrayList<Participant> participantList = new ArrayList<Participant>();
	// File Reading objects
	private String path;
	private BufferedReader in;
	private BufferedWriter bw;
	private ConferenceConnector conferenceC = new ConferenceConnector(
			"\\data\\CONFERENCES.txt");
	private TypeConnector typeC = new TypeConnector("\\data\\TYPE.txt");
	private int autoincrement = 0;

	// Constructs the connection object
	public ParticipantConnector(String p) {
		path = System.getProperty("user.dir") + p;
		if (!new File(path).exists()) {
			try {
				File f = new File(path);
				f.createNewFile();
				bw = new BufferedWriter(new FileWriter(path));
				bw.write("0");
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Truncates the array
	public void clear() {
		for (int i = 1, n = participantList.size(); i < n; i++) {
			delete(1);
		}
	}

	// Goes line by line and stores information into an arraylist of objects
	public void read() {
		try {
			participantList.clear();
			in = new BufferedReader(new FileReader(path));
			// First Line is the auto incrementation value
			autoincrement = Integer.parseInt(in.readLine());
			while (in.ready()) {
				// Temp Data
				String tempLine = in.readLine();
				int id = -1;
				Conference conf = new Conference();
				Type type = new Type();
				String fname = "";
				String lname = "";
				int chapter = -1;

				// Follows assumption that data is stored as
				// id,conferenceCode,typeabbreviation,fname,lname,chapter
				for (int i = 0, x = 1; i < tempLine.length(); i++) {
					// Looks for comma separators and stores information of each
					// line into a temp object
					if (tempLine.charAt(i) == ',') {
						// First entry, id
						if (x == 1) {
							id = Integer.parseInt(tempLine.substring(0, i));
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Second entry, Conference
						} else if (x == 2) {
							conferenceC.read();
							conf = conferenceC.get(conferenceC.search(1,
									tempLine.substring(0, i))[0]);
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Third entry, Type
						} else if (x == 3) {
							typeC.read();
							type = typeC.get(typeC.search(tempLine.substring(0,
									i))[0]);
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Fourth entry, First Name
						} else if (x == 4) {
							fname = tempLine.substring(0, i).replace('|', ',');
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Fifth entry, Last Name
						} else if (x == 5) {
							lname = tempLine.substring(0, i).replace('|', ',');
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
						}
					}
					// Sixth entry, Chapter
					if (x == 6) {
						chapter = Integer.parseInt(tempLine);
						break;
					}
				}
				participantList.add(new Participant(id, conf, type, fname,
						lname, chapter));
			}
			in.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.setBounds(100, 100, 350, 200);
			dialog.setLocationRelativeTo(null);
			dialog.txtrField
					.setText("Something went wrong while trying to read the file! Please double check PARTICIPANTS.txt and ensure that the first line is set as the highest ID number and every other line is formatted as \"id,Conference Code,Type Abbreviation,First Name,Last Name,Chapter Number\"");
			dialog.setTitle("Error:File Corrupt");
			dialog.setVisible(true);
		}
	}

	// Writes the array into the flat text at the designated path
	public void write() {
		try {
			Participant[] writeList = participantList
					.toArray(new Participant[participantList.size()]);

			Arrays.sort(writeList, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getFirstName().compareTo(p2.getFirstName());
				}
			});

			Arrays.sort(writeList, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getLastName().compareTo(p2.getLastName());
				}
			});

			Arrays.sort(writeList, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getConference().getAbbreviation()
							.compareTo(p2.getConference().getAbbreviation());
				}
			});

			bw = new BufferedWriter(new FileWriter(path));
			bw.write("" + autoincrement);
			bw.newLine();
			for (int i = 0; i < participantList.size(); i++) {
				bw.write(writeList[i].getID() + ","
						+ writeList[i].getConference().getAbbreviation() + ","
						+ writeList[i].getType().getAbbreviation() + ","
						+ writeList[i].getFirstName().replace(',', '|') + ","
						+ writeList[i].getLastName().replace(',', '|') + ","
						+ writeList[i].getChapter());
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Deletes an arraylist entry at index
	public void delete(int index) {
		participantList.remove(index);
	}

	// Changes a designated entry in the arraylist
	public Participant update(int index, Conference conf, Type type,
			String fname, String lname, int chapter) {

		participantList.get(index).updateConference(conf);

		participantList.get(index).updateType(type);

		participantList.get(index).updateFirstName(fname);

		participantList.get(index).updateLastName(lname);

		participantList.get(index).updateChapter(chapter);

		return participantList.get(index);
	}

	// Inserts data into the arraylist
	public Participant insert(Conference conf, Type type, String fname,
			String lname, int chapter) {
		autoincrement++;
		Participant part = new Participant(autoincrement, conf, type, fname,
				lname, chapter);
		participantList.add(part);
		return (part);
	}

	// Returns the type at a specified index
	public Participant get(int index) {
		return participantList.get(index);
	}

	public int getID() {
		return autoincrement;
	}

	// Returns the entire file as an array of objects
	public Participant[] getData() {
		return participantList.toArray(new Participant[participantList.size()]);
	}

	// Returns an array of indexes containing the specified query
	public Integer[] search(int column, String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < participantList.size(); i++) {
			if (column == 1
					&& (participantList.get(i).getID()) == Integer
							.parseInt(query)) {
				result.add(i);
			} else if (column == 2
					&& participantList.get(i).getConference().getAbbreviation()
							.contains(query)) {
				result.add(i);
			} else if (column == 3
					&& participantList.get(i).getType().getAbbreviation()
							.contains(query)) {
				result.add(i);
			} else if (column == 4
					&& participantList.get(i).getFirstName().contains(query)) {
				result.add(i);
			} else if (column == 5
					&& participantList.get(i).getLastName().contains(query)) {
				result.add(i);
			} else if (column == 6
					&& ("" + participantList.get(i).getChapter())
							.contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	public Integer[] search(String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < participantList.size(); i++) {
			if (("" + participantList.get(i).getID()).contains(query)) {
				result.add(i);
			} else if (participantList.get(i).getConference().getAbbreviation()
					.contains(query)) {
				result.add(i);
			} else if (participantList.get(i).getType().getAbbreviation()
					.contains(query)) {
				result.add(i);
			} else if (participantList.get(i).getFirstName().contains(query)) {
				result.add(i);
			} else if (participantList.get(i).getLastName().contains(query)) {
				result.add(i);
			} else if (("" + participantList.get(i).getChapter())
					.contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}
}
