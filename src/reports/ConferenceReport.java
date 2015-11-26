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

public class ConferenceReport {

	private String path;
	private Document doc;

	private Participant[] participants;
	private String conferenceName;
	private String conferenceLocation;
	private String initialDate;
	private String finalDate;

	/**
	 * @param args
	 */
	public ConferenceReport(String p, Participant[] part, Conference conf) {
		path = System.getProperty("user.dir") + p;
		ArrayList<Participant> temp = new ArrayList<Participant>();
		for (Participant x : part) {
			if (x.getConference().getAbbreviation()
					.equals(conf.getAbbreviation())) {
				temp.add(x);
			}
		}

		participants = temp.toArray(new Participant[temp.size()]);

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

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getType().getAbbreviation()
							.compareTo(p2.getType().getAbbreviation());
				}
			});

			doc.open();
			Paragraph preface = new Paragraph("List of all participants at "
					+ conferenceLocation, new Font(Font.FontFamily.TIMES_ROMAN,
					32, Font.BOLD));
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

			PdfPTable table = new PdfPTable(4);
			table.setSpacingBefore(30f);
			PdfPCell cell1 = new PdfPCell(new Paragraph("Type", new Font(
					Font.FontFamily.TIMES_ROMAN, 13)));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Paragraph("Last Name", new Font(
					Font.FontFamily.TIMES_ROMAN, 13)));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);
			PdfPCell cell3 = new PdfPCell(new Paragraph("First Name", new Font(
					Font.FontFamily.TIMES_ROMAN, 13)));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Paragraph("Chapter", new Font(
					Font.FontFamily.TIMES_ROMAN, 13)));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);

			for (Participant element : participants) {
				table.addCell(new PdfPCell(new Paragraph(element.getType()
						.getDescription().split("-")[0], new Font(
						Font.FontFamily.TIMES_ROMAN, 13))));
				table.addCell(new PdfPCell(new Paragraph(element.getLastName(),
						new Font(Font.FontFamily.TIMES_ROMAN, 13))));
				table.addCell(new PdfPCell(new Paragraph(
						element.getFirstName(), new Font(
								Font.FontFamily.TIMES_ROMAN, 13))));
				table.addCell(new PdfPCell(new Paragraph(""
						+ element.getChapter(), new Font(
						Font.FontFamily.TIMES_ROMAN, 13))));
			}

			doc.add(table);
			doc.close();
		} catch (Exception e) {
			Dialog dialog = new Dialog();
			dialog.txtrField
					.setText("The file is currently in use by another application. Please close it and try again.");
			dialog.setTitle("Error:File not found");
			dialog.setVisible(true);
		}
	}

	public void writeByChapter() {
		doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path));

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getFirstName().compareTo(p2.getFirstName());
				}
			});

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getLastName().compareTo(p2.getLastName());
				}
			});

			Arrays.sort(participants, new Comparator<Participant>() {
				@Override
				public int compare(Participant p1, Participant p2) {
					return p1.getType().getAbbreviation()
							.compareTo(p2.getType().getAbbreviation());
				}
			});

			ArrayList<Integer> temp = new ArrayList<Integer>();
			if (participants.length > 0) {
				temp.add(participants[0].getChapter());
			}
			for (Participant x : participants) {
				boolean bool = false;
				for (Integer z : temp) {
					if (x.getChapter() == z) {
						bool = true;
					}
				}
				if (bool == false) {
					temp.add(x.getChapter());
				}
			}
			Integer[] chapters = temp.toArray(new Integer[temp.size()]);

			Arrays.sort(chapters);

			doc.open();
			Paragraph preface = new Paragraph("List of all participants at "
					+ conferenceLocation + " by chapter", new Font(
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

			for (Integer x : chapters) {
				Paragraph heading = new Paragraph("Chapter: " + x, new Font(
						Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD));
				heading.setAlignment(Element.ALIGN_CENTER);
				doc.add(heading);

				PdfPTable table = new PdfPTable(3);
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

				for (Participant element : participants) {
					if (element.getChapter() == x) {
						table.addCell(new PdfPCell(new Paragraph(element
								.getType().getDescription().split("-")[0],
								new Font(Font.FontFamily.TIMES_ROMAN, 13))));
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
