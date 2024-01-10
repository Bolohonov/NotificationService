package dev.bolohonov.authserver.errors;

import dev.bolohonov.authserver.errors.ApiError;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends ApiError {

    public AuthorizationException(String reason, String name) {
        super(HttpStatus.FORBIDDEN, String.format("Ошибка авторизации пользователя с именем %s", name), reason);
    }
}
