package curso.exception;

public class DuplicateProffesorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="EL PROSEOR YA ESTA RESGISTRADO";
public DuplicateProffesorException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}

}
