package dev.bolohonov.authserver.config;

import dev.bolohonov.authserver.jwt.JwtTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

  @Bean
  public AuthenticationManager authenticationManager(
          HttpSecurity http) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    AuthenticationManagerBuilder authBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
    authBuilder
            .inMemoryAuthentication()
            .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER").and()
            .withUser("user").password(encoder.encode("user")).roles("USER");
    return authBuilder.build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Disable CSRF
    http.csrf(AbstractHttpConfigurer::disable);

    // Set session management to stateless
    http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    // Set unauthorized requests exception handler
    http.exceptionHandling(
        (exceptions) ->
            exceptions
                .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_FORBIDDEN)));

    // Set permissions on endpoints
    http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated());

    http.addFilterBefore(jwtTokenAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


  // Used by spring security if CORS is enabled.
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
