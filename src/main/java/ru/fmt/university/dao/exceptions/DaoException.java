package ru.fmt.university.dao.exceptions;

public class DaoException extends RuntimeException {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
