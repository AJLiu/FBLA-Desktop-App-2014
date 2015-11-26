package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import textConnection.Conference;
import textConnection.ConferenceConnector;
import textConnection.Participant;
import textConnection.ParticipantConnector;
import textConnection.Type;
import textConnection.TypeConnector;
import textConnection.Workshop;
import textConnection.WorkshopConnector;
import textConnection.WorkshopRegistration;
import textConnection.WorkshopRegistrationConnector;
import GUI.dialog.Confirm;
import GUI.dialog.IncompleteForm;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class AdminPanel extends JPanel {
	private final char[] ILLEGAL_CHARACTERS = { '|' };

	public static JTable table;
	private JTable table_1;
	public static JTable table_2;
	private JTable table_3;
	private JFormattedTextField txtFldChapterNumber;
	private JFormattedTextField txtFldChapterNumber_1;
	private JFormattedTextField txtFldFirstName;
	private JFormattedTextField txtFldLastName;
	private static SimpleDateFormat formatter;
	private JList ConferenceList;
	private JTextArea ConferenceDescription;
	private JTextArea ConferenceDescription1;
	private JList TypeList;
	private JTextArea TypeDescription;
	private JTextArea TypeDescription1;
	private JTextArea TypeDescriptionBox;
	private JList WorkshopsList;
	private JTextArea WorkshopsDescription;
	private JTextArea WorkshopsDescription1;
	private static JTabbedPane tabbedPane;
	private JPanel WestPanel;
	private CardLayout cl;
	private CardLayout cl1;
	private CardLayout cl2;
	private CardLayout cl3;
	private JPanel WestParticipants;
	private JFormattedTextField txtFldChapterNumber1;
	private JFormattedTextField txtFldFirstName1;
	private JFormattedTextField txtFldLastName1;
	private JList ConferenceList1;
	private JList TypeList1;
	private JList WorkshopsList1;
	private JPanel SouthPanel;
	private boolean restLoaded = false;

	private String tabDescriptionWorkshops = "Workshops are presentaions available at all conferences. Participants should preregister for these in the preregistration panel.";
	private String tabDescriptionConferences = "National Fall Leadership Conferences are the FBLA are events where students and advisers participate in motivational general sessions and professional development and career planning workshops.";
	private String tabDescriptionParticipants = "Participants are the ones participating in the national fall leadership conferences.";
	private String tabDescriptionType = "These are the type of participants.";

	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblConference;
	private JLabel lblChapterNumber;
	private JLabel lblType;
	private JLabel lblFirstName1;
	private JLabel lblLastName1;
	private JLabel lblConference1;
	private JLabel lblChapterNumber1;
	private JLabel lblType1;

	private String[][] typeListInfo;
	private String[][] typeTableInfo;
	private static String[][] conferenceListInfo;
	private String[][] conferenceTableInfo;
	private static String[][] participantInfo;
	private static ArrayList<String>[][] workshopListInfo;
	private static ArrayList<Workshop>[] workshopList;
	private static String[][] workshopTableInfo;
	private ArrayList<String[]>[] workshopRegParticipantInfo;
	private ArrayList<String[]>[] workshopRegWorkshopInfo;

	private ArrayList<Workshop>[] workshopRegParticipant;
	private ArrayList<Participant>[] workshopRegWorkshop;
	private Conference[] conferenceArray;
	private Type[] typeArray;
	private static Workshop[] workshopArray;
	private static Participant[] participantArray;
	private WorkshopRegistration[] workshopRegArray;

	private TypeConnector typeC = new TypeConnector("\\data\\TYPE.txt");
	private ConferenceConnector conferenceC = new ConferenceConnector(
			"\\data\\CONFERENCES.txt");
	private static WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");
	private static ParticipantConnector participantsC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");
	private WorkshopRegistrationConnector workshopRegC = new WorkshopRegistrationConnector(
			"\\data\\WKSHP_REGISTRATIONS.txt");
	private JTable southParticipantsPanel;
	private JTextArea BlankPanelDescription;
	private JTextField Location;
	private JButton btnSaveConference;
	private JLabel lblWorkshopName;
	private JLabel lblWorkshopDescription;
	private JTextField WorkshopName;
	private JLabel lblWorkshopDate;
	private JTextArea WorkshopDescription;
	private JLabel lblWorkshopConference;
	private JList WorkshopConfList;
	private JTextArea WorkshopConfDescription;
	private JButton btnSubmitNewWorkshop;
	private JTable southWorkshopsPanel;
	private JPanel WestWorkshops;
	private JPanel WestWorkshopsUpdate;
	private JLabel lblWorkshopName1;
	private JTextField WorkshopName1;
	private JLabel lblWorkshopDescription1;
	private JTextArea WorkshopDescription1;
	private JLabel lblWorkshopDate1;
	private JLabel lblWorkshopConference1;
	private JList WorkshopConfList1;
	private JTextArea WorkshopConfDescription1;
	private JButton btnUpdateWorkshop;
	private JButton btnDeleteWorkshop;
	private JButton btnBackToNew_1;
	private JLabel lblMonth;
	private JComboBox smonth;
	private JLabel lblYear;
	private JComboBox syear;
	private JComboBox eyear;
	private JLabel label_4;
	private JComboBox emonth;
	private JLabel label_5;
	private JComboBox eday;
	private JLabel label_6;
	private JComboBox sday;
	private JComboBox workshopDay;
	private JComboBox workshopMonth;
	private JComboBox workshopYear;
	private JComboBox workshopHour;
	private JLabel lblMinute;
	private JComboBox workshopMinute;
	private JLabel label_7;
	private JComboBox workshopDay1;
	private JLabel label_8;
	private JComboBox workshopMonth1;
	private JLabel label_9;
	private JComboBox workshopYear1;
	private JComboBox workshopMinute1;
	private JComboBox workshopHour1;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel lblDay_1;
	private JLabel lblMonth_1;
	private JLabel lblYear_1;
	private JLabel lblTime;
	private JTextField ConferenceAbbrv;

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
		// Type Table
		typeTableInfo = new String[typeListInfo[0].length][1];
		for (int i = 0; i < typeTableInfo.length; i++) {
			typeTableInfo[i][0] = typeListInfo[0][i];
		}
	}

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
		// Conference Table
		conferenceTableInfo = new String[conferenceListInfo[0].length][conferenceListInfo.length];
		for (int i = 0; i < conferenceTableInfo.length; i++) {
			conferenceTableInfo[i][0] = conferenceListInfo[0][i];
			conferenceTableInfo[i][1] = conferenceListInfo[3][i];
			conferenceTableInfo[i][2] = conferenceListInfo[1][i];
			conferenceTableInfo[i][3] = conferenceListInfo[2][i];
		}
	}

	public static void readWorkshop() {
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

		workshopList = new ArrayList[conferenceListInfo[0].length];
		for (int i = 0; i < conferenceListInfo[0].length; i++) {
			Workshop[] tempWorkshopArray = workshopC.get(workshopC.search(2,
					conferenceListInfo[0][i]));
			workshopList[i] = new ArrayList<Workshop>();
			for (Workshop x : tempWorkshopArray) {
				workshopList[i].add(x);
			}
		}
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

	public static void readParticipants() {
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

	private void readWorkshopRegistrations() {
		workshopRegC.read();

		workshopRegArray = workshopRegC.getData();

		// WorkshopReg for Participant Panel
		workshopRegParticipant = new ArrayList[participantArray.length];
		for (int i = 0; i < workshopRegParticipant.length; i++) {
			workshopRegParticipant[i] = new ArrayList<Workshop>();
			Participant temp = participantArray[i];
			Integer[] participantIndices = workshopRegC.search(2,
					"" + temp.getID());
			for (int z : participantIndices) {
				workshopRegParticipant[i]
						.add(workshopRegC.get(z).getWorkshop());
			}
		}

		workshopRegParticipantInfo = new ArrayList[participantArray.length];
		for (int x = 0; x < participantArray.length; x++) {
			workshopRegParticipantInfo[x] = new ArrayList<String[]>();
			for (int y = 0; y < workshopRegParticipant[x].size(); y++) {
				String[] temp = new String[3];
				temp[0] = workshopRegParticipant[x].get(y).getName();
				formatter = new SimpleDateFormat("dd-MMM-yyyy");
				temp[1] = formatter.format(workshopRegParticipant[x].get(y)
						.getDate().getTime());
				formatter = new SimpleDateFormat("hh:mm a");
				temp[2] = formatter.format(workshopRegParticipant[x].get(y)
						.getDate().getTime());
				workshopRegParticipantInfo[x].add(temp);
			}

		}

		// WorkshopReg for Workshop Panel
		workshopRegWorkshop = new ArrayList[workshopArray.length];
		for (int i = 0; i < workshopRegWorkshop.length; i++) {
			workshopRegWorkshop[i] = new ArrayList<Participant>();
			Integer[] z = workshopRegC.search(1, "" + workshopC.get(i).getID());
			if (z.length > 0) {
				for (int x = 0; x < workshopRegC.get(z[0]).getParticipants().length; x++) {
					workshopRegWorkshop[i].add(workshopRegC.get(z[0])
							.getParticipants()[x]);
				}
			}
		}
		workshopRegWorkshopInfo = new ArrayList[workshopArray.length];
		for (int i = 0; i < workshopArray.length; i++) {
			workshopRegWorkshopInfo[i] = new ArrayList<String[]>();
			for (int x = 0; x < workshopRegWorkshop[i].size(); x++) {
				String[] temp = new String[4];
				temp[0] = workshopRegWorkshop[i].get(x).getFirstName();
				temp[1] = workshopRegWorkshop[i].get(x).getLastName();
				temp[2] = "" + workshopRegWorkshop[i].get(x).getChapter();
				temp[3] = workshopRegWorkshop[i].get(x).getType()
						.getAbbreviation();

				workshopRegWorkshopInfo[i].add(temp);
			}
		}

	}

	public static void changeTab(int ind) {
		tabbedPane.setSelectedIndex(ind);
	}

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	public AdminPanel() {
		readType();
		readConference();
		readWorkshop();
		readParticipants();
		readWorkshopRegistrations();

		cl = new CardLayout();
		cl1 = new CardLayout();
		cl2 = new CardLayout();
		cl3 = new CardLayout();

		WestPanel = new JPanel();
		WestPanel.setLayout(cl);

		WestWorkshops = new JPanel();
		WestPanel.add(WestWorkshops, "1");
		WestWorkshops.setLayout(cl3);

		JPanel WestWorkshopsNew = new JPanel();
		WestWorkshops.add(WestWorkshopsNew, "1");

		lblWorkshopName = new JLabel("Workshop Name");
		lblWorkshopName.setFont(new Font("SansSerif", Font.PLAIN, 12));

		lblWorkshopDescription = new JLabel("Workshop Description");
		lblWorkshopDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopName = new JTextField();
		WorkshopName.addKeyListener(new KeyAdapter() {
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
		WorkshopName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopName.setColumns(10);

		lblWorkshopDate = new JLabel("Workshop Date");
		lblWorkshopDate.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopDescription = new JTextArea();
		WorkshopDescription.addKeyListener(new KeyAdapter() {
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
		WorkshopDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
		WorkshopDescription.setLineWrap(true);
		WorkshopDescription.setWrapStyleWord(true);
		WorkshopDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblWorkshopConference = new JLabel("Workshop Conference");
		lblWorkshopConference.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopConfList = new JList();
		WorkshopConfList.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopConfList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Set description
				if (WorkshopConfList.getSelectedIndex() > -1) {
					WorkshopConfDescription
							.setText(conferenceListInfo[3][WorkshopConfList
									.getSelectedIndex()]
									+ "\nStarting Date: "
									+ conferenceListInfo[1][WorkshopConfList
											.getSelectedIndex()]
									+ "\nEnding Date: "
									+ conferenceListInfo[2][WorkshopConfList
											.getSelectedIndex()]);
				} else {
					WorkshopConfDescription
							.setText("Please select the conference to be attended.");
				}
			}
		});
		WorkshopConfList.setBorder(new LineBorder(new Color(0, 0, 0)));
		WorkshopConfList.setModel(new AbstractListModel() {
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
		WorkshopConfDescription = new JTextArea();
		WorkshopConfDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopConfDescription.setWrapStyleWord(true);
		WorkshopConfDescription.setLineWrap(true);
		WorkshopConfDescription.setEditable(false);
		WorkshopConfDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnSubmitNewWorkshop = new JButton("Submit New Workshop");
		btnSubmitNewWorkshop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean formComplete = true;

				lblWorkshopName.setText("Workshop Name");
				lblWorkshopName.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblWorkshopName.setForeground(Color.BLACK);
				lblWorkshopDescription.setText("Workshop Description");
				lblWorkshopDescription.setFont(new Font("SansSerif",
						Font.PLAIN, 12));
				lblWorkshopDescription.setForeground(Color.BLACK);
				lblWorkshopConference.setText("Workshop Conference");
				lblWorkshopConference.setFont(new Font("SansSerif", Font.PLAIN,
						12));
				lblWorkshopConference.setForeground(Color.BLACK);

				if (WorkshopName.getText().equals("")) {
					formComplete = false;
					lblWorkshopName.setText("Workshop Name*");
					lblWorkshopName
							.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblWorkshopName.setForeground(Color.RED);
				}
				if (WorkshopDescription.getText().equals("")) {
					formComplete = false;
					lblWorkshopDescription.setText("Workshop Description*");
					lblWorkshopDescription.setFont(new Font("SansSerif",
							Font.BOLD, 13));
					lblWorkshopDescription.setForeground(Color.RED);
				}
				if (WorkshopConfList.getSelectedIndex() == -1) {
					formComplete = false;
					lblWorkshopConference.setText("Workshop Conference*");
					lblWorkshopConference.setFont(new Font("SansSerif",
							Font.BOLD, 13));
					lblWorkshopConference.setForeground(Color.RED);
				}
				AdminFrame.contentPane.repaint();
				AdminFrame.contentPane.revalidate();

				if (formComplete) {
					Confirm prompt = new Confirm();
					prompt.txtrAreYouSure
							.setText("Are you sure you wish to submit this workshop form?");
					prompt.setVisible(true);
					if (prompt.isConfirmed()) {
						String name = WorkshopName.getText();
						String desc = WorkshopDescription.getText();
						Calendar date = new GregorianCalendar(workshopYear
								.getSelectedIndex() + 2000, workshopMonth
								.getSelectedIndex(), workshopDay
								.getSelectedIndex() + 1, workshopHour
								.getSelectedIndex(), workshopMinute
								.getSelectedIndex() * 15);
						Conference conference = conferenceArray[WorkshopConfList
								.getSelectedIndex()];

						workshopC.insert(conference, name, desc, date);
						workshopC.write();
						readWorkshop();
						readWorkshopRegistrations();

						WorkshopName.setText("");
						WorkshopDescription.setText("");
						WorkshopConfList.clearSelection();
						workshopDay.setSelectedIndex(0);
						workshopMonth.setSelectedIndex(0);
						workshopYear.setSelectedIndex(0);
						workshopMinute.setSelectedIndex(0);
						workshopHour.setSelectedIndex(0);

						table.setModel(new DefaultTableModel(workshopTableInfo,
								new String[] { "Conference", "Name", "Date",
										"Time" }) {
							boolean[] columnEditables = new boolean[] { false,
									false, false, false };

							@Override
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						table.getColumnModel().getColumn(0)
								.setPreferredWidth(40);
						table.getColumnModel().getColumn(1)
								.setPreferredWidth(140);
						table.getColumnModel().getColumn(2)
								.setPreferredWidth(60);
						table.getColumnModel().getColumn(3)
								.setPreferredWidth(60);
					}
				} else {
					IncompleteForm error = new IncompleteForm();
					error.setVisible(true);
				}
			}
		});
		btnSubmitNewWorkshop.setFont(new Font("SansSerif", Font.PLAIN, 12));

		workshopDay = new JComboBox();
		workshopDay.setModel(new DefaultComboBoxModel(new String[] { "01",
				"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

		workshopMonth = new JComboBox();
		workshopMonth.setModel(new DefaultComboBoxModel(new String[] {
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" }));

		workshopYear = new JComboBox();
		workshopYear.setModel(new DefaultComboBoxModel(new String[] { "2000",
				"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
				"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028", "2029" }));

		workshopHour = new JComboBox();
		workshopHour.setModel(new DefaultComboBoxModel(new String[] { "00",
				"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23" }));

		lblMinute = new JLabel(":");

		workshopMinute = new JComboBox();
		workshopMinute.setModel(new DefaultComboBoxModel(new String[] { "00",
				"15", "30", "45" }));

		lblDay_1 = new JLabel("Day");

		lblMonth_1 = new JLabel("Month");

		lblYear_1 = new JLabel("Year");

		lblTime = new JLabel("Time");
		GroupLayout gl_WestWorkshopsNew = new GroupLayout(WestWorkshopsNew);
		gl_WestWorkshopsNew
				.setHorizontalGroup(gl_WestWorkshopsNew
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestWorkshopsNew
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestWorkshopsNew
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblWorkshopDate)
														.addComponent(
																lblWorkshopDescription)
														.addGroup(
																gl_WestWorkshopsNew
																		.createSequentialGroup()
																		.addComponent(
																				WorkshopConfList,
																				GroupLayout.PREFERRED_SIZE,
																				121,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				WorkshopConfDescription,
																				GroupLayout.DEFAULT_SIZE,
																				285,
																				Short.MAX_VALUE))
														.addGroup(
																gl_WestWorkshopsNew
																		.createSequentialGroup()
																		.addComponent(
																				lblTime)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopHour,
																				GroupLayout.PREFERRED_SIZE,
																				52,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblMinute,
																				GroupLayout.PREFERRED_SIZE,
																				4,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopMinute,
																				GroupLayout.PREFERRED_SIZE,
																				60,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_WestWorkshopsNew
																		.createSequentialGroup()
																		.addComponent(
																				lblDay_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopDay,
																				GroupLayout.PREFERRED_SIZE,
																				42,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblMonth_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopMonth,
																				GroupLayout.PREFERRED_SIZE,
																				88,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblYear_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopYear,
																				GroupLayout.PREFERRED_SIZE,
																				57,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnSubmitNewWorkshop)
														.addComponent(
																lblWorkshopConference)
														.addGroup(
																gl_WestWorkshopsNew
																		.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																		.addGroup(
																				gl_WestWorkshopsNew
																						.createSequentialGroup()
																						.addComponent(
																								lblWorkshopName)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								WorkshopName))
																		.addComponent(
																				WorkshopDescription,
																				Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE,
																				365,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_WestWorkshopsNew
				.setVerticalGroup(gl_WestWorkshopsNew
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestWorkshopsNew
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestWorkshopsNew
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblWorkshopName)
														.addComponent(
																WorkshopName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblWorkshopDescription)
										.addGap(1)
										.addComponent(WorkshopDescription,
												GroupLayout.PREFERRED_SIZE,
												147, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblWorkshopDate)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestWorkshopsNew
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																workshopDay,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																workshopMonth,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																workshopYear,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDay_1)
														.addComponent(
																lblMonth_1)
														.addComponent(lblYear_1))
										.addGap(18)
										.addGroup(
												gl_WestWorkshopsNew
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																workshopHour,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMinute)
														.addComponent(
																workshopMinute,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTime))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblWorkshopConference)
										.addGap(11)
										.addGroup(
												gl_WestWorkshopsNew
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																WorkshopConfList,
																GroupLayout.PREFERRED_SIZE,
																119,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																WorkshopConfDescription,
																GroupLayout.PREFERRED_SIZE,
																118,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(btnSubmitNewWorkshop,
												GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26)));
		WestWorkshopsNew.setLayout(gl_WestWorkshopsNew);

		WestWorkshopsUpdate = new JPanel();
		WestWorkshops.add(WestWorkshopsUpdate, "2");

		lblWorkshopName1 = new JLabel("Workshop Name");
		lblWorkshopName1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopName1 = new JTextField();
		WorkshopName1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopName1.setColumns(10);

		lblWorkshopDescription1 = new JLabel("Workshop Description");
		lblWorkshopDescription1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopDescription1 = new JTextArea();
		WorkshopDescription1.setWrapStyleWord(true);
		WorkshopDescription1.setLineWrap(true);
		WorkshopDescription1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		WorkshopDescription1.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblWorkshopDate1 = new JLabel("Workshop Date");
		lblWorkshopDate1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		lblWorkshopConference1 = new JLabel("Workshop Conference");
		lblWorkshopConference1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		WorkshopConfList1 = new JList();
		WorkshopConfList1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Set description
				if (WorkshopConfList1.getSelectedIndex() > -1) {
					WorkshopConfDescription1
							.setText(conferenceListInfo[3][WorkshopConfList1
									.getSelectedIndex()]
									+ "\nStarting Date: "
									+ conferenceListInfo[1][WorkshopConfList1
											.getSelectedIndex()]
									+ "\nEnding Date: "
									+ conferenceListInfo[2][WorkshopConfList1
											.getSelectedIndex()]);
				} else {
					WorkshopConfDescription1
							.setText("Please select the conference to be attended.");
				}
			}
		});
		WorkshopConfList1.setModel(new AbstractListModel() {
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
		WorkshopConfList1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopConfList1.setBorder(new LineBorder(new Color(0, 0, 0)));

		WorkshopConfDescription1 = new JTextArea();
		WorkshopConfDescription1.setWrapStyleWord(true);
		WorkshopConfDescription1.setLineWrap(true);
		WorkshopConfDescription1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopConfDescription1.setEditable(false);
		WorkshopConfDescription1.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnUpdateWorkshop = new JButton("Update Workshop");
		btnUpdateWorkshop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean formComplete = true;

				lblWorkshopName1.setText("Workshop Name");
				lblWorkshopName1.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblWorkshopName1.setForeground(Color.BLACK);
				lblWorkshopDescription1.setText("Workshop Description");
				lblWorkshopDescription1.setFont(new Font("SansSerif",
						Font.PLAIN, 12));
				lblWorkshopDescription1.setForeground(Color.BLACK);
				lblWorkshopConference1.setText("Workshop Conference");
				lblWorkshopConference1.setFont(new Font("SansSerif",
						Font.PLAIN, 12));
				lblWorkshopConference1.setForeground(Color.BLACK);

				if (WorkshopName1.getText().equals("")) {
					formComplete = false;
					lblWorkshopName1.setText("Workshop Name*");
					lblWorkshopName1.setFont(new Font("SansSerif", Font.BOLD,
							13));
					lblWorkshopName1.setForeground(Color.RED);
				}
				if (WorkshopDescription1.getText().equals("")) {
					formComplete = false;
					lblWorkshopDescription1.setText("Workshop Description*");
					lblWorkshopDescription1.setFont(new Font("SansSerif",
							Font.BOLD, 13));
					lblWorkshopDescription1.setForeground(Color.RED);
				}
				if (WorkshopConfList1.getSelectedIndex() == -1) {
					formComplete = false;
					lblWorkshopConference1.setText("Workshop Conference*");
					lblWorkshopConference1.setFont(new Font("SansSerif",
							Font.BOLD, 13));
					lblWorkshopConference1.setForeground(Color.RED);
				}
				AdminFrame.contentPane.repaint();
				AdminFrame.contentPane.revalidate();

				if (formComplete) {
					Confirm prompt = new Confirm();
					prompt.txtrAreYouSure
							.setText("Are you sure you wish to update this workshop form?");
					prompt.setVisible(true);
					if (prompt.isConfirmed()) {
						String name = WorkshopName1.getText();
						String desc = WorkshopDescription1.getText();
						Calendar date = new GregorianCalendar(workshopYear1
								.getSelectedIndex() + 2000, workshopMonth1
								.getSelectedIndex(), workshopDay1
								.getSelectedIndex() + 1, workshopHour1
								.getSelectedIndex(), workshopMinute1
								.getSelectedIndex() * 15);
						Conference conference = conferenceArray[WorkshopConfList1
								.getSelectedIndex()];

						workshopC.update(table.getSelectedRow(), name, desc,
								date, conference);
						workshopC.write();
						readWorkshop();
						readWorkshopRegistrations();

						WorkshopName1.setText("");
						WorkshopDescription1.setText("");
						WorkshopConfList1.clearSelection();
						workshopDay1.setSelectedIndex(0);
						workshopMonth1.setSelectedIndex(0);
						workshopYear1.setSelectedIndex(0);
						workshopMinute1.setSelectedIndex(0);
						workshopHour1.setSelectedIndex(0);

						cl3.show(WestWorkshops, "1");

						table.setModel(new DefaultTableModel(workshopTableInfo,
								new String[] { "Conference", "Name", "Date",
										"Time" }) {
							boolean[] columnEditables = new boolean[] { false,
									false, false, false };

							@Override
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						table.getColumnModel().getColumn(0)
								.setPreferredWidth(40);
						table.getColumnModel().getColumn(1)
								.setPreferredWidth(140);
						table.getColumnModel().getColumn(2)
								.setPreferredWidth(60);
						table.getColumnModel().getColumn(3)
								.setPreferredWidth(60);
					}
				} else {
					IncompleteForm error = new IncompleteForm();
					error.setVisible(true);
				}
			}
		});
		btnUpdateWorkshop.setFont(new Font("SansSerif", Font.PLAIN, 12));

		btnDeleteWorkshop = new JButton("Delete Workshop");
		btnDeleteWorkshop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Confirm prompt = new Confirm();
				prompt.txtrAreYouSure
						.setText("Are you sure you wish to delete "
								+ workshopTableInfo[table.getSelectedRow()][1]
								+ "?");
				prompt.setVisible(true);
				if (prompt.isConfirmed()) {
					workshopRegC.delete(workshopC.delete(table.getSelectedRow()));
					workshopC.write();
					workshopRegC.write();
					readWorkshop();
					readWorkshopRegistrations();

					cl3.show(WestWorkshops, "1");

					table.setModel(new DefaultTableModel(
							workshopTableInfo,
							new String[] { "Conference", "Name", "Date", "Time" }) {
						boolean[] columnEditables = new boolean[] { false,
								false, false, false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(140);
					table.getColumnModel().getColumn(2).setPreferredWidth(60);
					table.getColumnModel().getColumn(3).setPreferredWidth(60);
				}
			}
		});
		btnDeleteWorkshop.setFont(new Font("SansSerif", Font.PLAIN, 12));

		btnBackToNew_1 = new JButton("Back to New Workshop Creation");
		btnBackToNew_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl3.show(WestWorkshops, "1");
				table.clearSelection();
			}
		});
		btnBackToNew_1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		label_7 = new JLabel("Day");

		workshopDay1 = new JComboBox();
		workshopDay1.setModel(new DefaultComboBoxModel(new String[] { "01",
				"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

		label_8 = new JLabel("Month");

		workshopMonth1 = new JComboBox();
		workshopMonth1.setModel(new DefaultComboBoxModel(new String[] {
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" }));

		label_9 = new JLabel("Year");

		workshopYear1 = new JComboBox();
		workshopYear1.setModel(new DefaultComboBoxModel(new String[] { "2000",
				"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
				"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028", "2029" }));

		workshopMinute1 = new JComboBox();
		workshopMinute1.setModel(new DefaultComboBoxModel(new String[] { "00",
				"15", "30", "45" }));

		workshopHour1 = new JComboBox();
		workshopHour1.setModel(new DefaultComboBoxModel(new String[] { "00",
				"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23" }));

		label_10 = new JLabel("Time");

		label_11 = new JLabel(":");
		GroupLayout gl_WestWorkshopsUpdate = new GroupLayout(
				WestWorkshopsUpdate);
		gl_WestWorkshopsUpdate
				.setHorizontalGroup(gl_WestWorkshopsUpdate
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestWorkshopsUpdate
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				btnBackToNew_1)
																		.addGap(148))
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addGroup(
																				gl_WestWorkshopsUpdate
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_WestWorkshopsUpdate
																										.createSequentialGroup()
																										.addComponent(
																												WorkshopConfList1,
																												GroupLayout.PREFERRED_SIZE,
																												121,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												WorkshopConfDescription1,
																												GroupLayout.DEFAULT_SIZE,
																												277,
																												Short.MAX_VALUE))
																						.addGroup(
																								gl_WestWorkshopsUpdate
																										.createSequentialGroup()
																										.addComponent(
																												btnUpdateWorkshop)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												154,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnDeleteWorkshop,
																												GroupLayout.PREFERRED_SIZE,
																												131,
																												GroupLayout.PREFERRED_SIZE)))
																		.addContainerGap())
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				lblWorkshopDate1)
																		.addContainerGap(
																				341,
																				Short.MAX_VALUE))
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				lblWorkshopConference1)
																		.addContainerGap(
																				303,
																				Short.MAX_VALUE))
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				lblWorkshopDescription1)
																		.addContainerGap(
																				304,
																				Short.MAX_VALUE))
														.addGroup(
																gl_WestWorkshopsUpdate
																		.createSequentialGroup()
																		.addGroup(
																				gl_WestWorkshopsUpdate
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addGroup(
																								gl_WestWorkshopsUpdate
																										.createSequentialGroup()
																										.addComponent(
																												lblWorkshopName1)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												WorkshopName1))
																						.addComponent(
																								WorkshopDescription1,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								365,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								Alignment.LEADING,
																								gl_WestWorkshopsUpdate
																										.createSequentialGroup()
																										.addGroup(
																												gl_WestWorkshopsUpdate
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																label_7,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																label_10,
																																GroupLayout.DEFAULT_SIZE,
																																22,
																																Short.MAX_VALUE))
																										.addGap(4)
																										.addGroup(
																												gl_WestWorkshopsUpdate
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_WestWorkshopsUpdate
																																		.createSequentialGroup()
																																		.addComponent(
																																				workshopHour1,
																																				GroupLayout.PREFERRED_SIZE,
																																				52,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				label_11,
																																				GroupLayout.PREFERRED_SIZE,
																																				6,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				workshopMinute1,
																																				GroupLayout.PREFERRED_SIZE,
																																				60,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																gl_WestWorkshopsUpdate
																																		.createSequentialGroup()
																																		.addComponent(
																																				workshopDay1,
																																				GroupLayout.PREFERRED_SIZE,
																																				42,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				label_8)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				workshopMonth1,
																																				GroupLayout.PREFERRED_SIZE,
																																				88,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				label_9)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				workshopYear1,
																																				GroupLayout.PREFERRED_SIZE,
																																				57,
																																				GroupLayout.PREFERRED_SIZE)))))
																		.addGap(61)))));
		gl_WestWorkshopsUpdate
				.setVerticalGroup(gl_WestWorkshopsUpdate
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestWorkshopsUpdate
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnBackToNew_1)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblWorkshopName1)
														.addComponent(
																WorkshopName1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblWorkshopDescription1)
										.addGap(1)
										.addComponent(WorkshopDescription1,
												GroupLayout.PREFERRED_SIZE,
												123, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblWorkshopDate1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																label_7,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																workshopDay1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_8)
														.addComponent(
																workshopMonth1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_9)
														.addComponent(
																workshopYear1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_10)
														.addComponent(
																workshopHour1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_11)
														.addComponent(
																workshopMinute1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(11)
										.addComponent(lblWorkshopConference1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																WorkshopConfList1,
																GroupLayout.PREFERRED_SIZE,
																130,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																WorkshopConfDescription1,
																GroupLayout.PREFERRED_SIZE,
																129,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_WestWorkshopsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnUpdateWorkshop,
																GroupLayout.PREFERRED_SIZE,
																33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnDeleteWorkshop,
																GroupLayout.PREFERRED_SIZE,
																33,
																GroupLayout.PREFERRED_SIZE))
										.addGap(10)));
		WestWorkshopsUpdate.setLayout(gl_WestWorkshopsUpdate);

		JPanel WestConferences = new JPanel();
		WestPanel.add(WestConferences, "2");

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("SansSerif", Font.PLAIN, 12));

		Location = new JTextField();
		Location.addKeyListener(new KeyAdapter() {
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
		Location.setBorder(new LineBorder(new Color(171, 173, 179)));
		Location.setEditable(false);
		Location.setColumns(10);

		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JLabel lblEndingdate = new JLabel("Ending Date");
		lblEndingdate.setFont(new Font("SansSerif", Font.PLAIN, 12));

		btnSaveConference = new JButton("Save Conference Changes");
		btnSaveConference.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table_1.getSelectedRow() != -1) {
					if (!Location.getText().equals("")
							&& !ConferenceAbbrv.getText().equals("")) {
						Confirm prompt = new Confirm();
						prompt.txtrAreYouSure
								.setText("Are you sure you wish to submit this participant form?");
						prompt.setVisible(true);
						if (prompt.isConfirmed()) {
							formatter = new SimpleDateFormat("dd-MMMM-YYYY");
							GregorianCalendar day1 = new GregorianCalendar(
									syear.getSelectedIndex() + 2000, smonth
											.getSelectedIndex(), sday
											.getSelectedIndex() + 1);
							GregorianCalendar day2 = new GregorianCalendar(
									eyear.getSelectedIndex() + 2000, emonth
											.getSelectedIndex(), eday
											.getSelectedIndex() + 1);

							conferenceC.update(table_1.getSelectedRow(),
									Location.getText(),
									ConferenceAbbrv.getText(), day1, day2);
							conferenceC.write();
							readConference();

							table_1.setModel(new DefaultTableModel(
									conferenceTableInfo, new String[] { "Code",
											"Location", "Starting Date",
											"Ending Date" }) {
								boolean[] columnEditables = new boolean[] {
										false, false, false, false };

								@Override
								public boolean isCellEditable(int row,
										int column) {
									return columnEditables[column];
								}
							});
							table_1.getColumnModel().getColumn(0)
									.setPreferredWidth(40);
							table_1.getColumnModel().getColumn(1)
									.setPreferredWidth(140);
							table_1.getColumnModel().getColumn(2)
									.setPreferredWidth(60);
							table_1.getColumnModel().getColumn(3)
									.setPreferredWidth(60);
						}
					}
				}
			}
		});
		btnSaveConference.setFont(new Font("SansSerif", Font.PLAIN, 12));

		sday = new JComboBox();
		sday.setEnabled(false);
		sday.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03",
				"04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));

		JLabel lblDay = new JLabel("Day:");

		lblMonth = new JLabel("Month:");

		smonth = new JComboBox();
		smonth.setEnabled(false);
		smonth.setModel(new DefaultComboBoxModel(new String[] { "January",
				"February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" }));

		lblYear = new JLabel("Year:");

		syear = new JComboBox();
		syear.setEnabled(false);
		syear.setModel(new DefaultComboBoxModel(new String[] { "2000", "2001",
				"2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
				"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025",
				"2026", "2027", "2028", "2029" }));

		eyear = new JComboBox();
		eyear.setEnabled(false);
		eyear.setModel(new DefaultComboBoxModel(new String[] { "2000", "2001",
				"2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
				"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025",
				"2026", "2027", "2028", "2029" }));

		label_4 = new JLabel("Year:");

		emonth = new JComboBox();
		emonth.setEnabled(false);
		emonth.setModel(new DefaultComboBoxModel(new String[] { "January",
				"February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" }));

		label_5 = new JLabel("Month:");

		eday = new JComboBox();
		eday.setEnabled(false);
		eday.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03",
				"04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));

		label_6 = new JLabel("Day:");

		JLabel lblAbbreviation = new JLabel("Abbreviation");

		ConferenceAbbrv = new JTextField();
		ConferenceAbbrv.setEditable(false);
		ConferenceAbbrv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				for (char element : ILLEGAL_CHARACTERS) {
					if (c == element) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						arg0.consume();
					}
				}

				if (ConferenceAbbrv.getText().length() >= 2) {
					java.awt.Toolkit.getDefaultToolkit().beep();
					arg0.consume();
				}
			}
		});
		ConferenceAbbrv.setColumns(10);
		GroupLayout gl_WestConferences = new GroupLayout(WestConferences);
		gl_WestConferences
				.setHorizontalGroup(gl_WestConferences
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestConferences
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestConferences
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestConferences
																		.createSequentialGroup()
																		.addGroup(
																				gl_WestConferences
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_WestConferences
																										.createSequentialGroup()
																										.addComponent(
																												lblLocation)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												Location,
																												GroupLayout.DEFAULT_SIZE,
																												317,
																												Short.MAX_VALUE))
																						.addComponent(
																								lblEndingdate,
																								GroupLayout.PREFERRED_SIZE,
																								74,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnSaveConference)
																						.addGroup(
																								gl_WestConferences
																										.createSequentialGroup()
																										.addGap(10)
																										.addGroup(
																												gl_WestConferences
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_WestConferences
																																		.createSequentialGroup()
																																		.addComponent(
																																				label_6,
																																				GroupLayout.PREFERRED_SIZE,
																																				29,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				eday,
																																				GroupLayout.PREFERRED_SIZE,
																																				51,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addGap(18)
																																		.addComponent(
																																				label_5,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				emonth,
																																				GroupLayout.PREFERRED_SIZE,
																																				106,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																gl_WestConferences
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblDay)
																																		.addPreferredGap(
																																				ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				sday,
																																				GroupLayout.PREFERRED_SIZE,
																																				51,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addGap(18)
																																		.addComponent(
																																				lblMonth)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				smonth,
																																				GroupLayout.PREFERRED_SIZE,
																																				106,
																																				GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addGroup(
																												gl_WestConferences
																														.createParallelGroup(
																																Alignment.LEADING,
																																false)
																														.addComponent(
																																label_4,
																																Alignment.TRAILING,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																lblYear,
																																Alignment.TRAILING,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_WestConferences
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addComponent(
																																syear,
																																GroupLayout.PREFERRED_SIZE,
																																72,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																eyear,
																																GroupLayout.PREFERRED_SIZE,
																																72,
																																GroupLayout.PREFERRED_SIZE))))
																		.addGap(58))
														.addGroup(
																gl_WestConferences
																		.createSequentialGroup()
																		.addComponent(
																				lblStartingDate)
																		.addContainerGap(
																				355,
																				Short.MAX_VALUE))
														.addGroup(
																gl_WestConferences
																		.createSequentialGroup()
																		.addComponent(
																				lblAbbreviation)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				ConferenceAbbrv,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				283,
																				Short.MAX_VALUE)))));
		gl_WestConferences
				.setVerticalGroup(gl_WestConferences
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestConferences
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestConferences
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblLocation)
														.addComponent(
																Location,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestConferences
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAbbreviation)
														.addComponent(
																ConferenceAbbrv,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addComponent(lblStartingDate)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestConferences
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																sday,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDay)
														.addComponent(lblMonth)
														.addComponent(
																smonth,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblYear)
														.addComponent(
																syear,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_WestConferences
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestConferences
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblEndingdate)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_WestConferences
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_WestConferences
																										.createSequentialGroup()
																										.addGroup(
																												gl_WestConferences
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																eday,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addGroup(
																																gl_WestConferences
																																		.createSequentialGroup()
																																		.addGap(3)
																																		.addComponent(
																																				label_6)))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												btnSaveConference,
																												GroupLayout.PREFERRED_SIZE,
																												47,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								eyear,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_WestConferences
																		.createSequentialGroup()
																		.addGap(36)
																		.addGroup(
																				gl_WestConferences
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								label_4)
																						.addComponent(
																								emonth,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								label_5))))
										.addContainerGap(318, Short.MAX_VALUE)));
		WestConferences.setLayout(gl_WestConferences);

		WestParticipants = new JPanel();
		WestPanel.add(WestParticipants, "3");
		WestParticipants.setLayout(cl1);

		JPanel WestParticipantsNew = new JPanel();
		WestParticipants.add(WestParticipantsNew, "1");
		WestParticipantsNew.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 12));

		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("SansSerif", Font.PLAIN, 12));

		lblChapterNumber = new JLabel("Chapter Number");
		lblChapterNumber.setFont(new Font("SansSerif", Font.PLAIN, 12));

		NumberFormat f = NumberFormat.getIntegerInstance();
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
		lblType.setFont(new Font("SansSerif", Font.PLAIN, 12));

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

		lblConference = new JLabel("Conference");
		lblConference.setFont(new Font("SansSerif", Font.PLAIN, 12));

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

		JLabel lblWorkshop = new JLabel("Workshop");
		lblWorkshop.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JScrollPane ScrollPaneWorkshops = new JScrollPane();
		ScrollPaneWorkshops.setBorder(new LineBorder(Color.BLACK));

		TypeDescription = new JTextArea();
		TypeDescription.setLineWrap(true);
		TypeDescription.setWrapStyleWord(true);
		TypeDescription.setText("Please select the participant type.");
		TypeDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
		TypeDescription.setEditable(false);
		TypeDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		ConferenceDescription = new JTextArea();
		ConferenceDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
		ConferenceDescription
				.setText("Please select the conference to be attended.");
		ConferenceDescription.setWrapStyleWord(true);
		ConferenceDescription.setLineWrap(true);
		ConferenceDescription.setEditable(false);
		ConferenceDescription.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnRegister = new JButton("Register New Participant");
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
				AdminFrame.contentPane.repaint();
				AdminFrame.contentPane.revalidate();

				if (formComplete) {
					Confirm prompt = new Confirm();
					prompt.txtrAreYouSure
							.setText("Are you sure you wish to submit this participant form?");
					prompt.setVisible(true);
					if (prompt.isConfirmed()) {
						String fname = txtFldFirstName.getText();
						String lname = txtFldLastName.getText();
						int chapter = Integer.parseInt(txtFldChapterNumber
								.getText());
						int type = TypeList.getSelectedIndex();
						int conference = ConferenceList.getSelectedIndex();
						int[] workshops = WorkshopsList.getSelectedIndices();

						readWorkshopRegistrations();
						readWorkshop();

						Participant part = participantsC.insert(
								conferenceArray[conference], typeArray[type],
								fname, lname, chapter);
						participantsC.write();
						readParticipants();

						if (WorkshopsList.getSelectedIndex() != -1) {
							for (int x : workshops) {
								workshopRegC.insert(
										workshopList[conference].get(x), part);

								workshopRegC.write();
								readWorkshopRegistrations();
							}
						}

						txtFldFirstName.setText("");
						txtFldLastName.setText("");
						txtFldChapterNumber.setText("");
						TypeList.clearSelection();
						ConferenceList.clearSelection();

						table_2.setModel(new DefaultTableModel(participantInfo,
								new String[] { "Conference", "Type",
										"First Name", "Last Name", "Chapter" }) {
							Class[] columnTypes = new Class[] { String.class,
									String.class, String.class, String.class,
									String.class };

							boolean[] columnEditables = new boolean[] { false,
									false, false, false, false };

							@Override
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}

							@Override
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						table_2.getColumnModel().getColumn(0)
								.setPreferredWidth(60);
						table_2.getColumnModel().getColumn(1)
								.setPreferredWidth(55);
						table_2.getColumnModel().getColumn(2)
								.setPreferredWidth(100);
						table_2.getColumnModel().getColumn(3)
								.setPreferredWidth(100);
						table_2.getColumnModel().getColumn(4)
								.setPreferredWidth(65);

					}
				} else {
					IncompleteForm error = new IncompleteForm();
					error.setVisible(true);
				}
			}
		});

		JScrollPane ScrollPaneWorkshopsDescription = new JScrollPane();
		ScrollPaneWorkshopsDescription.setBorder(null);

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
		GroupLayout gl_WestParticipantsNew = new GroupLayout(
				WestParticipantsNew);
		gl_WestParticipantsNew
				.setHorizontalGroup(gl_WestParticipantsNew
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestParticipantsNew
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestParticipantsNew
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestParticipantsNew
																		.createSequentialGroup()
																		.addComponent(
																				lblFirstName)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldFirstName,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)
																		.addComponent(
																				lblLastName)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldLastName,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.TRAILING,
																gl_WestParticipantsNew
																		.createSequentialGroup()
																		.addGroup(
																				gl_WestParticipantsNew
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								TypeList,
																								GroupLayout.DEFAULT_SIZE,
																								120,
																								Short.MAX_VALUE)
																						.addComponent(
																								ConferenceList,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								120,
																								Short.MAX_VALUE)
																						.addComponent(
																								ScrollPaneWorkshops,
																								GroupLayout.DEFAULT_SIZE,
																								120,
																								Short.MAX_VALUE))
																		.addGap(18)
																		.addGroup(
																				gl_WestParticipantsNew
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								ConferenceDescription,
																								GroupLayout.DEFAULT_SIZE,
																								276,
																								Short.MAX_VALUE)
																						.addComponent(
																								ScrollPaneWorkshopsDescription,
																								GroupLayout.DEFAULT_SIZE,
																								276,
																								Short.MAX_VALUE)
																						.addComponent(
																								TypeDescription,
																								GroupLayout.DEFAULT_SIZE,
																								276,
																								Short.MAX_VALUE)))
														.addComponent(
																btnRegister,
																GroupLayout.PREFERRED_SIZE,
																181,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblWorkshop)
														.addComponent(
																lblConference)
														.addComponent(lblType)
														.addGroup(
																gl_WestParticipantsNew
																		.createSequentialGroup()
																		.addComponent(
																				lblChapterNumber)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldChapterNumber,
																				GroupLayout.PREFERRED_SIZE,
																				64,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_WestParticipantsNew
				.setVerticalGroup(gl_WestParticipantsNew
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestParticipantsNew
										.createSequentialGroup()
										.addGap(15)
										.addGroup(
												gl_WestParticipantsNew
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFirstName)
														.addComponent(
																lblLastName)
														.addComponent(
																txtFldLastName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtFldFirstName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsNew
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
												ComponentPlacement.RELATED)
										.addComponent(lblType)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsNew
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestParticipantsNew
																		.createSequentialGroup()
																		.addComponent(
																				TypeList,
																				GroupLayout.PREFERRED_SIZE,
																				75,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblConference))
														.addComponent(
																TypeDescription,
																GroupLayout.PREFERRED_SIZE,
																75,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsNew
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																ConferenceDescription)
														.addComponent(
																ConferenceList,
																GroupLayout.DEFAULT_SIZE,
																85,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblWorkshop)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsNew
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																ScrollPaneWorkshopsDescription,
																GroupLayout.PREFERRED_SIZE,
																132,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																ScrollPaneWorkshops,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnRegister,
												GroupLayout.PREFERRED_SIZE, 37,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(39, Short.MAX_VALUE)));

		WorkshopsDescription = new JTextArea();
		WorkshopsDescription.setWrapStyleWord(true);
		WorkshopsDescription
				.setText("Select a conference to view available workshops");
		WorkshopsDescription.setLineWrap(true);
		WorkshopsDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopsDescription.setEditable(false);
		WorkshopsDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		ScrollPaneWorkshopsDescription.setViewportView(WorkshopsDescription);

		WorkshopsList = new JList();
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
		ScrollPaneWorkshops.setViewportView(WorkshopsList);
		WestParticipantsNew.setLayout(gl_WestParticipantsNew);

		JPanel WestParticipantsUpdate = new JPanel();
		WestParticipants.add(WestParticipantsUpdate, "2");
		WestParticipantsUpdate.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblFirstName1 = new JLabel("First Name");
		lblFirstName1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		txtFldFirstName1 = new JFormattedTextField();
		txtFldFirstName1.addKeyListener(new KeyAdapter() {
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

		lblLastName1 = new JLabel("Last Name");
		lblLastName1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		txtFldLastName1 = new JFormattedTextField();
		txtFldLastName1.addKeyListener(new KeyAdapter() {
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

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBorder(new LineBorder(Color.BLACK));

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBorder(new LineBorder(new Color(0, 0, 0)));

		TypeList1 = new JList();
		TypeList1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Set Type Description
				if (TypeList1.getSelectedIndex() > -1) {
					TypeDescription1.setText(typeListInfo[1][TypeList1
							.getSelectedIndex()]);
				} else {
					TypeDescription1
							.setText("Please select the participant type.");
				}
			}
		});
		TypeList1.setModel(new AbstractListModel() {
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
		TypeList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TypeList1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		TypeList1.setBorder(new LineBorder(new Color(0, 0, 0)));

		ConferenceList1 = new JList();
		ConferenceList1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// Set description
				if (ConferenceList1.getSelectedIndex() > -1) {
					ConferenceDescription1
							.setText(conferenceListInfo[3][ConferenceList1
									.getSelectedIndex()]
									+ "\nStarting Date: "
									+ conferenceListInfo[1][ConferenceList1
											.getSelectedIndex()]
									+ "\nEnding Date: "
									+ conferenceListInfo[2][ConferenceList1
											.getSelectedIndex()]);
				} else {
					ConferenceDescription1
							.setText("Please select the conference to be attended.");
				}

				// Set Workshop stuff
				if (ConferenceList1.getSelectedIndex() != -1) {
					WorkshopsDescription1
							.setText("Please preregister for any workshops that this participant wishes to participate in.");
					WorkshopsList1.setModel(new AbstractListModel() {
						String[] values = workshopListInfo[ConferenceList1
								.getSelectedIndex()][1]
								.toArray(new String[workshopListInfo[ConferenceList1
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
					WorkshopsDescription1
							.setText("Select a conference to view available workshops");
					WorkshopsList1.setModel(new AbstractListModel() {
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
		ConferenceList1.setModel(new AbstractListModel() {
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
		ConferenceList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ConferenceList1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		ConferenceList1.setBorder(new LineBorder(new Color(0, 0, 0)));

		TypeDescription1 = new JTextArea();
		TypeDescription1.setWrapStyleWord(true);
		TypeDescription1.setText("Please select the participant type.");
		TypeDescription1.setLineWrap(true);
		TypeDescription1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		TypeDescription1.setEditable(false);
		TypeDescription1.setBorder(new LineBorder(new Color(0, 0, 0)));

		ConferenceDescription1 = new JTextArea();
		ConferenceDescription1.setWrapStyleWord(true);
		ConferenceDescription1
				.setText("Please select the conference to be attended.");
		ConferenceDescription1.setLineWrap(true);
		ConferenceDescription1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		ConferenceDescription1.setEditable(false);
		ConferenceDescription1.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblChapterNumber1 = new JLabel("Chapter Number");
		lblChapterNumber1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		txtFldChapterNumber1 = new JFormattedTextField();
		txtFldChapterNumber1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(c <= 27 || (c >= 48 && c <= 57))) {
					java.awt.Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});

		lblType1 = new JLabel("Type");
		lblType1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		lblConference1 = new JLabel("Conference");
		lblConference1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JLabel lblWorkshop1 = new JLabel("Workshop");
		lblWorkshop1.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JButton btnUpdateSelectedParticipant = new JButton("Update Participant");
		btnUpdateSelectedParticipant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean formComplete = true;

				lblType1.setText("Type");
				lblType1.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblType1.setForeground(Color.BLACK);
				lblFirstName1.setText("First Name");
				lblFirstName1.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblFirstName1.setForeground(Color.BLACK);
				lblLastName1.setText("Last Name");
				lblLastName1.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblLastName1.setForeground(Color.BLACK);
				lblConference1.setText("Conference");
				lblConference1.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblConference1.setForeground(Color.BLACK);
				lblChapterNumber1.setText("Chapter Number");
				lblChapterNumber1
						.setFont(new Font("SansSerif", Font.PLAIN, 12));
				lblChapterNumber1.setForeground(Color.BLACK);

				if (txtFldFirstName1.getText().equals("")) {
					formComplete = false;
					lblFirstName1.setText("First Name*");
					lblFirstName1.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblFirstName1.setForeground(Color.RED);
				}
				if (txtFldLastName1.getText().equals("")) {
					formComplete = false;
					lblLastName1.setText("Last Name*");
					lblLastName1.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblLastName1.setForeground(Color.RED);
				}
				if (txtFldChapterNumber1.getText().equals("")) {
					formComplete = false;
					lblChapterNumber1.setText("Chapter Number*");
					lblChapterNumber1.setFont(new Font("SansSerif", Font.BOLD,
							13));
					lblChapterNumber1.setForeground(Color.RED);
				}
				if (TypeList1.getSelectedIndex() == -1) {
					formComplete = false;
					lblType1.setText("Type*");
					lblType1.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblType1.setForeground(Color.RED);
				}
				if (ConferenceList1.getSelectedIndex() == -1) {
					formComplete = false;
					lblConference1.setText("Conference*");
					lblConference1
							.setFont(new Font("SansSerif", Font.BOLD, 13));
					lblConference1.setForeground(Color.RED);
				}
				AdminFrame.contentPane.repaint();
				AdminFrame.contentPane.revalidate();

				if (formComplete) {
					Confirm prompt = new Confirm();
					prompt.txtrAreYouSure
							.setText("Are you sure you wish to update this form?");
					prompt.setVisible(true);
					if (prompt.isConfirmed()) {
						String fname = txtFldFirstName1.getText();
						String lname = txtFldLastName1.getText();
						int chapter = Integer.parseInt(txtFldChapterNumber1
								.getText());
						int type = TypeList1.getSelectedIndex();
						int conference = ConferenceList1.getSelectedIndex();
						int[] workshops = WorkshopsList1.getSelectedIndices();

						readWorkshop();
						Participant part = participantsC.update(
								table_2.getSelectedRow(),
								conferenceArray[conference], typeArray[type],
								fname, lname, chapter);
						participantsC.write();
						readParticipants();

						workshopRegC.removeParticipant(part);

						if (WorkshopsList1.getSelectedIndex() != -1) {

							for (int x : workshops) {
								workshopRegC.insert(
										workshopList[conference].get(x), part);
							}
							workshopRegC.write();
							readWorkshopRegistrations();
						}

						cl1.show(WestParticipants, "1");

						table_2.setModel(new DefaultTableModel(participantInfo,
								new String[] { "Conference", "Type",
										"First Name", "Last Name", "Chapter" }) {
							Class[] columnTypes = new Class[] { String.class,
									String.class, String.class, String.class,
									String.class };

							boolean[] columnEditables = new boolean[] { false,
									false, false, false, false };

							@Override
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}

							@Override
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						table_2.getColumnModel().getColumn(0)
								.setPreferredWidth(60);
						table_2.getColumnModel().getColumn(1)
								.setPreferredWidth(55);
						table_2.getColumnModel().getColumn(2)
								.setPreferredWidth(100);
						table_2.getColumnModel().getColumn(3)
								.setPreferredWidth(100);
						table_2.getColumnModel().getColumn(4)
								.setPreferredWidth(65);
					}
				} else {
					IncompleteForm error = new IncompleteForm();
					error.setVisible(true);
				}
			}
		});
		btnUpdateSelectedParticipant.setFont(new Font("SansSerif", Font.PLAIN,
				12));

		JButton btnDeleteParticipant = new JButton("Delete Participant");
		btnDeleteParticipant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Confirm prompt = new Confirm();
				prompt.txtrAreYouSure
						.setText("Are you sure you wish to delete "
								+ participantInfo[table_2.getSelectedRow()][2]
								+ " "
								+ participantInfo[table_2.getSelectedRow()][3]
								+ "?");
				prompt.setVisible(true);
				if (prompt.isConfirmed()) {
					participantsC.delete(table_2.getSelectedRow());
					workshopRegC.removeParticipant(participantArray[table_2
							.getSelectedRow()]);
					participantsC.write();
					workshopRegC.write();
					readWorkshopRegistrations();
					readParticipants();

					cl1.show(WestParticipants, "1");

					table_2.setModel(new DefaultTableModel(participantInfo,
							new String[] { "Conference", "Type", "First Name",
									"Last Name", "Chapter" }) {
						Class[] columnTypes = new Class[] { String.class,
								String.class, String.class, String.class,
								String.class };

						boolean[] columnEditables = new boolean[] { false,
								false, false, false, false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}

						@Override
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
					table_2.getColumnModel().getColumn(0).setPreferredWidth(60);
					table_2.getColumnModel().getColumn(1).setPreferredWidth(55);
					table_2.getColumnModel().getColumn(2)
							.setPreferredWidth(100);
					table_2.getColumnModel().getColumn(3)
							.setPreferredWidth(100);
					table_2.getColumnModel().getColumn(4).setPreferredWidth(65);
				}
			}
		});
		btnDeleteParticipant.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JButton btnBackToNew = new JButton(
				"Back to New Participant Registration");
		btnBackToNew.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnBackToNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				table_2.clearSelection();
			}
		});
		GroupLayout gl_WestParticipantsUpdate = new GroupLayout(
				WestParticipantsUpdate);
		gl_WestParticipantsUpdate
				.setHorizontalGroup(gl_WestParticipantsUpdate
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestParticipantsUpdate
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestParticipantsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				lblFirstName1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldFirstName1,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)
																		.addComponent(
																				lblLastName1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldLastName1,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_WestParticipantsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				btnUpdateSelectedParticipant,
																				GroupLayout.PREFERRED_SIZE,
																				181,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(52)
																		.addComponent(
																				btnDeleteParticipant,
																				GroupLayout.PREFERRED_SIZE,
																				181,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addComponent(
																lblWorkshop1)
														.addComponent(
																lblConference1)
														.addComponent(lblType1)
														.addGroup(
																gl_WestParticipantsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				lblChapterNumber1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtFldChapterNumber1,
																				GroupLayout.PREFERRED_SIZE,
																				64,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnBackToNew)
														.addGroup(
																gl_WestParticipantsUpdate
																		.createSequentialGroup()
																		.addGroup(
																				gl_WestParticipantsUpdate
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								TypeList1,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								102,
																								Short.MAX_VALUE)
																						.addComponent(
																								ConferenceList1,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								102,
																								Short.MAX_VALUE)
																						.addComponent(
																								scrollPane_4,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								102,
																								Short.MAX_VALUE))
																		.addGap(18)
																		.addGroup(
																				gl_WestParticipantsUpdate
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								TypeDescription1,
																								GroupLayout.DEFAULT_SIZE,
																								294,
																								Short.MAX_VALUE)
																						.addComponent(
																								scrollPane_5,
																								GroupLayout.DEFAULT_SIZE,
																								294,
																								Short.MAX_VALUE)
																						.addComponent(
																								ConferenceDescription1,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								294,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		gl_WestParticipantsUpdate
				.setVerticalGroup(gl_WestParticipantsUpdate
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestParticipantsUpdate
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnBackToNew)
										.addGap(12)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFirstName1)
														.addComponent(
																lblLastName1)
														.addComponent(
																txtFldLastName1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtFldFirstName1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblChapterNumber1)
														.addComponent(
																txtFldChapterNumber1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblType1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_WestParticipantsUpdate
																		.createSequentialGroup()
																		.addComponent(
																				TypeList1,
																				GroupLayout.PREFERRED_SIZE,
																				75,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblConference1))
														.addComponent(
																TypeDescription1,
																GroupLayout.PREFERRED_SIZE,
																75,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																ConferenceDescription1)
														.addComponent(
																ConferenceList1,
																GroupLayout.DEFAULT_SIZE,
																93,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblWorkshop1)
										.addGap(7)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																scrollPane_5)
														.addComponent(
																scrollPane_4,
																GroupLayout.DEFAULT_SIZE,
																111,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_WestParticipantsUpdate
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnUpdateSelectedParticipant,
																GroupLayout.PREFERRED_SIZE,
																37,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnDeleteParticipant,
																GroupLayout.PREFERRED_SIZE,
																37,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		WorkshopsDescription1 = new JTextArea();
		WorkshopsDescription1.setWrapStyleWord(true);
		WorkshopsDescription1
				.setText("Select a conference to view available workshops");
		WorkshopsDescription1.setLineWrap(true);
		WorkshopsDescription1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		WorkshopsDescription1.setEditable(false);
		WorkshopsDescription1.setBorder(null);
		scrollPane_5.setViewportView(WorkshopsDescription1);

		WorkshopsList1 = new JList();
		WorkshopsList1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (WorkshopsList1.getSelectedIndex() >= 0) {

					WorkshopsDescription1.setText(workshopListInfo[ConferenceList1
							.getSelectedIndex()][3].get(WorkshopsList1
							.getSelectedIndex())
							+ "\n"
							+ workshopListInfo[ConferenceList1
									.getSelectedIndex()][2].get(WorkshopsList1
									.getSelectedIndex()) + "\n");
				}
			}
		});
		WorkshopsList1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		scrollPane_4.setViewportView(WorkshopsList1);
		WestParticipantsUpdate.setLayout(gl_WestParticipantsUpdate);

		JPanel WestTypes = new JPanel();
		WestPanel.add(WestTypes, "4");

		JLabel lblPanel_2 = new JLabel("Edit Participant Type Description");
		lblPanel_2.setFont(new Font("SansSerif", Font.PLAIN, 12));

		TypeDescriptionBox = new JTextArea();
		TypeDescriptionBox.setWrapStyleWord(true);
		TypeDescriptionBox.setLineWrap(true);
		TypeDescriptionBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		TypeDescriptionBox.setEditable(false);

		JButton btnSaveParticipantType = new JButton(
				"Save Participant Type Changes");
		btnSaveParticipantType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Confirm prompt = new Confirm();
				prompt.txtrAreYouSure
						.setText("Are you sure you wish to update this form?");
				prompt.setVisible(true);
				if (prompt.isConfirmed()) {
					typeC.update(table_3.getSelectedRow(),
							typeTableInfo[table_3.getSelectedRow()][0] + "-"
									+ TypeDescriptionBox.getText());
					TypeDescriptionBox.setEditable(false);
					TypeDescriptionBox.setText("");
					table_3.clearSelection();
					typeC.write();
					readType();
				}
			}
		});
		btnSaveParticipantType.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GroupLayout gl_WestTypes = new GroupLayout(WestTypes);
		gl_WestTypes
				.setHorizontalGroup(gl_WestTypes
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_WestTypes
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_WestTypes
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnSaveParticipantType)
														.addComponent(
																lblPanel_2)
														.addComponent(
																TypeDescriptionBox,
																GroupLayout.PREFERRED_SIZE,
																368,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(58, Short.MAX_VALUE)));
		gl_WestTypes.setVerticalGroup(gl_WestTypes.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_WestTypes
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblPanel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(TypeDescriptionBox,
								GroupLayout.PREFERRED_SIZE, 212,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 2160,
								Short.MAX_VALUE)
						.addComponent(btnSaveParticipantType,
								GroupLayout.PREFERRED_SIZE, 47,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		WestTypes.setLayout(gl_WestTypes);

		JPanel EastPanel = new JPanel();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (restLoaded == true) {
					table.clearSelection();
					table_1.clearSelection();
					table_2.clearSelection();
					table_3.clearSelection();
					if (tabbedPane.getSelectedIndex() == 0) {
						cl.show(WestPanel, "1");
						BlankPanelDescription.setText(tabDescriptionWorkshops);
					} else if (tabbedPane.getSelectedIndex() == 1) {
						cl.show(WestPanel, "2");
						BlankPanelDescription
								.setText(tabDescriptionConferences);
					} else if (tabbedPane.getSelectedIndex() == 2) {
						cl.show(WestPanel, "3");
						BlankPanelDescription
								.setText(tabDescriptionParticipants);
					} else if (tabbedPane.getSelectedIndex() == 3) {
						cl.show(WestPanel, "4");
						BlankPanelDescription.setText(tabDescriptionType);
					}
				}
				restLoaded = true;
			}
		});
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabbedPane.setBorder(null);

		JPanel WorkshopsTab = new JPanel();
		WorkshopsTab.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Workshops", null, WorkshopsTab, null);
		WorkshopsTab.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		WorkshopsTab.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectedRow() != -1) {
							cl3.show(WestWorkshops, "2");
							cl2.show(SouthPanel, "1");

							WorkshopName1.setText(workshopTableInfo[table
									.getSelectedRow()][1]);
							WorkshopDescription1.setText(workshopArray[table
									.getSelectedRow()].getDescription());

							if (conferenceListInfo[0][0]
									.equals(workshopTableInfo[table
											.getSelectedRow()][0])) {
								WorkshopConfList1.setSelectedIndex(0);
							} else if (conferenceListInfo[0][1]
									.equals(workshopTableInfo[table
											.getSelectedRow()][0])) {
								WorkshopConfList1.setSelectedIndex(1);
							} else if (conferenceListInfo[0][2]
									.equals(workshopTableInfo[table
											.getSelectedRow()][0])) {
								WorkshopConfList1.setSelectedIndex(2);
							}

							formatter = new SimpleDateFormat("dd");
							workshopDay1.setSelectedItem(formatter
									.format(workshopArray[table
											.getSelectedRow()].getDate()
											.getTime()));
							formatter = new SimpleDateFormat("MMMM");
							workshopMonth1.setSelectedItem(formatter
									.format(workshopArray[table
											.getSelectedRow()].getDate()
											.getTime()));
							formatter = new SimpleDateFormat("YYYY");
							workshopYear1.setSelectedItem(formatter
									.format(workshopArray[table
											.getSelectedRow()].getDate()
											.getTime()));
							formatter = new SimpleDateFormat("HH");
							workshopHour1.setSelectedItem(formatter
									.format(workshopArray[table
											.getSelectedRow()].getDate()
											.getTime()));
							formatter = new SimpleDateFormat("mm");
							workshopMinute1.setSelectedItem(formatter
									.format(workshopArray[table
											.getSelectedRow()].getDate()
											.getTime()));

							southWorkshopsPanel.setModel(new DefaultTableModel(
									workshopRegWorkshopInfo[table
											.getSelectedRow()]
											.toArray(new String[workshopRegWorkshopInfo[table
													.getSelectedRow()].size()][4]),
									new String[] { "First Name", "Last Name",
											"Chapter", "Type" }) {
								boolean[] columnEditables = new boolean[] {
										false, false, false, false };

								@Override
								public boolean isCellEditable(int row,
										int column) {
									return columnEditables[column];
								}
							});

						} else if (table.getSelectedRow() == -1) {
							cl2.show(SouthPanel, "0");
							cl3.show(WestWorkshops, "1");
						}
					}
				});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
					}
				});
		table.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(workshopTableInfo, new String[] {
				"Conference", "Name", "Date", "Time" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane.setViewportView(table);

		JPanel ConferencesTab = new JPanel();
		ConferencesTab.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Conferences", null, ConferencesTab, null);
		ConferencesTab.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		ConferencesTab.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table_1.getSelectedRow() != -1) {
							Location.setEditable(true);
							ConferenceAbbrv.setEditable(true);
							sday.setEnabled(true);
							smonth.setEnabled(true);
							syear.setEnabled(true);
							eday.setEnabled(true);
							emonth.setEnabled(true);
							eyear.setEnabled(true);

							Location.setText(conferenceArray[table_1
									.getSelectedRow()].getLocation());
							ConferenceAbbrv.setText(conferenceArray[table_1
									.getSelectedRow()].getAbbreviation());

							formatter = new SimpleDateFormat("dd");
							sday.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getInitialDate()
											.getTime()));
							formatter = new SimpleDateFormat("MMMM");
							smonth.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getInitialDate()
											.getTime()));
							formatter = new SimpleDateFormat("YYYY");
							syear.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getInitialDate()
											.getTime()));
							formatter = new SimpleDateFormat("dd");
							eday.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getFinalDate()
											.getTime()));
							formatter = new SimpleDateFormat("MMMM");
							emonth.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getFinalDate()
											.getTime()));
							formatter = new SimpleDateFormat("YYYY");
							eyear.setSelectedItem(formatter
									.format(conferenceArray[table_1
											.getSelectedRow()].getFinalDate()
											.getTime()));
						} else {
							Location.setText("");
							ConferenceAbbrv.setText("");
							Location.setEditable(false);
							ConferenceAbbrv.setEditable(false);
							sday.setEnabled(false);
							sday.setSelectedIndex(0);
							smonth.setEnabled(false);
							smonth.setSelectedIndex(0);
							syear.setEnabled(false);
							syear.setSelectedIndex(0);
							eday.setEnabled(false);
							eday.setSelectedIndex(0);
							emonth.setEnabled(false);
							emonth.setSelectedIndex(0);
							eyear.setEnabled(false);
							eyear.setSelectedIndex(0);
						}
					}
				});
		table_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table_1.setModel(new DefaultTableModel(conferenceTableInfo,
				new String[] { "Code", "Location", "Starting Date",
						"Ending Date" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(140);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(60);
		table_1.setShowGrid(false);
		scrollPane_1.setViewportView(table_1);

		JPanel ParticipantsTab = new JPanel();
		ParticipantsTab.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Participants", null, ParticipantsTab, null);
		ParticipantsTab.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		ParticipantsTab.add(scrollPane_2, BorderLayout.CENTER);

		table_2 = new JTable();
		table_2.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table_2.getSelectedRow() != -1) {
							cl1.show(WestParticipants, "2");
							cl2.show(SouthPanel, "3");

							txtFldFirstName1.setText(participantInfo[table_2
									.getSelectedRow()][2]);
							txtFldLastName1.setText(participantInfo[table_2
									.getSelectedRow()][3]);
							txtFldChapterNumber1
									.setText(participantInfo[table_2
											.getSelectedRow()][4]);

							for (int i = 0; i < typeListInfo[2].length; i++) {
								if (typeListInfo[2][i]
										.equals(participantInfo[table_2
												.getSelectedRow()][1])) {
									TypeList1.setSelectedIndex(i);
								}
							}

							for (int i = 0; i < conferenceListInfo[0].length; i++) {
								if (conferenceListInfo[0][i]
										.equals(participantInfo[table_2
												.getSelectedRow()][0])) {
									ConferenceList1.setSelectedIndex(i);
								}
							}

							WorkshopsList1.clearSelection();

							southParticipantsPanel.setModel(new DefaultTableModel(
									workshopRegParticipantInfo[table_2
											.getSelectedRow()]
											.toArray(new String[workshopRegParticipantInfo[table_2
													.getSelectedRow()].size()][3]),
									new String[] { "Workshop", "Date", "Time" }) {
								boolean[] columnEditables = new boolean[] {
										false, false, false };

								@Override
								public boolean isCellEditable(int row,
										int column) {
									return columnEditables[column];
								}
							});
						} else if (table_2.getSelectedRow() == -1) {
							cl2.show(SouthPanel, "0");
							cl1.show(WestParticipants, "1");
						}
					}
				});
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table_2.setModel(new DefaultTableModel(participantInfo, new String[] {
				"Conference", "Type", "First Name", "Last Name", "Chapter" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(65);
		table_2.setShowGrid(false);
		scrollPane_2.setViewportView(table_2);

		JPanel MembersTab = new JPanel();
		MembersTab.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Participant Type", null, MembersTab, null);
		MembersTab.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		MembersTab.add(scrollPane_3);

		table_3 = new JTable();
		table_3.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table_3.getSelectedRow() != -1) {
							TypeDescriptionBox.setEditable(true);
							String str = typeArray[table_3.getSelectedRow()]
									.getDescription();
							for (int i = 0; i < str.length(); i++) {
								if (str.charAt(i) == '-') {
									str = str.substring(i + 1);
								}
							}
							TypeDescriptionBox.setText(str);
						} else {
							TypeDescriptionBox.setEditable(false);
							TypeDescriptionBox.setText("");
						}
					}
				});
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table_3.setModel(new DefaultTableModel(typeTableInfo,
				new String[] { "Type" }) {
			Class[] columnTypes = new Class[] { String.class };

			boolean[] columnEditables = new boolean[] { false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_3.setShowGrid(false);
		scrollPane_3.setViewportView(table_3);

		SouthPanel = new JPanel();
		SouthPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		SouthPanel.setLayout(cl2);

		JPanel BlankPanel = new JPanel();
		SouthPanel.add(BlankPanel, "0");

		BlankPanelDescription = new JTextArea();
		BlankPanelDescription.setText(tabDescriptionWorkshops);
		BlankPanelDescription.setEditable(false);
		BlankPanelDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
		BlankPanelDescription.setWrapStyleWord(true);
		BlankPanelDescription.setLineWrap(true);
		GroupLayout gl_BlankPanel = new GroupLayout(BlankPanel);
		gl_BlankPanel
				.setHorizontalGroup(gl_BlankPanel.createParallelGroup(
						Alignment.LEADING).addGroup(
						gl_BlankPanel.createSequentialGroup().addContainerGap()
								.addComponent(BlankPanelDescription)
								.addContainerGap()));
		gl_BlankPanel.setVerticalGroup(gl_BlankPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_BlankPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(BlankPanelDescription,
								GroupLayout.PREFERRED_SIZE, 144,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(26, Short.MAX_VALUE)));
		BlankPanel.setLayout(gl_BlankPanel);

		JPanel SouthWorkshops = new JPanel();
		SouthPanel.add(SouthWorkshops, "1");
		SouthWorkshops.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_7 = new JScrollPane();
		SouthWorkshops.add(scrollPane_7);

		southWorkshopsPanel = new JTable();
		southWorkshopsPanel.setModel(new DefaultTableModel(new Object[][] { {
				null, null, null, null }, }, new String[] { "First Name",
				"Last Name", "Chapter", "Type" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_7.setViewportView(southWorkshopsPanel);

		JPanel SouthParticipants = new JPanel();
		SouthPanel.add(SouthParticipants, "3");
		SouthParticipants.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_6 = new JScrollPane();
		SouthParticipants.add(scrollPane_6);

		southParticipantsPanel = new JTable();
		southParticipantsPanel.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, }, new String[] {
						"Workshop", "Date", "Time" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		southParticipantsPanel.getColumnModel().getColumn(0)
				.setPreferredWidth(143);
		scrollPane_6.setViewportView(southParticipantsPanel);
		GroupLayout gl_EastPanel = new GroupLayout(EastPanel);
		gl_EastPanel
				.setHorizontalGroup(gl_EastPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_EastPanel
										.createSequentialGroup()
										.addGap(38)
										.addGroup(
												gl_EastPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																tabbedPane,
																Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addComponent(
																SouthPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																437,
																Short.MAX_VALUE))
										.addGap(30)));
		gl_EastPanel.setVerticalGroup(gl_EastPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_EastPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
								291, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(SouthPanel, GroupLayout.PREFERRED_SIZE,
								168, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(41, Short.MAX_VALUE)));
		EastPanel.setLayout(gl_EastPanel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(WestPanel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(7)
						.addComponent(EastPanel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
										.createParallelGroup(
												Alignment.TRAILING, false)
										.addComponent(WestPanel,
												Alignment.LEADING, 0, 0,
												Short.MAX_VALUE)
										.addComponent(EastPanel,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addContainerGap(278, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
