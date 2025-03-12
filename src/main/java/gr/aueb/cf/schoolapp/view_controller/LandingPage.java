package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LandingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel footer = new JPanel();
	private JLabel lblIdentification;

	public LandingPage() {
		setTitle("Ποιότητα στην Εκπαίδευση");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LandingPage.class.getResource("/images/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 360);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 52, 117));
		header.setBounds(0, 0, 670, 52);
		
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel govImage = new JLabel("");
		govImage.setBounds(43, 0, 127, 52);
		header.add(govImage);
		govImage.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gov_logo_small.png")));
		footer.setBounds(0, 248, 670, 75);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
		lblManual.setForeground(new Color(0, 52, 117));
		lblManual.setBounds(71, 29, 151, 29);
		footer.add(lblManual);
		
		JLabel lbl_manual_1 = new JLabel("Συχνές Ερωτήσεις");
		lbl_manual_1.setForeground(new Color(0, 52, 117));
		lbl_manual_1.setBounds(232, 29, 151, 29);
		footer.add(lbl_manual_1);
		
		JLabel lbl_manual_1_1 = new JLabel("Υποστήριξη Πολιτών");
		lbl_manual_1_1.setForeground(new Color(0, 52, 117));
		lbl_manual_1_1.setBounds(393, 29, 151, 29);
		footer.add(lbl_manual_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 730, 1);
		footer.add(separator);
		separator.setBackground(new Color(0, 52, 117));
		
		lblIdentification = new JLabel("Απαιτείται ταυτοποίηση");
		lblIdentification.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIdentification.setBounds(40, 71, 376, 38);
		contentPane.add(lblIdentification);
		
		JLabel lblNewLabel = new JLabel("Για να προχωρήσετετε πρέπει να συνδεθείτε");
		lblNewLabel.setBounds(40, 122, 318, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnConnect = new JButton("Σύνδεση");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getLandingPage().setVisible(false);
				Main.getLoginPage().setVisible(true);
				
			}
		});
		btnConnect.setForeground(new Color(255, 255, 255));
		btnConnect.setBackground(new Color(0, 128, 64));
		btnConnect.setBounds(40, 203, 89, 34);
		btnConnect.setEnabled(false);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Δηλώνω ότι αποδέχομαι τη ");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 // Enable or disable the button based on the checkbox state
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    btnConnect.setEnabled(true); // Enable the button when checked
                } else {
                    btnConnect.setEnabled(false); // Disable the button when unchecked
                }
			}
		});
		chckbxNewCheckBox.setBounds(36, 158, 190, 25);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_1 = new JLabel("Δήλωση Ιδιωτικότητας");
		lblNewLabel_1.setForeground(new Color(0, 52, 117));
		lblNewLabel_1.setBounds(225, 158, 132, 25);
		contentPane.add(lblNewLabel_1);
		
		
		contentPane.add(btnConnect);
	}
}
