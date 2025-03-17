package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void defaultConstructorGettersAndSetters() {
        Teacher teacher = new Teacher();

        teacher.setId(1);
        assertEquals(1, teacher.getId());

        teacher.setFirstname("Athanassios");
        assertEquals("Athanassios", teacher.getFirstname());

        teacher.setLastname("Androutsos");
        assertEquals("Androutsos", teacher.getLastname());
    }

    @Test
    void overloadedConstructor() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

        Teacher teacher = new Teacher(1, "Αθανάσιος", "Ανδρούτσος", "87654321", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", "10434", 7,
                "ee421a09-cd3b-499c-b054-4cf96d13e5b7",
                LocalDateTime.parse("26/2/2025 14:48", formatter),
                LocalDateTime.parse("26/2/2025 14:58", formatter));

        assertEquals(1, teacher.getId());
        assertEquals("Αθανάσιος", teacher.getFirstname());
        assertEquals("Ανδρούτσος", teacher.getLastname());
    }
}