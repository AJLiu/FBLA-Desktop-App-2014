package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import textConnection.Participant;
import textConnection.ParticipantConnector;
import textConnection.Workshop;
import textConnection.WorkshopConnector;
import textConnection.WorkshopRegistrationConnector;
import GUI.AdminFrame;
import GUI.AdminPanel;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class MultiDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable deleteWorkshops;
	private JTable deleteParticipants;
	private JTabbedPane tabbedPane;

	private String[][] participantInfo; // Contains the information to be
										// displayed in the table
	private String[][] workshopTableInfo; // Contains the information to be
											// displayed in the table
	private Workshop[] workshopArray; // Contains all of the workshops
	private Participant[] participantArray; // Contains all of the participants

	private WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");
	private ParticipantConnector participantsC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");
	private WorkshopRegistrationConnector workshopRegC = new WorkshopRegistrationConnector(
			"\\data\\WKSHP_REGISTRATIONS.txt");

	private SimpleDateFormat formatter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MultiDelete dialog = new MultiDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the workshop data file and instantiates the workshop arrays
	 */
	private void readWorkshop() {
		// Workshops List
		workshopC.read();
		workshopArray = workshopC.getData();
		// Workshops Table
		workshopTableInfo = new String[workshopArray.length][4];
		for (int i = 0; i < workshopArray.length; i++) {
			workshopTableInfo[i][0] = workshopArray[i].getConference()
					.getAbbreviation();
			workshopTableInfo[i][1] = workshopArray[i].getName();
			formatter = new SimpleDateFormat("dd-MMM-yyyy");
			workshopTableInfo[i][2] = formatter.format(workshopArray[i]
					.getDate().getTime());
			formatter = new SimpleDateFormat("hh:mm a");
			workshopTableInfo[i][3] = formatter.format(workshopArray[i]
					.getDate().getTime());
		}
	}

	/**
	 * Reads the participant data file and instantiates the participant arrays
	 */
	private void readParticipants() {
		// Participants Table
		participantsC.read();
		participantArray = participantsC.getData();
		participantInfo = new String[participantArray.length][5];
		for (int i = 0; i < participantArray.length; i++) {
			participantInfo[i][0] = participantArray[i].getConference()
					.getAbbreviation();
			participantInfo[i][1] = participantArray[i].getType()
					.getAbbreviation();
			participantInfo[i][2] = participantArray[i].getFirstName();
			participantInfo[i][3] = participantArray[i].getLastName();
			participantInfo[i][4] = "" + participantArray[i].getChapter();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MultiDelete() {
		setResizable(false);
		setTitle("Delete Multiple Items\r\n");
		readParticipants();
		readWorkshop();

		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				tabbedPane.addTab("Delete Workshops", null, scrollPane, null);
				{
					deleteWorkshops = new JTable();
					deleteWorkshops.setShowVerticalLines(false);
					deleteWorkshops.setModel(new DefaultTableModel(
							workshopTableInfo, new String[] { "Conference",
									"Name", "Date", "Time" }) {
						Class[] columnTypes = new Class[] { String.class,
								String.class, String.class, String.class };

						@Override
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}

						boolean[] columnEditables = new boolean[] { false,
								false, false, false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					scrollPane.setViewportView(deleteWorkshops);
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				tabbedPane
						.addTab("Delete Participants", null, scrollPane, null);
				{
					deleteParticipants = new JTable();
					deleteParticipants.setShowVerticalLines(false);
					deleteParticipants.setModel(new DefaultTableModel(
							participantInfo, new String[] { "Conference",
									"Type", "First Name", "Last Name",
									"Chapter" }) {
						Class[] columnTypes = new Class[] { String.class,
								String.class, String.class, String.class,
								String.class };

						@Override
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}

						boolean[] columnEditables = new boolean[] { false,
								false, false, false, false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					scrollPane.setViewportView(deleteParticipants);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Delete");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (tabbedPane.getSelectedIndex() == 0) {
							Confirm prompt = new Confirm();
							prompt.txtrAreYouSure
									.setText("Are you sure you wish to delete these workshops?");
							prompt.setVisible(true);
							if (prompt.isConfirmed()) {
								int[] x = deleteWorkshops.getSelectedRows();
								Integer[] z = new Integer[x.length];
								for (int i = 0; i < z.length; i++) {
									z[i] = Integer.valueOf(x[i]);
								}
								Arrays.sort(z, Collections.reverseOrder());
								for (int i : z) {
									workshopRegC.delete(workshopC.delete(i));
								}
								workshopC.write();
								workshopRegC.write();
								readWorkshop();
								AdminPanel.readWorkshop();

								AdminPanel.table
										.setModel(new DefaultTableModel(
												workshopTableInfo,
												new String[] { "Conference",
														"Name", "Date", "Time" }) {
											boolean[] columnEditables = new boolean[] {
													false, false, false, false };

											@Override
											public boolean isCellEditable(
													int row, int column) {
												return columnEditables[column];
											}
										});
								deleteWorkshops.setModel(new DefaultTableModel(
										workshopTableInfo, new String[] {
												"Conference", "Name", "Date",
												"Time" }) {
									Class[] columnTypes = new Class[] {
											String.class, String.class,
											String.class, String.class };

									@Override
									public Class getColumnClass(int columnIndex) {
										return columnTypes[columnIndex];
									}

									boolean[] columnEditables = new boolean[] {
											false, false, false, false };

									@Override
									public boolean isCellEditable(int row,
											int column) {
										return columnEditables[column];
									}
								});
							}
						} else {
							Confirm prompt = new Confirm();
							prompt.txtrAreYouSure
									.setText("Are you sure you wish to delete these participants?");
							prompt.setVisible(true);
							if (prompt.isConfirmed()) {
								int[] x = deleteParticipants.getSelectedRows();
								Integer[] z = new Integer[x.length];
								for (int i = 0; i < z.length; i++) {
									z[i] = Integer.valueOf(x[i]);
								}
								Arrays.sort(z, Collections.reverseOrder());
								for (int i : z) {
									participantsC.delete(i);
									workshopRegC
											.removeParticipant(participantArray[i]);
								}
								participantsC.write();
								workshopRegC.write();
								readParticipants();
								readWorkshop();
								AdminPanel.readParticipants();
								AdminPanel.readWorkshop();

								AdminPanel.table_2
										.setModel(new DefaultTableModel(
												participantInfo,
												new String[] { "Conference",
														"Type", "First Name",
														"Last Name", "Chapter" }) {
											Class[] columnTypes = new Class[] {
													String.class, String.class,
													String.class, String.class,
													String.class };

											boolean[] columnEditables = new boolean[] {
													false, false, false, false,
													false };

											@Override
											public boolean isCellEditable(
													int row, int column) {
												return columnEditables[column];
											}

											@Override
											public Class getColumnClass(
													int columnIndex) {
												return columnTypes[columnIndex];
											}
										});
								deleteParticipants
										.setModel(new DefaultTableModel(
												participantInfo,
												new String[] { "Conference",
														"Type", "First Name",
														"Last Name", "Chapter" }) {
											Class[] columnTypes = new Class[] {
													String.class, String.class,
													String.class, String.class,
													String.class };

											@Override
											public Class getColumnClass(
													int columnIndex) {
												return columnTypes[columnIndex];
											}

											boolean[] columnEditables = new boolean[] {
													false, false, false, false,
													false };

											@Override
											public boolean isCellEditable(
													int row, int column) {
												return columnEditables[column];
											}
										});
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
