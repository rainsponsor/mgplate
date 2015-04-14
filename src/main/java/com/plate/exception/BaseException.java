package com.plate.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * @author rainsponsor
 *
 */
public class BaseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log _log = LogFactory.getLog(BaseException.class);
    private Throwable _rootCause;

    public BaseException() {
        super();
    }

    public BaseException(String s) {
        this(s, null);
        _rootCause = this;
    }

    public BaseException(String s, Throwable e) {
        super(s);
        if (e instanceof BaseException) {
            _rootCause = ((BaseException) e)._rootCause;
        } else {
            _rootCause = e;
        }
        _log.error(s, e);
    }

    public BaseException(Throwable e) {
        this("", e);
    }

    public Throwable getRootCause() {
        return _rootCause;
    }
}
