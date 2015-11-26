/******************************************
 * Conference.java
 *
 * Stores Conference Object data
 ******************************************/

package textConnection;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Conference {

	private String conference_Abbreviation;
	private String conference_Location;
	private Calendar conference_initialDate = new GregorianCalendar();
	private Calendar conference_finalDate = new GregorianCalendar();

	/**
	 * Constructors
	 */
	public Conference() {
		conference_Abbreviation = "N/A";
		conference_Location = "N/A";
		conference_initialDate = new GregorianCalendar();
		conference_finalDate = new GregorianCalendar();
	}

	public Conference(String abbr, String loc, Calendar bDate, Calendar eDate) {
		conference_Abbreviation = abbr;
		conference_Location = loc;
		conference_initialDate = bDate;
		conference_finalDate = eDate;
	}

	/**
	 * Modifier Methods
	 */
	public void updateAbbreviation(String abbr) {
		conference_Abbreviation = abbr;
	}

	public void updateLocation(String loc) {
		conference_Location = loc;
	}

	public void updateDates(Calendar bDate, Calendar eDate) {
		conference_initialDate = bDate;
		conference_finalDate = eDate;
	}

	/**
	 * Return Methods
	 */
	public String getAbbreviation() {
		return conference_Abbreviation;
	}

	public String getLocation() {
		return conference_Location;
	}

	public Calendar getInitialDate() {
		return conference_initialDate;
	}

	public Calendar getFinalDate() {
		return conference_finalDate;
	}

	@Override
	public String toString() {
		return conference_Location + "\nStart:"
				+ conference_initialDate.get(Calendar.MONTH) + "/"
				+ conference_initialDate.get(Calendar.DATE) + "/"
				+ conference_initialDate.get(Calendar.YEAR) + "\nEnd:"
				+ conference_finalDate.get(Calendar.MONTH) + "/"
				+ conference_finalDate.get(Calendar.DATE) + "/"
				+ conference_finalDate.get(Calendar.YEAR);
	}
}
