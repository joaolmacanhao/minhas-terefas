package br.com.jlm.minhastarefas.exception;

public class TarefaStatusException extends RuntimeException {

	public TarefaStatusException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TarefaStatusException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public TarefaStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public TarefaStatusException(String message) {
		super(message);
	}

	public TarefaStatusException(Throwable cause) {
		super(cause);
			}
	
	

}
