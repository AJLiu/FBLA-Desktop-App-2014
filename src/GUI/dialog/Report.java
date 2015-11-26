package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import reports.ConferenceReport;
import reports.ParticipantReports;
import reports.WorkshopReport;
import textConnection.Conference;
import textConnection.ConferenceConnector;
import textConnection.ParticipantConnector;
import textConnection.WorkshopConnector;
import GUI.AdminFrame;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Report extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea txtrShowsAList;
	private JTabbedPane tabbedPane;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JCheckBox chckbxNewCheckBox;

	private String[] conferenceListInfo;

	private Conference[] conferenceArray;

	private ConferenceConnector conferenceC = new ConferenceConnector(
			"\\data\\CONFERENCES.txt");
	private ParticipantConnector participantC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");
	private WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");

	private void readConference() {
		// Conference List
		conferenceC.read();
		conferenceArray = conferenceC.getData();
		conferenceListInfo = new String[conferenceArray.length];
		for (int x = 0; x < conferenceArray.length; x++) {
			conferenceListInfo[x] = conferenceArray[x].getLocation();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Report dialog = new Report();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Report() {
		setTitle("Save a Report");
		readConference();
		setResizable(false);
		setBounds(100, 100, 570, 352);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);
			{
				JPanel conference = new JPanel();
				tabbedPane.addTab("Participants by Conference", null,
						conference, null);

				JTextArea txtrShowsAList_1 = new JTextArea();
				txtrShowsAList_1.setWrapStyleWord(true);
				txtrShowsAList_1
						.setText("Shows a list of all of the participants participating in a conference.\r\n\r\nThe list of participants can be sorted by \"type->last name\" or by \"chapter->type->last name\" with each chapter starting on a new page.");
				txtrShowsAList_1.setLineWrap(true);
				txtrShowsAList_1.setFont(new Font("SansSerif", Font.PLAIN, 13));
				txtrShowsAList_1.setEditable(false);

				JLabel lblConference = new JLabel("Conference: ");

				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(conferenceListInfo));

				chckbxNewCheckBox = new JCheckBox("");

				JLabel lblSortByChapter = new JLabel("Sort by chapter number");
				GroupLayout gl_conference = new GroupLayout(conference);
				gl_conference
						.setHorizontalGroup(gl_conference
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										gl_conference
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														gl_conference
																.createParallelGroup(
																		Alignment.LEADING)
																.addGroup(
																		gl_conference
																				.createSequentialGroup()
																				.addComponent(
																						lblConference)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(
																						comboBox,
																						GroupLayout.PREFERRED_SIZE,
																						138,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		gl_conference
																				.createSequentialGroup()
																				.addComponent(
																						lblSortByChapter)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(
																						chckbxNewCheckBox)))
												.addPreferredGap(
														ComponentPlacement.RELATED,
														68, Short.MAX_VALUE)
												.addComponent(
														txtrShowsAList_1,
														GroupLayout.PREFERRED_SIZE,
														256,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap()));
				gl_conference
						.setVerticalGroup(gl_conference
								.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_conference
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														gl_conference
																.createParallelGroup(
																		Alignment.BASELINE)
																.addComponent(
																		txtrShowsAList_1,
																		GroupLayout.PREFERRED_SIZE,
																		228,
																		GroupLayout.PREFERRED_SIZE)
																.addGroup(
																		gl_conference
																				.createSequentialGroup()
																				.addGroup(
																						gl_conference
																								.createParallelGroup(
																										Alignment.BASELINE)
																								.addComponent(
																										comboBox,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addComponent(
																										lblConference))
																				.addGroup(
																						gl_conference
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addGroup(
																										gl_conference
																												.createSequentialGroup()
																												.addGap(16)
																												.addComponent(
																														lblSortByChapter))
																								.addGroup(
																										gl_conference
																												.createSequentialGroup()
																												.addGap(13)
																												.addComponent(
																														chckbxNewCheckBox)))))
												.addContainerGap(
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)));
				conference.setLayout(gl_conference);
			}
			{
				JPanel workshop = new JPanel();
				tabbedPane.addTab("Participants by Workshop", null, workshop,
						null);

				JTextArea txtrShowsAList_2 = new JTextArea();
				txtrShowsAList_2.setWrapStyleWord(true);
				txtrShowsAList_2
						.setText("Shows a list of all workshops in a conference with each workshops in a separate page. Each page also contains all of the participants participating in the workshop.\r\n\r\nWorkshops are sorted by their names while the participants by their last names.");
				txtrShowsAList_2.setLineWrap(true);
				txtrShowsAList_2.setFont(new Font("SansSerif", Font.PLAIN, 13));
				txtrShowsAList_2.setEditable(false);

				JLabel lblConference_1 = new JLabel("Conference: ");

				comboBox1 = new JComboBox();
				comboBox1
						.setModel(new DefaultComboBoxModel(conferenceListInfo));
				GroupLayout gl_workshop = new GroupLayout(workshop);
				gl_workshop.setHorizontalGroup(gl_workshop.createParallelGroup(
						Alignment.LEADING).addGroup(
						Alignment.TRAILING,
						gl_workshop
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblConference_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox1,
										GroupLayout.PREFERRED_SIZE, 138,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										75, Short.MAX_VALUE)
								.addComponent(txtrShowsAList_2,
										GroupLayout.PREFERRED_SIZE, 256,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
				gl_workshop
						.setVerticalGroup(gl_workshop
								.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_workshop
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														gl_workshop
																.createParallelGroup(
																		Alignment.LEADING)
																.addGroup(
																		gl_workshop
																				.createParallelGroup(
																						Alignment.BASELINE)
																				.addComponent(
																						lblConference_1)
																				.addComponent(
																						comboBox1,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addComponent(
																		txtrShowsAList_2,
																		GroupLayout.PREFERRED_SIZE,
																		228,
																		GroupLayout.PREFERRED_SIZE))
												.addContainerGap(
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)));
				workshop.setLayout(gl_workshop);
			}
			{
				JPanel schedules = new JPanel();
				tabbedPane.addTab("Participant Schedules", null, schedules,
						null);
				{
					txtrShowsAList = new JTextArea();
					txtrShowsAList
							.setText("Shows a list of all participants and their preregestered workshops with each participant on a separate page.\r\n\r\nDisplays the Workshops in the form of an agenda for the Participants.");
					txtrShowsAList.setWrapStyleWord(true);
					txtrShowsAList.setLineWrap(true);
					txtrShowsAList.setEditable(false);
					txtrShowsAList
							.setFont(new Font("SansSerif", Font.PLAIN, 13));
				}
				GroupLayout gl_schedules = new GroupLayout(schedules);
				gl_schedules
						.setHorizontalGroup(gl_schedules
								.createParallelGroup(Alignment.LEADING)
								.addGroup(
										Alignment.TRAILING,
										gl_schedules
												.createSequentialGroup()
												.addContainerGap(283,
														Short.MAX_VALUE)
												.addComponent(
														txtrShowsAList,
														GroupLayout.PREFERRED_SIZE,
														256,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap()));
				gl_schedules.setVerticalGroup(gl_schedules.createParallelGroup(
						Alignment.LEADING).addGroup(
						gl_schedules
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(txtrShowsAList,
										GroupLayout.DEFAULT_SIZE, 228,
										Short.MAX_VALUE).addContainerGap()));
				schedules.setLayout(gl_schedules);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save Report");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						participantC.read();
						workshopC.read();
						if (tabbedPane.getSelectedIndex() == 0) {
							if (!chckbxNewCheckBox.isSelected()) {
								ConferenceReport confReport = new ConferenceReport(
										"\\reports\\participantsReportbyConference.pdf",
										participantC.getData(),
										conferenceArray[comboBox
												.getSelectedIndex()]);
								confReport.write();
								confReport.open();
							} else {
								ConferenceReport confReport = new ConferenceReport(
										"\\reports\\participantsReportbyConference.pdf",
										participantC.getData(),
										conferenceArray[comboBox
												.getSelectedIndex()]);
								confReport.writeByChapter();
								confReport.open();
							}
						} else if (tabbedPane.getSelectedIndex() == 1) {
							WorkshopReport wkshpReport = new WorkshopReport(
									"\\reports\\participantsReportbyWorkshop.pdf",
									workshopC.getData(),
									conferenceArray[comboBox1
											.getSelectedIndex()]);
							wkshpReport.write();
							wkshpReport.open();

						} else if (tabbedPane.getSelectedIndex() == 2) {
							ParticipantReports partReport = new ParticipantReports(
									"\\reports\\participantsList.pdf",
									participantC.getData());
							partReport.write();
							partReport.open();
						}
					}
				});
				okButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
				cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
