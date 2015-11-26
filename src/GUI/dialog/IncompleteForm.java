package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import GUI.AdminFrame;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class IncompleteForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea txtrErrorMissingFields;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IncompleteForm dialog = new IncompleteForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IncompleteForm() {
		setModal(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Error: Missing Forms");
		setBounds(100, 100, 288, 160);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrErrorMissingFields = new JTextArea();
			txtrErrorMissingFields
					.setFont(new Font("SansSerif", Font.PLAIN, 13));
			txtrErrorMissingFields.setEditable(false);
			txtrErrorMissingFields
					.setText("Error!\r\nMissing Forms\r\n\r\nPlease fill in all of the marked forms\r\n");
		}

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(IncompleteForm.class
				.getResource("/resources/32-software-update-urgent.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtrErrorMissingFields,
										GroupLayout.DEFAULT_SIZE, 228,
										Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(txtrErrorMissingFields,
												GroupLayout.PREFERRED_SIZE, 83,
												Short.MAX_VALUE))
						.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton();
				closeButton.setText("OK");
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Closes the dialog
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
			}
		}
	}
}
