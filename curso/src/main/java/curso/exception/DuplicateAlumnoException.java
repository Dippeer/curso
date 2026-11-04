package curso.exception;

public class DuplicateAlumnoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="EL alumno YA ESTA RESGISTRADO";
public DuplicateAlumnoException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}

}
