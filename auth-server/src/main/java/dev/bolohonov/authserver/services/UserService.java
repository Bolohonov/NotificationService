package dev.bolohonov.authserver.services;

import dev.bolohonov.authserver.model.User;
import dev.bolohonov.authserver.dto.UserDto;


import java.util.Collection;
import java.util.Optional;

public interface UserService {
    /**
     * Получить список всех пользователей
     */
    Collection<UserDto> findAll();


    /**
     * Добавить пользователя
     */
    UserDto saveUser(User user);

    /**
     * Получить пользователя по id
     */
    Optional<UserDto> getUserById(Long userId);

    /**
     * Получить domain пользователя по имени
     */
    Optional<User> getDomainUserByName(String userName);

    /**
     * Получить domain пользователя по id
     */
    Optional<User> getDomainUserById(Long id);

    /**
     * Получить пользователя по email
     */
    Optional<UserDto> getUserByName(String name);

    /**
     * Удалить пользователя
     */
    void deleteUser(Long userId);
}
