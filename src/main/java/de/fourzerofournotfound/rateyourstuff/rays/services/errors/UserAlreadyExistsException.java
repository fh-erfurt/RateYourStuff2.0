package de.fourzerofournotfound.rateyourstuff.rays.services.errors;

import de.fourzerofournotfound.rateyourstuff.rays.models.User;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message) {super(message);}
}
