package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.AdminFrame;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Confirm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextArea txtrAreYouSure;
	private boolean confirm = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Confirm dialog = new Confirm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Confirm() {
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Confirmation");
		setBounds(100, 100, 350, 150);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrAreYouSure = new JTextArea();
			txtrAreYouSure.setEditable(false);
			txtrAreYouSure.setTabSize(4);
			txtrAreYouSure.setWrapStyleWord(true);
			txtrAreYouSure.setLineWrap(true);
			txtrAreYouSure.setFont(new Font("SansSerif", Font.PLAIN, 13));
			txtrAreYouSure.setText("Are you sure?");
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel.createSequentialGroup().addGap(50)
						.addComponent(txtrAreYouSure).addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGap(5)
						.addComponent(txtrAreYouSure, GroupLayout.DEFAULT_SIZE,
								85, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(20);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Sets confirm to true then closes the dialog
						confirm = true;
						setVisible(false);
						dispose();
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Closes the dialog
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/*
	 * Returns whether or not the user clicks yes
	 */
	public boolean isConfirmed() {
		return confirm;
	}
}
