package hr.java.vjezbe.iznimke;

import java.io.Serial;
import java.io.Serializable;

public class NemoguceOdreditiProsjekStudentaException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1733455075417696347L;

    public NemoguceOdreditiProsjekStudentaException(String message) {
        super(message);
    }

    public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
        super(cause);
    }

    public NemoguceOdreditiProsjekStudentaException() {
    }
}
