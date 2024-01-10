package dev.bolohonov.authserver.errors;

import org.springframework.http.HttpStatus;

public class UserNameDuplicateException extends ApiError {
    public UserNameDuplicateException(String reason, String name) {
        super(HttpStatus.BAD_REQUEST, String.format("Пользователь с именем %s уже существует", name), reason);
    }
}
