package dev.bolohonov.authserver.services;

import dev.bolohonov.authserver.model.Token;

import java.util.Optional;

public interface TokenService {

    Token saveToken(Token token);

    Optional<Token> findById(Long id);

}
