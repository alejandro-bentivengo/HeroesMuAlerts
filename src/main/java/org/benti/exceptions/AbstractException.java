package org.benti.exceptions;

import org.benti.log.SimpleLogger;

public class AbstractException extends Throwable {

    public AbstractException(String message) {
        super(message);
        SimpleLogger.log(message);
    }

    public AbstractException(Throwable e) {
        super(e);
        SimpleLogger.log(e.getMessage());
    }
}
