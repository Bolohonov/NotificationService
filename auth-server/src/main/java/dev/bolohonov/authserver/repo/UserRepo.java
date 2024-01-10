package dev.bolohonov.authserver.repo;

import dev.bolohonov.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
