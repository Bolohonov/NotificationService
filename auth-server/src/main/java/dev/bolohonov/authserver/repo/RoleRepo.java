package dev.bolohonov.authserver.repo;

import dev.bolohonov.authserver.model.Role;
import dev.bolohonov.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Collection<Role> findAllByUser(User user);
}
