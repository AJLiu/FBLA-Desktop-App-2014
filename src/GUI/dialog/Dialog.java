package GUI.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import GUI.AdminFrame;

/**
 * @author Anthony Liu - Alpharetta High School - Desktop Application
 *         Programming 2014
 */

public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextArea txtrField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog() {
		java.awt.Toolkit.getDefaultToolkit().beep();
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Error: Missing Forms");
		setBounds(100, 100, 288, 128);
		setLocationRelativeTo(AdminFrame.contentPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrField = new JTextArea();
			txtrField.setLineWrap(true);
			txtrField.setWrapStyleWord(true);
			txtrField.setFont(new Font("SansSerif", Font.PLAIN, 13));
			txtrField.setEditable(false);
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(txtrField);
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
