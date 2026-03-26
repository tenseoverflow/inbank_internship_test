package com.tenseoverflow.inbanktest.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long personalCode) {
        super("Profile not found for " + personalCode);
    }
}
