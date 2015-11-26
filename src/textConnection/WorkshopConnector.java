/******************************************
 * ParticipantsConnector.java
 *
 * Reads and Manipulates the participants file
 ******************************************/

package textConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class WorkshopConnector {

	// Data is stored in memory as an arraylist
	private ArrayList<Workshop> workshopList = new ArrayList<Workshop>();
	// File Reading objects
	private String path;
	private BufferedReader in;
	private BufferedWriter bw;
	private ConferenceConnector conferenceC = new ConferenceConnector(
			"\\data\\CONFERENCES.txt");
	private int autoincrement = 0;

	// Constructs the connection object
	public WorkshopConnector(String p) {
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
		for (int i = 1, n = workshopList.size(); i < n; i++) {
			delete(1);
		}
	}

	// Goes line by line and stores information into an arraylist of objects
	public void read() {
		try {
			workshopList.clear();
			in = new BufferedReader(new FileReader(path));
			// First Line is the auto incrementation value
			autoincrement = Integer.parseInt(in.readLine());
			while (in.ready()) {
				// Temp data
				String tempLine = in.readLine();
				int id = -1;
				Conference conf = new Conference();
				String name = "";
				String desc = "";
				Calendar datetime = new GregorianCalendar();

				// Follows assumption that data is stored as
				// id,conferencecode,name,description,date(YYYMMDD),time(HH:MM)
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
							// Third entry, Name
						} else if (x == 3) {
							name = tempLine.substring(0, i).replace('|', ',');
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Fourth entry, Description
						} else if (x == 4) {
							desc = tempLine.substring(0, i).replace('|', ',');
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Fifth entry, Date
						} else if (x == 5) {
							datetime = new GregorianCalendar(
									Integer.parseInt(tempLine.substring(0, 4)),
									Integer.parseInt(tempLine.substring(4, 6)) - 1,
									Integer.parseInt(tempLine.substring(6, 8)));
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Sixth entry, Time
						}
					}
					if (x == 6) {
						datetime.set(Calendar.HOUR_OF_DAY,
								Integer.parseInt(tempLine.substring(0, 2)));
						datetime.set(Calendar.MINUTE,
								Integer.parseInt(tempLine.substring(3, 5)));
						break;
					}
				}
				workshopList.add(new Workshop(id, conf, name, desc, datetime));
			}
			in.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.setBounds(100, 100, 350, 200);
			dialog.setLocationRelativeTo(null);
			dialog.txtrField
					.setText("Something went wrong while trying to read the file! Please double check WORKSHOPS.txt and ensure that the first line is set as the highest ID number and every other line is formatted as \"id,Conference Code,Conference Name,Conference Description,YYYYMMDD,HH:MM\"");
			dialog.setTitle("Error:File Corrupt");
			dialog.setVisible(true);
		}
	}

	// Writes the array into the flat text at the designated path
	public void write() {
		try {

			Workshop[] writeList = workshopList
					.toArray(new Workshop[workshopList.size()]);

			Arrays.sort(writeList, new Comparator<Workshop>() {
				@Override
				public int compare(Workshop p1, Workshop p2) {
					return p1.getName().toLowerCase()
							.compareTo(p2.getName().toLowerCase());
				}
			});

			Arrays.sort(writeList, new Comparator<Workshop>() {
				@Override
				public int compare(Workshop p1, Workshop p2) {
					return p1.getDate().compareTo(p2.getDate());
				}
			});

			SimpleDateFormat formatDate = new SimpleDateFormat("dd");
			SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
			SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
			bw = new BufferedWriter(new FileWriter(path));
			bw.write("" + autoincrement);
			bw.newLine();
			for (Workshop element : writeList) {
				bw.write(element.getID() + ","
						+ element.getConference().getAbbreviation() + ","
						+ element.getName().replace(',', '|') + ","
						+ element.getDescription().replace(',', '|') + ","
						+ formatYear.format(element.getDate().getTime())
						+ formatMonth.format(element.getDate().getTime())
						+ formatDate.format(element.getDate().getTime()) + ","
						+ formatTime.format(element.getDate().getTime()));
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Deletes an arraylist entry at index
	public Workshop delete(int index) {
		Workshop temp = workshopList.get(index);
		workshopList.remove(index);
		return temp;
	}

	// Changes a designated entry in the arraylist
	public void update(int index, String name, String description,
			Calendar date, Conference conf) {
		workshopList.get(index).updateName(name);
		workshopList.get(index).updateDescription(description);
		workshopList.get(index).updateDate(date);
		workshopList.get(index).updateConference(conf);
	}

	// Inserts data into the arraylist
	public void insert(Conference conf, String name, String desc,
			Calendar datetime) {
		autoincrement++;
		workshopList
				.add(new Workshop(autoincrement, conf, name, desc, datetime));
	}

	// Returns the type at a specified index
	public Workshop get(int index) {
		return workshopList.get(index);
	}

	public Workshop[] get(Integer[] index) {
		ArrayList<Workshop> returnArray = new ArrayList<Workshop>();
		for (Integer element : index) {
			returnArray.add(workshopList.get(element));
		}
		return returnArray.toArray(new Workshop[returnArray.size()]);
	}

	// Returns the entire file as an array of objects
	public Workshop[] getData() {
		return workshopList.toArray(new Workshop[workshopList.size()]);
	}

	// Returns an array of indexes containing the specified query
	public Integer[] search(int column, String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < workshopList.size(); i++) {
			if (column == 1 && ("" + workshopList.get(i).getID()).equals(query)) {
				result.add(i);
			} else if (column == 2
					&& workshopList.get(i).getConference().getAbbreviation()
							.contains(query)) {
				result.add(i);
			} else if (column == 3
					&& workshopList.get(i).getName().contains(query)) {
				result.add(i);
			} else if (column == 4
					&& workshopList.get(i).getDescription().contains(query)) {
				result.add(i);
			} else if (column == 5
					&& (("" + workshopList.get(i).getDate().get(Calendar.YEAR))
							.contains(query)
							|| ("" + workshopList.get(i).getDate()
									.get(Calendar.MONTH)).contains(query) || ("" + workshopList
							.get(i).getDate().get(Calendar.DATE))
							.contains(query))) {
				result.add(i);
			} else if (column == 6
					&& (("" + workshopList.get(i).getDate()
							.get(Calendar.HOUR_OF_DAY)).contains(query) || ("" + workshopList
							.get(i).getDate().get(Calendar.MINUTE))
							.contains(query))) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	public Integer[] search(String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < workshopList.size(); i++) {
			if (("" + workshopList.get(i).getID()).contains(query)) {
				result.add(i);
			} else if (workshopList.get(i).getConference().getAbbreviation()
					.contains(query)) {
				result.add(i);
			} else if (workshopList.get(i).getName().contains(query)) {
				result.add(i);
			} else if (workshopList.get(i).getDescription().contains(query)) {
				result.add(i);
			} else if (("" + workshopList.get(i).getDate().get(Calendar.YEAR))
					.contains(query)
					|| ("" + workshopList.get(i).getDate().get(Calendar.MONTH))
							.contains(query)
					|| ("" + workshopList.get(i).getDate().get(Calendar.DATE))
							.contains(query)) {
				result.add(i);
			} else if (("" + workshopList.get(i).getDate()
					.get(Calendar.HOUR_OF_DAY)).contains(query)
					|| ("" + workshopList.get(i).getDate().get(Calendar.MINUTE))
							.contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	@Override
	public String toString() {
		return workshopList.toArray(new Workshop[workshopList.size()])
				.toString();
	}
}
