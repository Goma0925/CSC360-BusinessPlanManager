package businessplan.exceptions;

public class InvalidSectionDeletionException extends Exception { 
	public InvalidSectionDeletionException(String errorMessage) {
        super(errorMessage);
    }
}