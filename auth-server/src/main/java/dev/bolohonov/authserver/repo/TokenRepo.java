package dev.bolohonov.authserver.repo;

import dev.bolohonov.authserver.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {
}
