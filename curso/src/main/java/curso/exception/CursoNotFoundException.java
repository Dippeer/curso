package curso.exception;

public class CursoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="NO SE HA ENCONTRADO EL Curso";

public CursoNotFoundException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}


}
