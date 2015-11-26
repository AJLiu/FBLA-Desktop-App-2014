/******************************************
 * Type.java
 *
 * Stores Type Object data
 ******************************************/

package textConnection;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Type {
	// Initial variable declarations
	private String type_Abbreviation;
	private String type_Description;

	// Constructors
	public Type() {
		type_Abbreviation = "N/A";
		type_Description = "N/A";
	}

	public Type(String abbr, String desc) {
		type_Abbreviation = abbr;
		type_Description = desc;
	}

	// Update Methods
	// manages and changes object variables
	public void updateAbbreviation(String abbr) {
		type_Abbreviation = abbr;
	}

	public void updateDescription(String desc) {
		type_Description = desc;
	}

	// Return Methods
	// returns parts of the object
	public String getAbbreviation() {
		return type_Abbreviation;
	}

	public String getDescription() {
		return type_Description;
	}

	@Override
	public String toString() {
		return type_Abbreviation + ":" + type_Description;
	}
}