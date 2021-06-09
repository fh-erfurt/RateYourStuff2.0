package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class NetworkNotFoundException extends Exception{
    public NetworkNotFoundException() {
        super();
    }

    public NetworkNotFoundException(String message) {
        super(message);
    }
}
