package dev.bolohonov.api.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secretKey = "";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h

}
