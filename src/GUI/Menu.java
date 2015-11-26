package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Menu extends JFrame {

	private static JPanel contentPane;
	private static JPasswordField passwordField;
	private static JTextField textField;
	private static String password;
	private static String username;
	private static Menu frame;
	private static AdminFrame adminFrame;
	private static UserFrame userFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		readPW();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Reads and stores the password
	 */

	private static void readPW() {
		username = "admin";
		password = "password";
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					System.getProperty("user.dir") + "\\config\\PASSWORD.txt"));
			while (in.ready()) {
				String x = in.readLine();

				if (x.length() > 10 && x.substring(0, 10).equals("Password: ")) {
					password = x.substring(10);
				} else if (x.length() > 10
						&& x.substring(0, 10).equals("Username: ")) {
					username = x.substring(10);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void login() {
		if (textField.getText().equals(username)
				&& passwordField.getText().equals(password)) {
			adminFrame = new AdminFrame();
			adminFrame.setLocationRelativeTo(contentPane);
			adminFrame.setVisible(true);
			frame.dispose();
		} else {
			Dialog dialog = new Dialog();
			dialog.setTitle("Incorrect Password");
			dialog.txtrField
					.setText("You entered an invalid username or password. Please try again.");
			dialog.setVisible(true);
		}
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Menu.class.getResource("/resources/FULL-fblaicon.png")));
		setTitle("FBLA DESKTOP APPLICATION PROGRAMMING 2014");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Menu.class
				.getResource("/resources/FULL-fbla.png")));

		JPanel panel = new JPanel();
		panel.setBorder(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(154)
																		.addComponent(
																				label))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				262,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				42,
																				Short.MAX_VALUE)
																		.addComponent(
																				panel_1,
																				GroupLayout.PREFERRED_SIZE,
																				262,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(label,
												GroupLayout.PREFERRED_SIZE,
												196, GroupLayout.PREFERRED_SIZE)
										.addGap(38)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																155,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																panel_1,
																GroupLayout.PREFERRED_SIZE,
																155,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(53, Short.MAX_VALUE)));

		JLabel lblNewLabel = new JLabel("Participant Login");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

		JButton btnContinueAsA = new JButton("Continue as a Participant");
		btnContinueAsA.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnContinueAsA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userFrame = new UserFrame();
				userFrame.setVisible(true);
				userFrame.setLocationRelativeTo(contentPane);
				dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(89)
																		.addComponent(
																				lblNewLabel))
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnContinueAsA,
																				GroupLayout.DEFAULT_SIZE,
																				238,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnContinueAsA, GroupLayout.DEFAULT_SIZE,
								115, Short.MAX_VALUE).addContainerGap()));
		panel_1.setLayout(gl_panel_1);

		JLabel lblChapterAdministrator = new JLabel("Chapter Login");
		lblChapterAdministrator.setFont(new Font("SansSerif", Font.PLAIN, 11));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(91)
																.addComponent(
																		lblChapterAdministrator))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		panel_2,
																		GroupLayout.DEFAULT_SIZE,
																		242,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(lblChapterAdministrator)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 119,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 11));

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		textField.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 11));

		JButton btnLogin = new JButton("Log-in");
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGap(87)
																		.addComponent(
																				btnLogin))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblPassword)
																						.addComponent(
																								lblUsername))
																		.addGap(6)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								textField,
																								GroupLayout.PREFERRED_SIZE,
																								114,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								passwordField,
																								116,
																								116,
																								116))))
										.addContainerGap(58, Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addContainerGap(
																				14,
																				Short.MAX_VALUE)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								textField,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblUsername))
																		.addGap(9)
																		.addComponent(
																				lblPassword)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				24,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnLogin))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGap(40)
																		.addComponent(
																				passwordField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
