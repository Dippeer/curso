package curso.exception;

public class AlumnoInClassException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="TIENE CLASE";
public AlumnoInClassException(String dni) {
	super(String.format(dni, ERROR_MESSAGE));
}


}
