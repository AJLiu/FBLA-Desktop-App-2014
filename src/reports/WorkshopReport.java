/**
 * 
 */
package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import textConnection.Conference;
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

public class WorkshopReport {

	private String path;
	private Document doc;

	private Participant[] participants;
	private Workshop[] workshops;

	private String conferenceName;
	private String conferenceLocation;
	private String initialDate;
	private String finalDate;

	private WorkshopRegistrationConnector workshopRegC = new WorkshopRegistrationConnector(
			"\\data\\WKSHP_REGISTRATIONS.txt");

	/**
	 * @param args
	 */
	public WorkshopReport(String p, Workshop[] work, Conference conf) {
		path = System.getProperty("user.dir") + p;
		ArrayList<Workshop> temp = new ArrayList<Workshop>();
		for (Workshop x : work) {
			if (x.getConference().getAbbreviation()
					.equals(conf.getAbbreviation())) {
				temp.add(x);
			}
		}
		workshops = temp.toArray(new Workshop[temp.size()]);

		conferenceName = conf.getAbbreviation();
		conferenceLocation = conf.getLocation();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		initialDate = formatter.format(conf.getInitialDate().getTime());
		finalDate = formatter.format(conf.getFinalDate().getTime());
	}

	public void write() {
		doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path));

			Arrays.sort(workshops, new Comparator<Workshop>() {
				@Override
				public int compare(Workshop p1, Workshop p2) {
					return p1.getName().toLowerCase()
							.compareTo(p2.getName().toLowerCase());
				}
			});

			doc.open();
			Paragraph preface = new Paragraph("List of all participants at "
					+ conferenceLocation + " by workshop", new Font(
					Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD));
			preface.setAlignment(Element.ALIGN_CENTER);
			preface.setSpacingAfter(10f);
			doc.add(preface);

			Paragraph preface2 = new Paragraph();
			preface2.add(new Phrase("\nAbbreviation: ", new Font(
					Font.FontFamily.TIMES_ROMAN, 14)));
			preface2.add(new Phrase(conferenceName, new Font(
					Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

			preface2.add(new Phrase("\nLocation: ", new Font(
					Font.FontFamily.TIMES_ROMAN, 14)));
			preface2.add(new Phrase(conferenceLocation, new Font(
					Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

			preface2.add(new Phrase("\nStarting Date: ", new Font(
					Font.FontFamily.TIMES_ROMAN, 14)));
			preface2.add(new Phrase(initialDate, new Font(
					Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));

			preface2.add(new Phrase("\nEnding Date: ", new Font(
					Font.FontFamily.TIMES_ROMAN, 14)));
			preface2.add(new Phrase(finalDate, new Font(
					Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
			doc.add(preface2);
			doc.newPage();
			workshopRegC.read();

			for (Workshop x : workshops) {
				Paragraph title = new Paragraph(x.getName(), new Font(
						Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
				title.setAlignment(Element.ALIGN_CENTER);
				doc.add(title);

				Paragraph workshopInfo = new Paragraph();
				workshopInfo.add(new Phrase("Workshop Description: "
						+ x.getDescription()));
				SimpleDateFormat formatter = new SimpleDateFormat(
						"MMMM dd, yyyy 'at' hh:mm a");
				workshopInfo.add(new Phrase("\nWorkshop Time: "
						+ formatter.format(x.getDate().getTime())));

				doc.add(workshopInfo);

				PdfPTable table = new PdfPTable(4);
				table.setSpacingBefore(30f);
				PdfPCell cell1 = new PdfPCell(new Paragraph("Type", new Font(
						Font.FontFamily.TIMES_ROMAN, 13)));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				PdfPCell cell2 = new PdfPCell(new Paragraph("Last Name",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph("First Name",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
				PdfPCell cell4 = new PdfPCell(new Paragraph("Chapter",
						new Font(Font.FontFamily.TIMES_ROMAN, 13)));
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell4);

				participants = workshopRegC.get(x);

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

				for (Participant element : participants) {
					table.addCell(new PdfPCell(new Paragraph(element.getType()
							.getDescription().split("-")[0], new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
					table.addCell(new PdfPCell(new Paragraph(element
							.getLastName(), new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
					table.addCell(new PdfPCell(new Paragraph(element
							.getFirstName(), new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
					table.addCell(new PdfPCell(new Paragraph(""
							+ element.getChapter(), new Font(
							Font.FontFamily.TIMES_ROMAN, 13))));
				}

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

	public void open() {
		try {
			File myFile = new File(path);
			Desktop.getDesktop().open(myFile);
		} catch (Exception ex) {
			Dialog dialog = new Dialog();
			dialog.txtrField
					.setText("Your system does not have any software that can open PDF files. The report can still be found in the /reports/ folder.");
			dialog.setVisible(true);
		}
	}
}