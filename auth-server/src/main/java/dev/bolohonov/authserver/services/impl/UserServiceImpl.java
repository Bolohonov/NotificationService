package dev.bolohonov.authserver.services.impl;

import dev.bolohonov.authserver.dto.UserDto;
import dev.bolohonov.authserver.errors.UserNameDuplicateException;
import dev.bolohonov.authserver.errors.UserNotFoundException;
import dev.bolohonov.authserver.mappers.UserMapper;
import dev.bolohonov.authserver.model.User;
import dev.bolohonov.authserver.repo.UserRepo;
import dev.bolohonov.authserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    @Transactional(readOnly = true)
    @Override
    public Collection<UserDto> findAll() {
        return UserMapper.toUserDto(userRepository.findAll());
    }


    @Override
    @Transactional
    public UserDto saveUser(@Valid User user) {
        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return UserMapper.toUserDto(user);
        } else {
            throw new UserNameDuplicateException("Ошибка при создании пользователя", user.getName());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDto> getUserById(Long userId) {
        return of(UserMapper.toUserDto(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Ошибка запроса по id", userId)
        )));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getDomainUserByName(String userName) {
        return of(userRepository.findByName(userName).orElseThrow(
                () -> new UserNotFoundException("Ошибка запроса по имени", userName)
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getDomainUserById(Long id) {
        return of(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Ошибка запроса по id", id)
        ));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDto> getUserByName(String name) {
        return of(UserMapper.toUserDto(userRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Ошибка запроса по имени", name)
        )));
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        if (getUserById(userId).isEmpty()) {
            throw new UserNotFoundException("Ошибка запроса на удаление по id", userId);
        }
        userRepository.deleteById(userId);
    }

    private Integer getPageNumber(Integer from, Integer size) {
        return from % size;
    }
}
