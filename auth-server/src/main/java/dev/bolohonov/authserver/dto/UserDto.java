package dev.bolohonov.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    /**
     * Имя или логин пользователя
     */
    private String name;
}
