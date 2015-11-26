/******************************************
 * ConferenceConnector.java
 *
 * Reads and Manipulates the conference file
 ******************************************/

package textConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

// Conferences 2014
// Nov 7-8 Washington DC
// Nov 14-15 Minneapolis, MN
// Nov 21-22 New Orleans, LA

public class ConferenceConnector {

	// Data is stored in memory as an arraylist
	private ArrayList<Conference> conferenceList = new ArrayList<Conference>();
	// File Reading objects
	private String path;
	private BufferedReader in;
	private BufferedWriter bw;

	// Constructs the connection object
	public ConferenceConnector(String p) {
		path = System.getProperty("user.dir") + p;
		if (!new File(path).exists()) {
			try {
				File f = new File(path);
				f.createNewFile();
				bw = new BufferedWriter(new FileWriter(path));
				for (int i = 1; i <= 3; i++) {
					bw.write("Conf" + i
							+ ",placeHolderLocation,20000101,20000101");
					bw.newLine();
				}
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Truncates the array
	public void clear() {
		for (int i = 1, n = conferenceList.size(); i < n; i++) {
			delete(1);
		}
	}

	// Goes line by line and stores information into an arraylist of objects
	public void read() {
		try {
			conferenceList.clear();
			in = new BufferedReader(new FileReader(path));
			while (in.ready()) {
				// Temp data
				String tempLine = in.readLine();
				String abbr = "";
				String loc = "";
				Calendar initial = new GregorianCalendar();
				Calendar end = new GregorianCalendar();

				// Follows assumption that data is stored as
				// abbreviation,place,startday(YYYYMMDD),endday(YYYYMMDD)
				for (int i = 0, x = 1; i < tempLine.length(); i++) {
					// Looks for comma separators and stores information of each
					// line into a temp object
					if (tempLine.charAt(i) == ',') {
						// First entry, Abbreviation
						if (x == 1) {
							abbr = tempLine.substring(0, i);
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Second entry, Location
						} else if (x == 2) {
							loc = tempLine.substring(0, i).replace('|', ',');
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
							// Third entry, starting date
						} else if (x == 3) {
							initial = new GregorianCalendar(
									Integer.parseInt(tempLine.substring(0, 4)),
									Integer.parseInt(tempLine.substring(4, 6)) - 1,
									Integer.parseInt(tempLine.substring(6, 8)));
							tempLine = tempLine.substring(i + 1);
							i = 0;
							x++;
						}
						// Fourth entry, ending date
					} else if (x == 4) {
						end = new GregorianCalendar(Integer.parseInt(tempLine
								.substring(0, 4)), Integer.parseInt(tempLine
								.substring(4, 6)) - 1,
								Integer.parseInt(tempLine.substring(6, 8)));
						break;
					}
				}
				conferenceList.add(new Conference(abbr, loc, initial, end));
			}
			in.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.setBounds(100, 100, 350, 200);
			dialog.setLocationRelativeTo(null);
			dialog.txtrField
					.setText("Something went wrong while trying to read the file! Please double check CONFERENCE.txt and ensure that every line is formatted as \"abbreviation,place,YYYYMMDD,YYYYMMDD\"");
			dialog.setTitle("Error:File Corrupt");
			dialog.setVisible(true);
		}
	}

	// Writes the array into the flat text at the designated path
	public void write() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd");
			SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
			SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
			bw = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < conferenceList.size(); i++) {
				bw.write(conferenceList.get(i).getAbbreviation()

						+ ","
						+ conferenceList.get(i).getLocation().replace(',', '|')
						+ ","
						+ formatYear.format(conferenceList.get(i)
								.getInitialDate().getTime())
						+ formatMonth.format(conferenceList.get(i)
								.getInitialDate().getTime())
						+ formatDate.format(conferenceList.get(i)
								.getInitialDate().getTime())
						+ ","
						+ formatYear.format(conferenceList.get(i)
								.getFinalDate().getTime())
						+ formatMonth.format(conferenceList.get(i)
								.getFinalDate().getTime())
						+ formatDate.format(conferenceList.get(i)
								.getFinalDate().getTime()));
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Deletes an arraylist entry at index
	public void delete(int index) {
		conferenceList.remove(index);
	}

	// Changes a designated entry in the arraylist
	public void update(int index, String loc, String abbrv, Calendar day,
			Calendar day2) {

		conferenceList.get(index).updateLocation(loc);

		conferenceList.get(index).updateDates(day, day2);

		conferenceList.get(index).updateAbbreviation(abbrv);
	}

	// Returns the type at a specified index
	public Conference get(int index) {
		return conferenceList.get(index);
	}

	// Returns the entire file as an array of objects
	public Conference[] getData() {
		return conferenceList.toArray(new Conference[conferenceList.size()]);
	}

	// Returns an array of indexes containing the specified query (Restricted)
	public Integer[] search(int column, String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < conferenceList.size(); i++) {
			if (column == 1
					&& conferenceList.get(i).getAbbreviation().contains(query)) {
				result.add(i);
			} else if (column == 2
					&& conferenceList.get(i).getLocation().contains(query)) {
				result.add(i);
			} else if (column == 3
					&& (""
							+ conferenceList.get(i).getInitialDate()
									.get(Calendar.YEAR)
							+ conferenceList.get(i).getInitialDate()
									.get(Calendar.MONTH) + conferenceList
							.get(i).getInitialDate().get(Calendar.DATE))
							.contains(query)) {
				result.add(i);
			} else if (column == 4
					&& (""
							+ conferenceList.get(i).getFinalDate()
									.get(Calendar.YEAR)
							+ conferenceList.get(i).getFinalDate()
									.get(Calendar.MONTH) + conferenceList
							.get(i).getFinalDate().get(Calendar.DATE))
							.contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}

	// Returns an array of indexes containing the specified query
	public Integer[] search(String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < conferenceList.size(); i++) {
			if (conferenceList.get(i).getAbbreviation().contains(query)) {
				result.add(i);
			} else if (conferenceList.get(i).getLocation().contains(query)) {
				result.add(i);
			} else if ((""
					+ conferenceList.get(i).getInitialDate().get(Calendar.YEAR)
					+ conferenceList.get(i).getInitialDate()
							.get(Calendar.MONTH) + conferenceList.get(i)
					.getInitialDate().get(Calendar.DATE)).contains(query)) {
				result.add(i);
			} else if ((""
					+ conferenceList.get(i).getFinalDate().get(Calendar.YEAR)
					+ conferenceList.get(i).getFinalDate().get(Calendar.MONTH) + conferenceList
					.get(i).getFinalDate().get(Calendar.DATE)).contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}
}
