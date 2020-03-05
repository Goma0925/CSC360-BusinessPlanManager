package businessplan.exceptions;

public class InvalidSectionInsertionException extends Exception { 
	public InvalidSectionInsertionException(String errorMessage) {
        super(errorMessage);
    }
}