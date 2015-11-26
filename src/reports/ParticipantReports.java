/**
 * 
 */
package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

import textConnection.Participant;
import textConnection.Workshop;
import textConnection.WorkshopRegistrationConnector;
import GUI.dialog.Dialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class ParticipantReports {

	private String path;
	private Document doc;

	private Participant[] participants;
	private String firstName;
	private String lastName;
	private String chapter;
	private String type;
	private String conference;
	private String id;
	private String startingDate;
	private String endingDate;
	private Workshop[] workshopsTable;

	private WorkshopRegistrationConnector workshopRegC = new WorkshopRegistrationConnector(
			"\\data\\WKSHP_REGISTRATIONS.txt");

	/**
	 * @param args
	 */
	public ParticipantReports(String p, Participant[] part) {
		path = System.getProperty("user.dir") + p;
		participants = part;
		workshopRegC.read();
	}

	public void write() {
		doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path));

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getFirstName().toLowerCase()
							.compareTo(p2.getFirstName().toLowerCase());
				}
			});

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getLastName().toLowerCase()
							.compareTo(p2.getLastName().toLowerCase());
				}
			});

			doc.open();
			Paragraph preface = new Paragraph(
					"List of all participants schedules sorted by last names",
					new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD));
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingAfter(10f);
			doc.add(preface);
			doc.newPage();
			for (Participant part : participants) {
				setData(part);
				Paragraph paragraph = new Paragraph();
				paragraph.add(new Phrase("ID: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(id, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nLast Name: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(lastName, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nFirst Name: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(firstName, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nChapter: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(chapter, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nType: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(type, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nConference: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(conference, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nStarting Date: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(startingDate, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				paragraph.add(new Phrase("\nEnding Date: ", new Font(
						Font.FontFamily.TIMES_ROMAN, 14)));
				paragraph.add(new Phrase(endingDate, new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

				PdfPTable table = new PdfPTable(3);
				table.setSpacingBefore(20f);
				PdfPCell cell1 = new PdfPCell(new Paragraph("Workshop",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				PdfPCell cell2 = new PdfPCell(new Paragraph("Starting Date",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph("Starting Time",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);

				Arrays.sort(workshopsTable, new Comparator<Workshop>() {
					@Override
					public int compare(Workshop p1, Workshop p2) {
						return p1.getDate().compareTo(p2.getDate());
					}
				});

				SimpleDateFormat formatter1 = new SimpleDateFormat(
						"MMMM dd, yyyy");
				SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm a");
				for (Workshop element : workshopsTable) {
					table.addCell(new PdfPCell(new Paragraph(element.getName(),
							new Font(Font.FontFamily.TIMES_ROMAN, 13))));
					table.addCell(new PdfPCell(new Paragraph(formatter1
							.format(element.getDate().getTime()), new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
					table.addCell(new PdfPCell(new Paragraph(formatter2
							.format(element.getDate().getTime()), new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
				}

				doc.add(paragraph);
				doc.add(table);
				doc.newPage();
			}
			doc.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.txtrField
					.setText("The file is currently in use by another application. Please close it and try again.");
			dialog.setTitle("Error:File not found");
			dialog.setVisible(true);
		}
	}

	private void setData(Participant part) {
		firstName = part.getFirstName();
		lastName = part.getLastName();
		chapter = "" + part.getChapter();
		type = part.getType().getDescription().split("-")[0];
		conference = part.getConference().getLocation();
		id = "" + part.getID();
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMMM dd, yyyy");
		startingDate = formatter1.format(part.getConference().getInitialDate()
				.getTime());
		endingDate = formatter1.format(part.getConference().getFinalDate()
				.getTime());

		workshopsTable = workshopRegC.getWorkshops(part);
	}

	public void open() {
		try {
			File myFile = new File(path);
			Desktop.getDesktop().open(myFile);
		} catch (IOException ex) {
			Dialog dialog = new Dialog();
			dialog.txtrField
					.setText("Your system does not have any software that can open PDF files. The report can still be found in the /reports/ folder.");
			dialog.setVisible(true);
		}
	}
}
