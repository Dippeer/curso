package curso.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;
public static final String ERROR_MESSAGE_IO="El archivo no se puede acceder %n";
public static final String ERROR_MESSAGE_BAD_FORMAT="El archivo esta mal formado %n";
public static final String ERROR_MESSAGE_FILE_NOT_FOUND="El archivo no se ha encontrado %n";
	public DAOException(String message) {
		super(message);
	}
}
