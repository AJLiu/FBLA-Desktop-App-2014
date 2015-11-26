/******************************************
 * TypeConnector.java
 *
 * Reads and Manipulates the type file
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

// Participant types
// members
// advisers
// guests

public class TypeConnector {

	// Data is stored in memory as an arraylist
	private ArrayList<Type> typeList = new ArrayList<Type>();
	// File Reading objects
	private String path;
	private BufferedReader in;
	private BufferedWriter bw;

	// Constructs the connection object
	public TypeConnector(String p) {
		path = System.getProperty("user.dir") + p;
		if (!new File(path).exists()) {
			try {
				File f = new File(path);
				f.createNewFile();
				bw = new BufferedWriter(new FileWriter(path));
				bw.write("M,Member-description");
				bw.newLine();
				bw.write("A,Advisor-description");
				bw.newLine();
				bw.write("G,Guest-description");
				bw.newLine();
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Truncates the array
	public void clear() {
		for (int i = 1, n = typeList.size(); i < n; i++) {
			delete(1);
		}
	}

	// Goes line by line and stores information into an arraylist of objects
	public void read() {
		try {
			typeList.clear();
			in = new BufferedReader(new FileReader(path));
			while (in.ready()) {
				// Temp Data
				String tempLine = in.readLine();
				String abbr = "";
				String desc = "";

				// Follows assumption that data is stored as
				// abbreviation,description
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
						}
						// Last entry, Description
					} else if (x == 2) {
						desc = tempLine;
						break;
					}
				}
				typeList.add(new Type(abbr, desc));
			}
			in.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.setBounds(100, 100, 350, 200);
			dialog.setLocationRelativeTo(null);
			dialog.txtrField
					.setText("Something went wrong while trying to read the file! Please double check TYPE.txt and ensure that every line is formatted as \"abbreviation,Full Name-description\"");
			dialog.setTitle("Error:File Corrupt");
			dialog.setVisible(true);
		}
	}

	// Writes the array into the flat text at the designated path
	public void write() {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < typeList.size(); i++) {
				bw.write(typeList.get(i).getAbbreviation() + ","
						+ typeList.get(i).getDescription());
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Deletes an arraylist entry at index
	public void delete(int index) {
		typeList.remove(index);
	}

	// Changes a designated entry in the arraylist
	public void update(int index, String str) {
		typeList.get(index).updateDescription(str);
	}

	// Returns the type at a specified index
	public Type get(int index) {
		return typeList.get(index);
	}

	// Returns the entire file as an array of objects
	public Type[] getData() {
		return typeList.toArray(new Type[typeList.size()]);
	}

	// Returns an array of indexes containing the specified query
	public Integer[] search(String query) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < typeList.size(); i++) {
			if (typeList.get(i).getAbbreviation().contains(query)) {
				result.add(i);
			}
		}
		return result.toArray(new Integer[result.size()]);
	}
}
