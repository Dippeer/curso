package curso.exception;

public class AlumnoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="NO SE HA ENCONTRADO EL Alumno";

public AlumnoNotFoundException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}


}
