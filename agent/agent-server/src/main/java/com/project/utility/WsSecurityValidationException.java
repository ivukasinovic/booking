package com.project.utility;

/**
 * Created by Dejan Stojkic (Smek) on 6/15/2018.
 */

@SuppressWarnings("serial")
public abstract class WsSecurityValidationException extends Throwable { /*extends WsSecurityException { */

    public WsSecurityValidationException(String msg) {
        // super(msg);
    }

    public WsSecurityValidationException(String msg, Throwable ex) {
        // super(msg, ex);
    }
}