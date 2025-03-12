package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.view_controller.*;

import java.awt.*;

public class Main {
	
	private final static LandingPage landingPage = new LandingPage();
	private final static LoginPage loginPage = new LoginPage();
	private final static Dashboard dashboard = new Dashboard();
	private final static InsertTeacherPage insertTeacherPage = new InsertTeacherPage();
	private final static ViewTeachersPage viewTeachersPage = new ViewTeachersPage();
	private final static UpdateTeacherPage updateTeacherPage = new UpdateTeacherPage();
	private final static TeacherView teacherView = new TeacherView();
	private final static InsertSuccessPage insertSuccessPage = new InsertSuccessPage();
	private final static UpdateSuccessPage updateSuccessPage = new UpdateSuccessPage();
	
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
            try {
                landingPage.setVisible(true);
                landingPage.setLocationRelativeTo(null);

                loginPage.setVisible(false);
                loginPage.setLocationRelativeTo(null);

                dashboard.setVisible(false);
                dashboard.setLocationRelativeTo(null);

                insertTeacherPage.setVisible(false);
                insertTeacherPage.setLocationRelativeTo(null);

                viewTeachersPage.setVisible(false);
                viewTeachersPage.setLocationRelativeTo(null);

				teacherView.setVisible(false);
				teacherView.setLocationRelativeTo(null);

				updateTeacherPage.setVisible(false);
				updateTeacherPage.setLocationRelativeTo(null);

				insertSuccessPage.setVisible(false);
				insertSuccessPage.setLocationRelativeTo(null);

				updateSuccessPage.setVisible(false);
				updateSuccessPage.setLocationRelativeTo(null);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
	}

	public static LandingPage getLandingPage() {
		return landingPage;
	}

	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static Dashboard getDashboard() {
		return dashboard;
	}

	public static InsertTeacherPage getInsertTeacherPage() {
		return insertTeacherPage;
	}

	public static ViewTeachersPage getViewTeachersPage() {
		return viewTeachersPage;
	}

	public static UpdateTeacherPage getUpdateTeacherPage() {
		return updateTeacherPage;
	}

	public static TeacherView getTeacherView() {
		return teacherView;
	}

	public static InsertSuccessPage getInsertSuccessPage() {
		return insertSuccessPage;
	}

	public static UpdateSuccessPage getUpdateSuccessPage() {
		return updateSuccessPage;
	}

}
