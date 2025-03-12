package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ViewTeachersPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastnameText;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private int selectedId;
	//private String selectedId;
	private String selectedUUID;

	private final ICityDAO cityDao = new CityDAOImpl();
	private final ICityService cityService = new CityServiceImpl(cityDao);

	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

	public ViewTeachersPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();
			}
		});
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 891, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(0, 52, 117));
		header.setBounds(0, 0, 867, 52);
		contentPane.add(header);
		
		JLabel govImage = new JLabel("");
		govImage.setIcon(new ImageIcon(ViewTeachersPage.class.getResource("/images/gov_logo_small.png")));
		govImage.setBounds(0, 0, 100, 52);
		header.add(govImage);
		
		JLabel firstLastName = new JLabel("ΑΘΑΝΑΣΙΟΣ ΑΝΔΡΟΥΤΣΟΣ");
		firstLastName.setForeground(Color.WHITE);
		firstLastName.setBounds(674, 11, 183, 30);
		header.add(firstLastName);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 516, 877, 90);
		contentPane.add(footer);
		
		JLabel lbl_manual = new JLabel("Εγχειρίδιο Χρήσης");
		lbl_manual.setForeground(new Color(0, 52, 117));
		lbl_manual.setBounds(197, 37, 151, 29);
		footer.add(lbl_manual);
		
		JLabel lbl_oftenQuestions = new JLabel("Συχνές Ερωτήσεις");
		lbl_oftenQuestions.setForeground(new Color(0, 52, 117));
		lbl_oftenQuestions.setBounds(358, 37, 151, 29);
		footer.add(lbl_oftenQuestions);
		
		JLabel lbl_support = new JLabel("Υποστήριξη Πολιτών");
		lbl_support.setForeground(new Color(0, 52, 117));
		lbl_support.setBounds(519, 37, 151, 29);
		footer.add(lbl_support);
		
		lastnameText = new JTextField();
//		lastnameText.setBounds(110, 130, 181, 40);
		lastnameText.setBounds(120, 130, 171, 40);
		contentPane.add(lastnameText);
		lastnameText.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setBackground(new Color(0, 128, 0));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBounds(304, 130, 125, 40);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Εκκαθάριση");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastnameText.setText("");
				buildTable();
			}
		});
		btnClear.setForeground(new Color(192, 192, 192));
		btnClear.setBounds(439, 130, 125, 40);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("Αιτήσεις Εκπαιδευτών");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(282, 77, 267, 27);
		contentPane.add(lblNewLabel);
		
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is still adjusting
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                    int selectedRow = table.getSelectedRow();

                    // Check if a row is selected
                    if (selectedRow != -1) {
                        // Get data from the selected row
						selectedId = (Integer) model.getValueAt(selectedRow, 0); // ID column
                    }
                }
            }
        });
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Κωδικός", "Όνομα", "Επώνυμο"
			}
		));
		table.setBounds(57, 192, 507, 307);
		model = (DefaultTableModel) table.getModel();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(57, 192, 507, 307);
		contentPane.add(scrollPane);
		
		JButton viewBtn = new JButton("Προβολή");
		viewBtn.setForeground(new Color(255, 255, 255));
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewTeachersPage().setEnabled(false);
				Main.getTeacherView().setVisible(true);
			}
		});
		viewBtn.setBackground(new Color(0, 128, 0));
		viewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewBtn.setBounds(619, 229, 202, 52);
		contentPane.add(viewBtn);
		
		JButton updateBtn = new JButton("Επεξεργασία");
		updateBtn.setForeground(new Color(255, 255, 255));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewTeachersPage().setEnabled(false);
				Main.getUpdateTeacherPage().setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		updateBtn.setBackground(new Color(0, 128, 64));
		updateBtn.setBounds(619, 292, 202, 52);
		contentPane.add(updateBtn);
		
		JButton btnDelete = new JButton("Διαγραφή");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response;
                try {
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						teacherService.deleteTeacher(selectedId);
						JOptionPane.showMessageDialog(null, "Teacher was deleted successfully", "Delete",
								JOptionPane.INFORMATION_MESSAGE);
					}
                } catch (TeacherDAOException ex) {
                    //ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
                } catch (TeacherNotFoundException ex) {
                    //ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBackground(new Color(0, 128, 64));
		btnDelete.setBounds(619, 355, 202, 52);
		contentPane.add(btnDelete);
		
		JSeparator lineBottom_2 = new JSeparator();
		lineBottom_2.setBackground(Color.BLUE);
		lineBottom_2.setBounds(0, 516, 875, 2);
		contentPane.add(lineBottom_2);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewTeachersPage().setVisible(false);
				Main.getDashboard().setEnabled(true);
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		closeBtn.setBackground(Color.LIGHT_GRAY);
		closeBtn.setBounds(619, 445, 202, 52);
		contentPane.add(closeBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Επώνυμο");
		lblNewLabel_1.setBounds(57, 128, 57, 44);
		contentPane.add(lblNewLabel_1);
	}

	public int getSelectedId() {
		return selectedId;
	}
//	public String getSelectedId() {	if uuid was the first column in table
//		return selectedUUID;
//	}

	private void buildTable() {
		List<TeacherReadOnlyDTO> teachers;
		try {
			for (int i =  model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			 teachers = teacherService.getTeachersByLastname(lastnameText.getText().trim());
			 teachers.stream()
					 .map(t -> new Object[] {t.getId(), t.getFirstname(), t.getLastname()})
					 .forEach(row -> model.addRow(row));
		} catch (TeacherDAOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in building the table", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
