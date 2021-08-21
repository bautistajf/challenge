package org.challenge.util.exception;

@SuppressWarnings("java:S1845")
public enum ErrorMessageCode {

    ERROR_COLLABORATOR_DOES_NOT_EXIST_001("Collaborator with id: %s doesn't exist");

    public final String name;

    ErrorMessageCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

