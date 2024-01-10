package dev.bolohonov.authserver.mappers;

import dev.bolohonov.authserver.dto.UserDto;
import dev.bolohonov.authserver.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getName()
        );
    }

    public static Collection<UserDto> toUserDto(Collection<User> users) {
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
