package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import reports.UserReport;
import textConnection.Conference;
import textConnection.ConferenceConnector;
import textConnection.Participant;
import textConnection.ParticipantConnector;
import textConnection.Type;
import textConnection.TypeConnector;
import textConnection.Workshop;
import textConnection.WorkshopConnector;
import textConnection.WorkshopRegistrationConnector;
import GUI.dialog.Confirm;
import GUI.dialog.Dialog;
import GUI.dialog.IncompleteForm;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class UserPanel extends JPanel {
	private final char[] ILLEGAL_CHARACTERS = { '|' };

	private CardLayout cl;
	private JPanel panel;
	private JTextField txtFldFirstName;
	private JTextField txtFldLastName;
	private JFormattedTextField txtFldChapterNumber;
	private JList TypeList;
	private JList ConferenceList;
	private JList WorkshopsList;
	private JTextArea TypeDescription;
	private JTextArea ConferenceDescription;
	private JTextArea WorkshopsDescription;

	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblConference;
	private JLabel lblChapterNumber;
	private JLabel lblType;

	private TypeConnector typeC = new TypeConnector("\\data\\TYPE.txt");
	private ConferenceConnector conferenceC = new ConferenceConnector(
			"\\data\\CONFERENCES.txt");
	private WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");
	private ParticipantConnector participantsC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");
	private WorkshopRegistrationConnector workshopRegC = new WorkshopRegistrationConnector(
			"\\data\\WKSHP_REGISTRATIONS.txt");

	private String[][] typeListInfo;
	private String[][] conferenceListInfo;
	private ArrayList<String>[][] workshopListInfo;
	private String[][] participantInfo;
	private String[][] searchedParticipantInfo;
	private ArrayList<String[]> searchedParticipant;

	private Conference[] conferenceArray;
	private Type[] typeArray;
	private Participant[] participantArray;
	private Workshop[] workshopArray;
	private Workshop[] workshopList;

	private SimpleDateFormat formatter;
	private NumberFormat f;

	private JPanel schedule;
	private JLabel lblFirstName_1;
	private JTextField FirstNameField;
	private JLabel lblLastName_1;
	private JTextField LastNameField;
	private JLabel lblChapterNumber_1;
	private JFormattedTextField ChapterNumberField;
	private JButton btnRetrieveSchedule;
	private JButton btnCancel_1;
	private JTable table;

	private Participant retrieve;
	private JLabel lblFirstName_2;
	private JLabel FirstNameReport;
	private JLabel lblLastName_2;
	private JLabel LastNameReport;
	private JLabel lblChapterNumber_2;
	private JLabel ChapterReport;
	private JLabel lblParticipantType;
	private JLabel TypeReport;
	private JLabel lblConferenceAttending;
	private JLabel lblNewLabel_3;
	private JLabel ConferenceReport;
	private JTable table_1;
	private JLabel IDReport;
	private JLabel StartingDateReport;
	private JLabel EndingDateReport;

	/**
	 * Reads the necessary information to be desplayed
	 */

	private void readConference() {
		// Conference List
		conferenceC.read();
		conferenceArray = conferenceC.getData();
		formatter = new SimpleDateFormat("dd-MMM-yyyy");
		conferenceListInfo = new String[4][conferenceArray.length];
		for (int x = 0; x < conferenceArray.length; x++) {
			conferenceListInfo[0][x] = conferenceArray[x].getAbbreviation();
			conferenceListInfo[1][x] = formatter.format(conferenceArray[x]
					.getInitialDate().getTime());
			conferenceListInfo[2][x] = formatter.format(conferenceArray[x]
					.getFinalDate().getTime());
			conferenceListInfo[3][x] = conferenceArray[x].getLocation();
		}
	}

	private void readType() {
		// Type Information
		typeC.read();
		typeArray = typeC.getData();
		typeListInfo = new String[3][typeArray.length];
		for (int i = 0; i < typeArray.length; i++) {
			for (int x = 0; x < typeArray[i].getDescription().length(); x++) {
				if (typeArray[i].getDescription().charAt(x) == '-') {
					typeListInfo[0][i] = typeArray[i].getDescription()
							.substring(0, x);
					typeListInfo[1][i] = typeArray[i].getDescription()
							.substring(x + 1);
					typeListInfo[2][i] = typeArray[i].getAbbreviation();
					break;
				}
			}
		}
	}

	private void readWorkshop() {
		// Workshops List
		workshopC.read();
		workshopArray = workshopC.getData();
		formatter = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm a");
		workshopListInfo = new ArrayList[conferenceListInfo[0].length][4];
		for (int i = 0; i < conferenceListInfo[0].length; i++) {
			Workshop[] tempWorkshopArray = workshopC.get(workshopC.search(2,
					conferenceListInfo[0][i]));
			workshopListInfo[i][0] = new ArrayList<String>();
			workshopListInfo[i][1] = new ArrayList<String>();
			workshopListInfo[i][2] = new ArrayList<String>();
			workshopListInfo[i][3] = new ArrayList<String>();
			for (Workshop x : tempWorkshopArray) {
				workshopListInfo[i][0].add(x.getConference().getAbbreviation());
				workshopListInfo[i][1].add(x.getName());
				workshopListInfo[i][2].add(x.getDescription());
				workshopListInfo[i][3].add(formatter.format(x.getDate()
						.getTime()));
			}
		}
	}

	private void readParticipants() {
		// Participants Table
		participantsC.read();
		participantArray = participantsC.getData();
		participantInfo = new String[participantArray.length][6];
		for (int i = 0; i < participantArray.length; i++) {
			participantInfo[i][0] = participantArray[i].getConference()
					.getAbbreviation();
			participantInfo[i][1] = participantArray[i].getType()
					.getAbbreviation();
			participantInfo[i][2] = participantArray[i].getFirstName();
			participantInfo[i][3] = participantArray[i].getLastName();
			participantInfo[i][4] = "" + participantArray[i].getChapter();
			participantInfo[i][5] = "" + participantArray[i].getID();
		}
	}

	private void searchParticipants() {
		String firstName = FirstNameField.getText();
		String lastName = LastNameField.getText();
		String chapter = ChapterNumberField.getText();

		searchedParticipant = new ArrayList<String[]>();

		for (String[] partInfo : participantInfo) {
			searchedParticipant.add(partInfo);
		}

		if (!firstName.equals("")) {
			for (int i = 0; i < searchedParticipant.size(); i++) {
				if (!searchedParticipant.get(i)[2].toLowerCase().contains(
						firstName.toLowerCase())) {
					searchedParticipant.remove(i);
					i--;
				}
			}
		}
		if (!lastName.equals("")) {
			for (int i = 0; i < searchedParticipant.size(); i++) {
				if (!searchedParticipant.get(i)[3].toLowerCase().contains(
						lastName.toLowerCase())) {
					searchedParticipant.remove(i);
					i--;
				}
			}
		}
		if (!chapter.equals("")) {
			for (int i = 0; i < searchedParticipant.size(); i++) {
				if (!searchedParticipant.get(i)[4].toLowerCase().contains(
						chapter.toLowerCase())) {
					searchedParticipant.remove(i);
					i--;
				}
			}
		}
		searchedParticipantInfo = searchedParticipant
				.toArray(new String[searchedParticipant.size()][6]);

		table.setModel(new DefaultTableModel(searchedParticipantInfo,
				new String[] { "Conference", "Type", "First Name", "Last Name",
						"Chapter Number" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public UserPanel() {
		readConference();
		readType();
		readWorkshop();
		readParticipants();
		workshopRegC.read();

		cl = new CardLayout();

		panel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		add(panel);
		panel.setLayout(cl);

		JPanel menu = new JPanel();
		panel.add(menu, "1");

		JLabel lblRetrieveYourSchedule = new JLabel("Retrieve your Schedule");
		lblRetrieveYourSchedule.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchParticipants();
				cl.show(panel, "2");
			}
		});
		button.setIcon(new ImageIcon(UserPanel.class
				.getResource("/resources/FULL-accessories-text-editor.png")));

		JLabel lblRegesterANew = new JLabel("Register a New Participant");
		lblRegesterANew.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "3");
			}
		});
		button_1.setIcon(new ImageIcon(UserPanel.class
				.getResource("/resources/FULL-contact-new.png")));
		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(gl_menu
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_menu.createSequentialGroup()
								.addGap(195)
								.addGroup(
										gl_menu.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														lblRetrieveYourSchedule)
												.addGroup(
														gl_menu.createSequentialGroup()
																.addComponent(
																		button)
																.addPreferredGap(
																		ComponentPlacement.RELATED)))
								.addGroup(
										gl_menu.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_menu.createSequentialGroup()
																.addGap(183)
																.addComponent(
																		lblRegesterANew))
												.addGroup(
														gl_menu.createSequentialGroup()
																.addGap(193)
																.addComponent(
																		button_1)))
								.addContainerGap(194, Short.MAX_VALUE)));
		gl_menu.setVerticalGroup(gl_menu
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_menu.createSequentialGroup()
								.addGap(96)
								.addGroup(
										gl_menu.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblRetrieveYourSchedule)
												.addComponent(lblRegesterANew))
								.addGap(6)
								.addGroup(
										gl_menu.createParallelGroup(
												Alignment.LEADING)
												.addComponent(button_1)
												.addComponent(button))
								.addContainerGap(253, Short.MAX_VALUE)));
		menu.setLayout(gl_menu);

		JPanel participantFinder = new JPanel();
		panel.add(participantFinder, "2");

		lblFirstName_1 = new JLabel("First Name");

		FirstNameField = new JTextField();
		FirstNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				for (char element : ILLEGAL_CHARACTERS) {
					if (c == element) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						arg0.consume();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				searchParticipants();
			}
		});

		lblLastName_1 = new JLabel("Last Name");

		LastNameField = new JTextField();
		LastNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				for (char element : ILLEGAL_CHARACTERS) {
					if (c == element) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						arg0.consume();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				searchParticipants();
			}
		});

		lblChapterNumber_1 = new JLabel("Chapter Number");

		f = NumberFormat.getIntegerInstance();
		f.setMaximumIntegerDigits(5);
		f.setGroupingUsed(false);
		ChapterNumberField = new JFormattedTextField(f);
		ChapterNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(c <= 27 || (c >= 48 && c <= 57))) {
					java.awt.Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				searchParticipants();
			}
		});

		btnRetrieveSchedule = new JButton("Retrieve Schedule");
		btnRetrieveSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					retrieve = participantsC.get(participantsC.search(1,
							searchedParticipantInfo[table.getSelectedRow()][5])[0]);

					ChapterNumberField.setText("");
					FirstNameField.setText("");
					LastNameField.setText("");

					readParticipants();

					table.setModel(new DefaultTableModel(participantInfo,
							new String[] { "Conference", "Type", "First Name",
									"Last Name", "Chapter Number" }) {
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

					FirstNameReport.setText(retrieve.getFirstName());
					LastNameReport.setText(retrieve.getLastName());
					ChapterReport.setText("" + retrieve.getChapter());
					TypeReport.setText(retrieve.getType().getDescription()
							.split("-")[0]);
					ConferenceReport.setText(retrieve.getConference()
							.getLocation());
					IDReport.setText("" + retrieve.getID());
					formatter = new SimpleDateFormat("MMMM dd, yyyy");
					StartingDateReport.setText(formatter.format(retrieve
							.getConference().getInitialDate().getTime()));
					EndingDateReport.setText(formatter.format(retrieve
							.getConference().getFinalDate().getTime()));

					formatter = new SimpleDateFormat(
							"MMMM dd, yyyy 'at' hh:mm a");
					workshopList = workshopRegC.getWorkshops(retrieve);
					String[][] reportTable = new String[workshopList.length][2];
					for (int i = 0; i < reportTable.length; i++) {
						reportTable[i][0] = workshopList[i].getName();
						reportTable[i][1] = formatter.format(workshopList[i]
								.getDate().getTime());
					}

					table_1.setModel(new DefaultTableModel(reportTable,
							new String[] { "Name", "Starting Date" }) {
						boolean[] columnEditables = new boolean[] { false,
								false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

					cl.show(panel, "4");
				} else {
					Dialog dialog = new Dialog();
					dialog.setLocationRelativeTo(UserFrame.contentPane);
					dialog.setTitle("No Participant Selected");
					dialog.txtrField
							.setText("You did not select a participant.\nPlease select the participant whose schedule you wish to retrieve.");
					dialog.setVisible(true);

				}
			}
		});
		btnRetrieveSchedule.setFont(new Font("SansSerif", Font.PLAIN, 15));

		btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ChapterNumberField.setText("");
				FirstNameField.setText("");
				LastNameField.setText("");

				readParticipants();

				table.setModel(new DefaultTableModel(participantInfo,
						new String[] { "Conference", "Type", "First Name",
								"Last Name", "Chapter Number" }) {
					Class[] columnTypes = new Class[] { String.class,
							String.class, String.class, String.class,
							String.class };

					@Override
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false,
							false, false, false };

					@Override
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				cl.show(panel, "1");
			}
		});
		btnCancel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(130, 135, 144)));
		GroupLayout gl_participantFinder = new GroupLayout(participantFinder);
		gl_participantFinder
				.setHorizontalGroup(gl_participantFinder
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_participantFinder
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_participantFinder
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_2,
																GroupLayout.DEFAULT_SIZE,
																886,
																Short.MAX_VALUE)
														.addGroup(
																gl_participantFinder
																		.createSequentialGroup()
																		.addComponent(
																				btnRetrieveSchedule)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnCancel_1,
																				GroupLayout.PREFERRED_SIZE,
																				151,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_participantFinder
																		.createSequentialGroup()
																		.addComponent(
																				lblFirstName_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				FirstNameField,
																				GroupLayout.PREFERRED_SIZE,
																				144,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblLastName_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				LastNameField,
																				GroupLayout.PREFERRED_SIZE,
																				144,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblChapterNumber_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				ChapterNumberField,
																				GroupLayout.PREFERRED_SIZE,
																				74,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_participantFinder
				.setVerticalGroup(gl_participantFinder
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_participantFinder
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_participantFinder
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_participantFinder
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				FirstNameField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblLastName_1)
																		.addComponent(
																				LastNameField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblChapterNumber_1)
																		.addComponent(
																				ChapterNumberField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lblFirstName_1))
										.addGap(18)
										.addComponent(scrollPane_2,
												GroupLayout.DEFAULT_SIZE, 405,
												Short.MAX_VALUE)
										.addGap(18)
										.addGroup(
												gl_participantFinder
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnRetrieveSchedule)
														.addComponent(
																btnCancel_1,
																GroupLayout.PREFERRED_SIZE,
																29,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(participantInfo, new String[] {
				"Conference", "Type", "First Name", "Last Name",
				"Chapter Number" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table);
		participantFinder.setLayout(gl_participantFinder);

		JPanel registration = new JPanel();
		panel.add(registration, "3");

		lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 12));

		txtFldFirstName = new JFormattedTextField();
		txtFldFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				for (char element : ILLEGAL_CHARACTERS) {
					if (c == element) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						arg0.consume();
					}
				}
			}
		});

		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("SansSerif", Font.PLAIN, 12));

		txtFldLastName = new JFormattedTextField();
		txtFldLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				for (char element : ILLEGAL_CHARACTERS) {
					if (c == element) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						e.consume();
					}
				}
			}
		});

		lblChapterNumber = new JLabel("Chapter Number");
		lblChapterNumber.setFont(new Font("SansSerif", Font.PLAIN, 12));

		f = NumberFormat.getIntegerInstance();
		f.setMaximumIntegerDigits(5);
		f.setGroupingUsed(false);
		txtFldChapterNumber = new JFormattedTextField(f);
		txtFldChapterNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(c <= 27 || (c >= 48 && c <= 57))) {
					java.awt.Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});

		lblType = new JLabel("Type");
		lblType.setFont(new Font("SansSerif", Font.PLAIN, 14));

		TypeList = new JList();
		TypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TypeList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Set Type Description
				if (TypeList.getSelectedIndex() > -1) {
					TypeDescription.setText(typeListInfo[1][TypeList
							.getSelectedIndex()]);
				} else {
					TypeDescription
							.setText("Please select the participant type.");
				}
			}
		});
		TypeList.setFont(new Font("SansSerif", Font.PLAIN, 12));
		TypeList.setModel(new AbstractListModel() {
			String[] values = typeListInfo[0];

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		TypeList.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblConference = new JLabel("Conferences");
		lblConference.setFont(new Font("SansSerif", Font.PLAIN, 14));

		ConferenceList = new JList();
		ConferenceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ConferenceList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// Set description
				if (ConferenceList.getSelectedIndex() > -1) {
					ConferenceDescription
							.setText(conferenceListInfo[3][ConferenceList
									.getSelectedIndex()]
									+ "\nStarting Date: "
									+ conferenceListInfo[1][ConferenceList
											.getSelectedIndex()]
									+ "\nEnding Date: "
									+ conferenceListInfo[2][ConferenceList
											.getSelectedIndex()]);
				} else {
					ConferenceDescription
							.setText("Please select the conference to be attended.");
				}

				// Set Workshop stuff
				if (ConferenceList.getSelectedIndex() != -1) {
					WorkshopsDescription
							.setText("Please preregister for any workshops that this participant wishes to participate in.");
					WorkshopsList.setModel(new AbstractListModel() {
						String[] values = workshopListInfo[ConferenceList
								.getSelectedIndex()][1]
								.toArray(new String[workshopListInfo[ConferenceList
										.getSelectedIndex()][1].size()]);

						@Override
						public int getSize() {
							return values.length;
						}

						@Override
						public Object getElementAt(int index) {
							return values[index];
						}
					});
				} else {
					WorkshopsDescription
							.setText("Select a conference to view available workshops");
					WorkshopsList.setModel(new AbstractListModel() {
						String[] values = new String[0];

						@Override
						public int getSize() {
							return values.length;
						}

						@Override
						public Object getElementAt(int index) {
							return values[index];
						}
					});
				}
			}
		});
		ConferenceList.setFont(new Font("SansSerif", Font.PLAIN, 12));
		ConferenceList.setModel(new AbstractListModel() {
			String[] values = conferenceListInfo[0];

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ConferenceList.setBorder(new LineBorder(new Color(0, 0, 0)));

		TypeDescription = new JTextArea();
		TypeDescription.setText("Please select the participant type.");
		TypeDescription.setEditable(false);
		TypeDescription.setWrapStyleWord(true);
		TypeDescription.setLineWrap(true);
		TypeDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
		TypeDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		ConferenceDescription = new JTextArea();
		ConferenceDescription
				.setText("Please select the conference to be attended.");
		ConferenceDescription.setEditable(false);
		ConferenceDescription.setWrapStyleWord(true);
		ConferenceDescription.setLineWrap(true);
		ConferenceDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ConferenceDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblWorkshop = new JLabel("Workshops");
		lblWorkshop.setFont(new Font("SansSerif", Font.PLAIN, 14));

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean formComplete = true;

				lblType.setText("Type");
				lblType.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblType.setForeground(Color.BLACK);
				lblFirstName.setText("First Name");
				lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblFirstName.setForeground(Color.BLACK);
				lblLastName.setText("Last Name");
				lblLastName.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblLastName.setForeground(Color.BLACK);
				lblConference.setText("Conference");
				lblConference.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblConference.setForeground(Color.BLACK);
				lblChapterNumber.setText("Chapter Number");
				lblChapterNumber.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblChapterNumber.setForeground(Color.BLACK);

				if (txtFldFirstName.getText().equals("")) {
					formComplete = false;
					lblFirstName.setText("First Name*");
					lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblFirstName.setForeground(Color.RED);
				}
				if (txtFldLastName.getText().equals("")) {
					formComplete = false;
					lblLastName.setText("Last Name*");
					lblLastName.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblLastName.setForeground(Color.RED);
				}
				if (txtFldChapterNumber.getText().equals("")) {
					formComplete = false;
					lblChapterNumber.setText("Chapter Number*");
					lblChapterNumber.setFont(new Font("SansSerif", Font.BOLD,
							13));
					lblChapterNumber.setForeground(Color.RED);
				}
				if (TypeList.getSelectedIndex() == -1) {
					formComplete = false;
					lblType.setText("Type*");
					lblType.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblType.setForeground(Color.RED);
				}
				if (ConferenceList.getSelectedIndex() == -1) {
					formComplete = false;
					lblConference.setText("Conference*");
					lblConference.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblConference.setForeground(Color.RED);
				}
				UserFrame.contentPane.repaint();
				UserFrame.contentPane.revalidate();

				if (formComplete) {
					Confirm prompt = new Confirm();
					prompt.setLocationRelativeTo(UserFrame.contentPane);
					prompt.setVisible(true);
					if (prompt.isConfirmed()) {
						Dialog dialog = new Dialog();
						dialog.setLocationRelativeTo(UserFrame.contentPane);
						dialog.txtrField.setText("Participant form submitted");
						dialog.setVisible(true);

						String fname = txtFldFirstName.getText();
						String lname = txtFldLastName.getText();
						int chapter = Integer.parseInt(txtFldChapterNumber
								.getText());
						int type = TypeList.getSelectedIndex();
						int conference = ConferenceList.getSelectedIndex();
						int[] workshops = WorkshopsList.getSelectedIndices();
						for (int i = 0; i < workshops.length; i++) {
							if (conference >= 1) {
								workshops[i] += workshopListInfo[0][1].size();
							}
							if (conference >= 2) {
								workshops[i] += workshopListInfo[1][1].size();
							}
						}

						workshopC.read();
						Participant part = participantsC.insert(
								conferenceArray[conference], typeArray[type],
								fname, lname, chapter);
						participantsC.write();
						readParticipants();

						if (WorkshopsList.getSelectedIndex() != -1) {
							for (int workshop : workshops) {
								workshopRegC.insert(workshopArray[workshop],
										part);

								workshopRegC.write();
								workshopRegC.read();
							}
						}

						txtFldFirstName.setText("");
						txtFldLastName.setText("");
						txtFldChapterNumber.setText("");
						TypeList.clearSelection();
						ConferenceList.clearSelection();
						cl.show(panel, "1");
					}
				} else {
					IncompleteForm error = new IncompleteForm();
					error.setLocationRelativeTo(UserFrame.contentPane);
					error.setVisible(true);
				}
			}
		});
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "1");
			}
		});
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		GroupLayout gl_registration = new GroupLayout(registration);
		gl_registration
				.setHorizontalGroup(gl_registration
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_registration
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_registration
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_registration
																		.createParallelGroup(
																				Alignment.TRAILING)
																		.addGroup(
																				gl_registration
																						.createSequentialGroup()
																						.addGroup(
																								gl_registration
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												btnRegister)
																										.addGroup(
																												gl_registration
																														.createSequentialGroup()
																														.addComponent(
																																lblFirstName)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																txtFldFirstName,
																																GroupLayout.PREFERRED_SIZE,
																																128,
																																GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																ComponentPlacement.UNRELATED)
																														.addComponent(
																																lblLastName)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																txtFldLastName,
																																GroupLayout.PREFERRED_SIZE,
																																132,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGap(22))
																		.addGroup(
																				gl_registration
																						.createSequentialGroup()
																						.addComponent(
																								btnCancel,
																								GroupLayout.PREFERRED_SIZE,
																								97,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(18)))
														.addGroup(
																gl_registration
																		.createSequentialGroup()
																		.addComponent(
																				lblChapterNumber)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldChapterNumber,
																				GroupLayout.PREFERRED_SIZE,
																				62,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_registration
																		.createSequentialGroup()
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								lblConference,
																								Alignment.LEADING)
																						.addComponent(
																								lblType,
																								Alignment.LEADING)
																						.addComponent(
																								TypeList,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								110,
																								Short.MAX_VALUE)
																						.addComponent(
																								ConferenceList,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(18)
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								TypeDescription,
																								0,
																								0,
																								Short.MAX_VALUE)
																						.addComponent(
																								ConferenceDescription,
																								GroupLayout.DEFAULT_SIZE,
																								265,
																								Short.MAX_VALUE))))
										.addGap(34)
										.addGroup(
												gl_registration
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_1,
																GroupLayout.DEFAULT_SIZE,
																450,
																Short.MAX_VALUE)
														.addComponent(
																lblWorkshop,
																GroupLayout.PREFERRED_SIZE,
																86,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																450,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_registration
				.setVerticalGroup(gl_registration
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_registration
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_registration
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFirstName)
														.addComponent(
																txtFldFirstName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblLastName)
														.addComponent(
																txtFldLastName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblWorkshop))
										.addGroup(
												gl_registration
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_registration
																		.createSequentialGroup()
																		.addGap(11)
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblChapterNumber)
																						.addComponent(
																								txtFldChapterNumber,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblType)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								TypeList,
																								GroupLayout.PREFERRED_SIZE,
																								116,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								TypeDescription,
																								GroupLayout.PREFERRED_SIZE,
																								116,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblConference)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								ConferenceList,
																								GroupLayout.PREFERRED_SIZE,
																								129,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								ConferenceDescription,
																								GroupLayout.PREFERRED_SIZE,
																								129,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				93,
																				Short.MAX_VALUE)
																		.addGroup(
																				gl_registration
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								btnRegister)
																						.addComponent(
																								btnCancel)))
														.addGroup(
																gl_registration
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				289,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				scrollPane_1,
																				GroupLayout.DEFAULT_SIZE,
																				163,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		WorkshopsDescription = new JTextArea();
		WorkshopsDescription
				.setText("Select a conference to view available workshops");
		WorkshopsDescription.setEditable(false);
		WorkshopsDescription.setWrapStyleWord(true);
		WorkshopsDescription.setLineWrap(true);
		WorkshopsDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
		WorkshopsDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setViewportView(WorkshopsDescription);

		WorkshopsList = new JList();
		WorkshopsList.setBorder(new LineBorder(new Color(0, 0, 0)));
		WorkshopsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (WorkshopsList.getSelectedIndex() >= 0) {

					WorkshopsDescription.setText(workshopListInfo[ConferenceList
							.getSelectedIndex()][3].get(WorkshopsList
							.getSelectedIndex())
							+ "\n"
							+ workshopListInfo[ConferenceList
									.getSelectedIndex()][2].get(WorkshopsList
									.getSelectedIndex()) + "\n");
				}
			}
		});
		WorkshopsList.setModel(new AbstractListModel() {
			String[] values = new String[0];

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		WorkshopsList.setFont(new Font("SansSerif", Font.PLAIN, 12));
		scrollPane.setViewportView(WorkshopsList);
		registration.setLayout(gl_registration);

		schedule = new JPanel();
		panel.add(schedule, "4");

		lblFirstName_2 = new JLabel("First Name: ");
		lblFirstName_2.setFont(new Font("SansSerif", Font.PLAIN, 14));

		FirstNameReport = new JLabel("New label");
		FirstNameReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblLastName_2 = new JLabel("Last Name: ");
		lblLastName_2.setFont(new Font("SansSerif", Font.PLAIN, 14));

		LastNameReport = new JLabel("New label");
		LastNameReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblChapterNumber_2 = new JLabel("Chapter Number: ");
		lblChapterNumber_2.setFont(new Font("SansSerif", Font.PLAIN, 14));

		ChapterReport = new JLabel("New label");
		ChapterReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblParticipantType = new JLabel("Participant Type: ");
		lblParticipantType.setFont(new Font("SansSerif", Font.PLAIN, 14));

		TypeReport = new JLabel("New label");
		TypeReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblConferenceAttending = new JLabel("Conference Attending: ");
		lblConferenceAttending.setFont(new Font("SansSerif", Font.PLAIN, 14));

		lblNewLabel_3 = new JLabel("Starting Date: ");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 14));

		ConferenceReport = new JLabel("New label");
		ConferenceReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		StartingDateReport = new JLabel("New label");
		StartingDateReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		JScrollPane scrollPane_3 = new JScrollPane();

		JLabel lblEndingDate = new JLabel("Ending Date: ");
		lblEndingDate.setFont(new Font("SansSerif", Font.PLAIN, 14));

		EndingDateReport = new JLabel("New label");
		EndingDateReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		JLabel lblWorkshopsAttending = new JLabel("Workshops Attending:");
		lblWorkshopsAttending.setFont(new Font("SansSerif", Font.PLAIN, 14));

		JLabel lblParticipantId = new JLabel("Participant ID: ");
		lblParticipantId.setFont(new Font("SansSerif", Font.PLAIN, 14));

		IDReport = new JLabel("New label");
		IDReport.setFont(new Font("SansSerif", Font.BOLD, 14));

		JButton btnSaveReport = new JButton("Close");
		btnSaveReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panel, "1");
			}
		});
		btnSaveReport.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JButton btnClose = new JButton("Save Report");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserReport userReport = new UserReport("\\reports\\"
						+ retrieve.getFirstName().replace(" ", "")
						+ retrieve.getLastName().replace(" ", "")
						+ "Report.pdf");

				userReport.setData(FirstNameReport.getText(),
						LastNameReport.getText(), ChapterReport.getText(),
						TypeReport.getText(), ConferenceReport.getText(),
						IDReport.getText(), StartingDateReport.getText(),
						EndingDateReport.getText(), workshopList);

				userReport.write();
				userReport.open();
			}
		});
		btnClose.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GroupLayout gl_schedule = new GroupLayout(schedule);
		gl_schedule
				.setHorizontalGroup(gl_schedule
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_schedule
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_schedule
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_3,
																GroupLayout.DEFAULT_SIZE,
																886,
																Short.MAX_VALUE)
														.addGroup(
																gl_schedule
																		.createSequentialGroup()
																		.addComponent(
																				btnSaveReport)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnClose))
														.addGroup(
																gl_schedule
																		.createSequentialGroup()
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_schedule
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblWorkshopsAttending,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblEndingDate,
																																GroupLayout.PREFERRED_SIZE,
																																91,
																																GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																EndingDateReport,
																																GroupLayout.DEFAULT_SIZE,
																																173,
																																Short.MAX_VALUE)))
																						.addGroup(
																								Alignment.LEADING,
																								gl_schedule
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblNewLabel_3)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																StartingDateReport,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblConferenceAttending)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																ConferenceReport,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblParticipantType)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																TypeReport,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblChapterNumber_2)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																ChapterReport,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGroup(
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblLastName_2)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																LastNameReport,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGroup(
																												Alignment.LEADING,
																												gl_schedule
																														.createSequentialGroup()
																														.addComponent(
																																lblFirstName_2)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																FirstNameReport,
																																GroupLayout.PREFERRED_SIZE,
																																188,
																																GroupLayout.PREFERRED_SIZE))))
																		.addGap(438)
																		.addComponent(
																				lblParticipantId)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				IDReport,
																				GroupLayout.PREFERRED_SIZE,
																				68,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)))
										.addContainerGap()));
		gl_schedule
				.setVerticalGroup(gl_schedule
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_schedule
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_schedule
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_schedule
																		.createSequentialGroup()
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblFirstName_2)
																						.addComponent(
																								FirstNameReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblLastName_2)
																						.addComponent(
																								LastNameReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblChapterNumber_2)
																						.addComponent(
																								ChapterReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblParticipantType)
																						.addComponent(
																								TypeReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblConferenceAttending)
																						.addComponent(
																								ConferenceReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblNewLabel_3)
																						.addComponent(
																								StartingDateReport))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_schedule
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblEndingDate,
																								GroupLayout.PREFERRED_SIZE,
																								19,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								EndingDateReport,
																								GroupLayout.PREFERRED_SIZE,
																								19,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addComponent(
																				lblWorkshopsAttending,
																				GroupLayout.PREFERRED_SIZE,
																				19,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_schedule
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblParticipantId,
																				GroupLayout.PREFERRED_SIZE,
																				19,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				IDReport,
																				GroupLayout.PREFERRED_SIZE,
																				19,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(4)
										.addComponent(scrollPane_3,
												GroupLayout.PREFERRED_SIZE,
												224, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 27,
												Short.MAX_VALUE)
										.addGroup(
												gl_schedule
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnSaveReport)
														.addComponent(btnClose))
										.addContainerGap()));

		table_1 = new JTable();
		table_1.setRowSelectionAllowed(false);
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, }, new String[] { "Name",
						"Starting Date" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(table_1);
		schedule.setLayout(gl_schedule);

	}
}
