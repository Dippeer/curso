package curso.exception;

public class ProffesorNotfoundException extends Exception {

	private static final long serialVersionUID = 1L;
private static final String ERROR_MESSAGE="NO SE HA ENCONTRADO EL PROFESOR";

public ProffesorNotfoundException(String message) {
	super(String.format(ERROR_MESSAGE, message));
}


}
