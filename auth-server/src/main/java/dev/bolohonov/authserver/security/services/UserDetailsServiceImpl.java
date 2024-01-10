package dev.bolohonov.authserver.security.services;

import dev.bolohonov.authserver.errors.ApiError;
import dev.bolohonov.authserver.model.User;
import dev.bolohonov.authserver.repo.UserRepo;
import dev.bolohonov.authserver.security.UserDetailsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;
    private final UserDetailsHelper detailsHelper;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepository, UserDetailsHelper detailsHelper) {
        this.userRepository = userRepository;
        this.detailsHelper = detailsHelper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByName(name).orElseThrow(
                () -> new ApiError(HttpStatus.NOT_FOUND, "Пользователь не найден",
                        String.format("При выполнении %s не найден %s c именем %s",
                                "аутентификации", "пользователь", name))
        );

        return detailsHelper.buildDetails(user);
    }
}
