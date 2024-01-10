package dev.bolohonov.authserver.errors;

import dev.bolohonov.authserver.errors.ApiError;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApiError {
    public AuthenticationException(String name) {
        super(HttpStatus.FORBIDDEN, String.format("Неверный логин или пароль пользователя с именем %s", name),
                "Bad credentials");
    }
}
