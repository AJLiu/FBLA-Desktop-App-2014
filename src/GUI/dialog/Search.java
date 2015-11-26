package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import textConnection.Participant;
import textConnection.ParticipantConnector;
import textConnection.Workshop;
import textConnection.WorkshopConnector;
import GUI.AdminFrame;
import GUI.AdminPanel;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Search extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField workshopSearchField;
	private JTable workshopTable;
	private JTabbedPane tabbedPane;

	private String[][] participantInfo;
	private String[][] workshopTableInfo;
	private Workshop[] workshopArray;
	private Participant[] participantArray;

	private String[][] searchedParticipantInfo;
	private String[][] searchedWorkshopInfo;
	private Participant[] searchedParticipantArray;
	private Workshop[] searchedWorkshopArray;

	private WorkshopConnector workshopC = new WorkshopConnector(
			"\\data\\WORKSHOPS.txt");
	private ParticipantConnector participantsC = new ParticipantConnector(
			"\\data\\PARTICIPANTS.txt");

	private SimpleDateFormat formatter;
	private JTable participantTable;
	private JTextField participantSearchField1;
	private JTextField participantSearchField2;
	private JFormattedTextField participantSearchField3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Search dialog = new Search();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readWorkshop() {
		workshopC.read();
		workshopArray = workshopC.getData();
		formatter = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm a");
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
		searchedWorkshopArray = workshopArray;
	}

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
		searchedParticipantArray = participantArray;
	}

	private void searchWorkshop() {
		ArrayList<Workshop> temp = new ArrayList<Workshop>();

		for (Workshop y : workshopArray) {
			if (y.getName().toLowerCase()
					.contains(workshopSearchField.getText().toLowerCase())) {
				temp.add(y);
			}
		}

		searchedWorkshopInfo = new String[temp.size()][4];
		searchedWorkshopArray = temp.toArray(new Workshop[temp.size()]);
		for (int i = 0; i < searchedWorkshopArray.length; i++) {
			searchedWorkshopInfo[i][0] = searchedWorkshopArray[i]
					.getConference().getAbbreviation();
			searchedWorkshopInfo[i][1] = searchedWorkshopArray[i].getName();
			formatter = new SimpleDateFormat("dd-MMM-yyyy");
			searchedWorkshopInfo[i][2] = formatter
					.format(searchedWorkshopArray[i].getDate().getTime());
			formatter = new SimpleDateFormat("hh:mm a");
			searchedWorkshopInfo[i][3] = formatter
					.format(searchedWorkshopArray[i].getDate().getTime());
		}
		workshopTable.setModel(new DefaultTableModel(searchedWorkshopInfo,
				new String[] { "Conference", "Name", "Date", "Time" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}

	private void searchParticipants() {
		ArrayList<Participant> temp = new ArrayList<Participant>();

		String fname = participantSearchField1.getText();
		String lname = participantSearchField2.getText();
		String chapter = participantSearchField3.getText();

		for (Participant y : participantArray) {
			if (y.getFirstName().toLowerCase().contains(fname.toLowerCase())
					&& y.getLastName().toLowerCase()
							.contains(lname.toLowerCase())
					&& ("" + y.getChapter()).toString().toLowerCase()
							.contains(chapter.toLowerCase())) {
				temp.add(y);
			}
		}

		searchedParticipantInfo = new String[temp.size()][5];
		searchedParticipantArray = temp.toArray(new Participant[temp.size()]);
		for (int i = 0; i < searchedParticipantArray.length; i++) {
			searchedParticipantInfo[i][0] = searchedParticipantArray[i]
					.getConference().getAbbreviation();
			searchedParticipantInfo[i][1] = searchedParticipantArray[i]
					.getType().getAbbreviation();
			searchedParticipantInfo[i][2] = searchedParticipantArray[i]
					.getFirstName();
			searchedParticipantInfo[i][3] = searchedParticipantArray[i]
					.getLastName();
			searchedParticipantInfo[i][4] = ""
					+ searchedParticipantArray[i].getChapter();
		}
		participantTable.setModel(new DefaultTableModel(
				searchedParticipantInfo, new String[] { "Conference", "Type",
						"First Name", "Last Name", "Chapter" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Search() {
		setResizable(false);
		readWorkshop();
		readParticipants();
		setTitle("Search");
		setBounds(100, 100, 650, 398);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);

		JPanel workshopPanel = new JPanel();
		tabbedPane.addTab("Search Workshops", null, workshopPanel, null);

		JLabel lblNewLabel = new JLabel("Workshop Name");

		workshopSearchField = new JTextField();
		workshopSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				searchWorkshop();
			}
		});
		workshopSearchField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_workshopPanel = new GroupLayout(workshopPanel);
		gl_workshopPanel
				.setHorizontalGroup(gl_workshopPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_workshopPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_workshopPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																601,
																Short.MAX_VALUE)
														.addGroup(
																gl_workshopPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblNewLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				workshopSearchField,
																				GroupLayout.DEFAULT_SIZE,
																				539,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_workshopPanel
				.setVerticalGroup(gl_workshopPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_workshopPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_workshopPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																workshopSearchField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 242,
												Short.MAX_VALUE)
										.addContainerGap()));

		workshopTable = new JTable();
		workshopTable.setShowVerticalLines(false);
		workshopTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		workshopTable.setModel(new DefaultTableModel(workshopTableInfo,
				new String[] { "Conference", "Name", "Date", "Time" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(workshopTable);
		workshopPanel.setLayout(gl_workshopPanel);

		JPanel ParticipantPanel = new JPanel();
		tabbedPane.addTab("Search Participants", null, ParticipantPanel, null);

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblFirstName = new JLabel("First Name");

		participantSearchField1 = new JTextField();
		participantSearchField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchParticipants();
			}
		});
		participantSearchField1.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");

		participantSearchField2 = new JTextField();
		participantSearchField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchParticipants();
			}
		});
		participantSearchField2.setColumns(10);

		JLabel lblChapter = new JLabel("Chapter");

		participantSearchField3 = new JFormattedTextField();
		participantSearchField3.addKeyListener(new KeyAdapter() {
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
		GroupLayout gl_ParticipantPanel = new GroupLayout(ParticipantPanel);
		gl_ParticipantPanel
				.setHorizontalGroup(gl_ParticipantPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_ParticipantPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_ParticipantPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_1,
																GroupLayout.DEFAULT_SIZE,
																601,
																Short.MAX_VALUE)
														.addGroup(
																gl_ParticipantPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblFirstName)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				participantSearchField1,
																				GroupLayout.PREFERRED_SIZE,
																				156,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblLastName)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				participantSearchField2,
																				GroupLayout.PREFERRED_SIZE,
																				156,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				lblChapter)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				participantSearchField3,
																				GroupLayout.DEFAULT_SIZE,
																				123,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_ParticipantPanel
				.setVerticalGroup(gl_ParticipantPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_ParticipantPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_ParticipantPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFirstName)
														.addComponent(
																participantSearchField1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblLastName)
														.addComponent(
																participantSearchField2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																participantSearchField3,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblChapter))
										.addGap(12)
										.addComponent(scrollPane_1,
												GroupLayout.DEFAULT_SIZE, 242,
												Short.MAX_VALUE)
										.addContainerGap()));

		participantTable = new JTable();
		participantTable.setShowVerticalLines(false);
		participantTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		participantTable.setModel(new DefaultTableModel(participantInfo,
				new String[] { "Conference", "Type", "First Name", "Last Name",
						"Chapter" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(participantTable);
		ParticipantPanel.setLayout(gl_ParticipantPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (tabbedPane.getSelectedIndex() == 0) {
							if (workshopTable.getSelectedRow() != -1) {
								for (int i = 0; i < workshopArray.length; i++) {
									Workshop x = workshopArray[i];
									if (x.getID() == searchedWorkshopArray[workshopTable
											.getSelectedRow()].getID()) {
										AdminPanel.changeTab(0);
										AdminPanel.table
												.setRowSelectionInterval(i, i);
										dispose();
									}
								}
							} else {
								Dialog dialog = new Dialog();
								dialog.setTitle("Error: No workshop selected.");
								dialog.txtrField
										.setText("Please select a workshop.");
								dialog.setVisible(true);
							}
						} else {
							if (participantTable.getSelectedRow() != -1) {
								for (int i = 0; i < participantArray.length; i++) {
									Participant x = participantArray[i];
									if (x.getID() == searchedParticipantArray[participantTable
											.getSelectedRow()].getID()) {
										AdminPanel.changeTab(2);
										AdminPanel.table_2
												.setRowSelectionInterval(i, i);
										dispose();
									}
								}
							} else {
								Dialog dialog = new Dialog();
								dialog.setTitle("Error: No workshop selected.");
								dialog.txtrField
										.setText("Please select a participant.");
								dialog.setVisible(true);
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
