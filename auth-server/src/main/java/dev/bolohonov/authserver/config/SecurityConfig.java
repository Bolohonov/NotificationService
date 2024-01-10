package dev.bolohonov.authserver.config;

import dev.bolohonov.authserver.jwt.JwtTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

  @Autowired
  public SecurityConfig(JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter) {
    this.jwtTokenAuthenticationFilter = jwtTokenAuthenticationFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(
          HttpSecurity http) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .inMemoryAuthentication()
            .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER").and()
            .withUser("user").password(encoder.encode("user")).roles("USER")
            .and().and().parentAuthenticationManager(null).build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http.cors().and().csrf().disable();

    // Set session management to stateless
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Set unauthorized requests exception handler
    http.exceptionHandling(
        (exceptions) ->
            exceptions

                .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_FORBIDDEN)));

    // Set permissions on endpoints
    http.authorizeRequests()
        .antMatchers("/auth/**" )
        .permitAll()
        // Our private endpoints
        .anyRequest()
        .authenticated();

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