package org.fasttrackit.onlinecommerceshop.expection;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
