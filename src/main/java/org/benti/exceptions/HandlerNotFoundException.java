package org.benti.exceptions;

import org.benti.process.ResetType;
import org.benti.process.UserType;

public class HandlerNotFoundException extends AbstractException {

    public HandlerNotFoundException(UserType userType, ResetType resetType) {
        super("Handler not found for combination: ");
    }
}
