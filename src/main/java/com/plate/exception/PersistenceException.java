package com.plate.exception;

/**
 * 
 * @author rainsponsor
 *
 */
public class PersistenceException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException() {
        super();
    }

    public PersistenceException(String s) {
        super(s);
    }

    public PersistenceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PersistenceException(Throwable throwable) {
        super(throwable);
    }
}