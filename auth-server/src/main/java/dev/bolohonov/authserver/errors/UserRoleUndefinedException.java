package dev.bolohonov.authserver.errors;

import org.springframework.http.HttpStatus;

public class UserRoleUndefinedException extends ApiError {
    public UserRoleUndefinedException(String reason, String role) {
        super(HttpStatus.BAD_REQUEST, String.format("Указан неверный статус пользователя %s", role), reason);
    }
}

