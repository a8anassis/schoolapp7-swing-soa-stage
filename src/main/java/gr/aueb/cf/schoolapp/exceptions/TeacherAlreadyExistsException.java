package gr.aueb.cf.schoolapp.exceptions;

import java.io.Serial;

public class TeacherAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherAlreadyExistsException(String message) {
        super(message);
    }
}
