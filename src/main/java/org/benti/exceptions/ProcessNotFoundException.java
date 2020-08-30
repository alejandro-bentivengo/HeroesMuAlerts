package org.benti.exceptions;

public class ProcessNotFoundException extends AbstractException {

    private static final String MESSAGE = "The process %s was not found";

    public ProcessNotFoundException(String process) {
        super(String.format(MESSAGE, process));
    }
}
