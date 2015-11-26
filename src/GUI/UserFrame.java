package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.dialog.Dialog;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class UserFrame extends JFrame {

	public static JPanel contentPane;
	private static UserPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				UserFrame.class.getResource("/resources/FULL-fblaicon.png")));
		setTitle("FBLA DESKTOP APPLICATION PROGRAMMING 2014");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmBackToMenu = new JMenuItem("Back to Menu");
		mntmBackToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setLocationRelativeTo(contentPane);
				menu.setVisible(true);
				dispose();
			}
		});
		mntmBackToMenu.setIcon(new ImageIcon(UserFrame.class
				.getResource("/resources/16-go-previous.png")));
		mnFile.add(mntmBackToMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setIcon(new ImageIcon(UserFrame.class
				.getResource("/resources/16-process-stop.png")));
		mnFile.add(mntmExit);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dialog dialog = new Dialog();
				dialog.setTitle("About");
				dialog.setBounds(100, 100, 550, 450);
				dialog.setLocationRelativeTo(null);
				dialog.txtrField
						.setText("FBLA DESKTOP APPLICATION PROGRAMMING 2014\n"
								+ "National Fall Leadership Conference Registration and Event Manager\n"
								+ "____________________________________________________________________________\n"
								+ "A program that allows for chapters to register participants for the National Fall leadership Conference as well as manipulate workshop, member, and conference information. The program also provides chapters and participants with PDF reports describing the participants registered for certain events, the participants registered per conference, and the schedules of each participant.\n"
								+ "Made in Java\n\n"
								+ "Anthony Liu\n"
								+ "Alpharetta High school\n"
								+ "Georgia\n\n"
								+ "Icons have been aquired from https://www.iconfinder.com/iconsets/tango-icon-library and are licensed under an Attribution-Non-Commercial 3.0 Netherlands Creative Commons License available here: http://creativecommons.org/licenses/by-nc/3.0/nl/legalcode");
				dialog.setVisible(true);
			}
		});
		mntmAbout.setIcon(new ImageIcon(UserFrame.class
				.getResource("/resources/16-help-browser.png")));
		mnAbout.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		mainPanel = new UserPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
	}

}
