package com.grepp.board.infra.error;

// service/DuplicateEmailException.java
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) { super(message); }
}
