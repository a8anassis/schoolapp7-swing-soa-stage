package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private static ITeacherDAO teacherDAO;
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        teacherService = new TeacherServiceImpl(teacherDAO);
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @AfterAll
    public static void tearAll() throws TeacherDAOException {
        createDummyData();
    }

    @Test
    public void insertTeacher() throws TeacherDAOException, TeacherAlreadyExistsException {
        TeacherInsertDTO insertDTO = new TeacherInsertDTO("Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", "10434", 7);

        teacherService.insertTeacher(insertDTO);

        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Ιωάννου");
        assertEquals(1, teachers.size());
    }

    @Test
    public void updateTeacher()
            throws TeacherDAOException, TeacherNotFoundException, TeacherAlreadyExistsException {

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(9, "Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", "10434", 7);

        teacherService.updateTeacher(9, updateDTO);

        TeacherReadOnlyDTO teacher = teacherService.getTeacherById(9);
        assertEquals("080654320", teacher.getVat());
        assertEquals("Αθανάσιος", teacher.getFirstname());
        assertEquals("Ιωάννου", teacher.getLastname());
    }

    @Test
    public void deleteTeacherPositive()
            throws TeacherDAOException, TeacherNotFoundException {

        teacherService.deleteTeacher(1);
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(1);
        });
    }

    @Test
    public void deleteTeacherNegative() {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(15);
        });
    }

    @Test
    void getTeacherByIdPositive() throws TeacherDAOException, TeacherNotFoundException {
        TeacherReadOnlyDTO teacher = teacherService.getTeacherById(1);
        assertEquals("Ανδρούτσος", teacher.getLastname());
    }

    @Test
    void getTeacherByIdNegative()  {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(15);
        });
    }

    @Test
    void getTeacherByLastname() throws TeacherDAOException {
        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Ανδρούτσος");
        assertEquals(2, teachers.size());
    }

    @Test
    void getTeacherByLastnameNegative() throws TeacherDAOException {
        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Miller");
        assertEquals(0, teachers.size());
    }

    public static void createDummyData() throws TeacherDAOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

        Teacher teacher1 = new Teacher(null, "Αθανάσιος", "Ανδρούτσος", "87654321", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", "10434", 7,
                "ee421a09-cd3b-499c-b054-4cf96d13e5b7",
                LocalDateTime.parse("26/2/2025 14:48", formatter),
                LocalDateTime.parse("26/2/2025 14:58", formatter));

        Teacher teacher2 = new Teacher(null, "Άννα", "Γιαννούτσου", "12345678", "Κώ", "12345678",
                "anna@gmail.com", "Γεωργούτσου", "12", "67856", 5,
                "2467ffcf-9c4f-43ac-b43f-f8d097825566",
                LocalDateTime.parse("26/2/2025 15:01", formatter),
                LocalDateTime.parse("2/3/2025 22:54", formatter));

        Teacher teacher3 = new Teacher(null, "Μάκης", "Καπέτης", "987654321", "Ευάγγελος", "6935465768",
                "makis@gmail.com", "Πατησίων", "76", "10434", 1,
                "7e6b6c8c-6bd4-4ce7-8afc-ab3167a2d66d",
                LocalDateTime.parse("1/3/2025 19:46", formatter),
                LocalDateTime.parse("1/3/2025 19:46", formatter));

        Teacher teacher4 = new Teacher(null, "Νίκη", "Γιαννούτσου", "918273645", "Αθανάσιος", "6934564890",
                "niki@gmail.com", "Λαμπρούτσου", "12", "65098", 7,
                "d0c7361b-b16c-4ec0-88f1-783a9500f78d",
                LocalDateTime.parse("1/3/2025 19:59", formatter),
                LocalDateTime.parse("3/3/2025 11:30", formatter));

        Teacher teacher5 = new Teacher(null, "Κωνσταντίνος", "Ρούμπας", "456782341", "Κλεάνθης", "69987678767",
                "kostis@gmail.com", "Φράγκου", "37", "34567", 3,
                "fce2e5ee-938f-434f-8936-2e89f76d3bec",
                LocalDateTime.parse("1/3/2025 23:43", formatter),
                LocalDateTime.parse("1/3/2025 23:43", formatter));

        Teacher teacher6 = new Teacher(null, "Ελένη", "Λαμπρούτσου", "9078563411", "Λάμπρος", "2111309876",
                "eleni@aueb.gr", "Αδριανής", "12", "98076", 7,
                "9b0e785c-52a8-46b6-a661-ea7689f6c6a9",
                LocalDateTime.parse("2/3/2025 23:16", formatter),
                LocalDateTime.parse("2/3/2025 23:16", formatter));

        Teacher teacher7 = new Teacher(null, "Κυριάκος", "Νικολαϊδης", "76859401", "Νίκος", "90235674",
                "kyriakos@gmail.com", "Πατησίων", "76", "89750", 5,
                "4271d9a3-578a-49fa-9ad7-75b3a2537f65",
                LocalDateTime.parse("2/3/2025 23:20", formatter),
                LocalDateTime.parse("3/3/2025 00:40", formatter));

        Teacher teacher8 = new Teacher(null, "Ανδρέας", "Ανδρούτσος", "9812002345", "Αθανάσιος", "2103098765",
                "andreas@gmail.com", "Ανακούς", "119", "10434", 5,
                "9296f3bb-6a9b-404d-9327-d9d57c000347",
                LocalDateTime.parse("2/3/2025 23:38", formatter),
                LocalDateTime.parse("8/3/2025 12:53", formatter));

        Teacher teacher9 = new Teacher(null, "Ηφαιστίων", "Αλεξανδρής", "656565637", "Γρηγόριος", "2109876567",
                "ifaist@gmail.com", "Ανακούς", "77", "14341", 1,
                "4673d749-195d-406d-bd2b-c7065de34a29",
                LocalDateTime.parse("2/3/2025 23:47", formatter),
                LocalDateTime.parse("3/3/2025 11:30", formatter));

        teacherDAO.insert(teacher1);
        teacherDAO.insert(teacher2);
        teacherDAO.insert(teacher3);
        teacherDAO.insert(teacher4);
        teacherDAO.insert(teacher5);
        teacherDAO.insert(teacher6);
        teacherDAO.insert(teacher7);
        teacherDAO.insert(teacher8);
        teacherDAO.insert(teacher9);
    }

}