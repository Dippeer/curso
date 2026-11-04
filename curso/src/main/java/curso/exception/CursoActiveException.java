package curso.exception;

public class CursoActiveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="TIENE Alumnos";
public CursoActiveException(String dni) {
	super(String.format(dni, ERROR_MESSAGE));
}


}
