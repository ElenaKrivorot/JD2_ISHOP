package by.krivorot.ishop.dao;

public class DaoRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DaoRuntimeException() {
		super();
	}
	
	public DaoRuntimeException(String message, Exception e) {
		super(message, e);
	}
	
	public DaoRuntimeException(String message) {
		super(message);
	}
	
	public DaoRuntimeException(Exception e) {
		super(e);
	}

}
