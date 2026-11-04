package curso.exception;

public class DuplicateCursoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="EL Curso YA ESTA RESGISTRADO";
public DuplicateCursoException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}

}
