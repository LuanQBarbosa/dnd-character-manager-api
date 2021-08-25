package br.inatel.charactermanager.exception;

public class InvalidPropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidPropertyException(String message) {
		super(message);
	}

}
