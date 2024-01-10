package dev.bolohonov.authserver.errors;

import dev.bolohonov.authserver.errors.ApiError;
import org.springframework.http.HttpStatus;

public class ValidateTokenException extends ApiError {

    public ValidateTokenException(String reason) {
        super(HttpStatus.FORBIDDEN, "Ошибка валидации токена пользователя", reason);
    }
}
