package com.World;

import com.World.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(usuarioDetailsService)
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/registro", "/guardarRegistro",
                        "/css/**", "/js/**", "/images/**", "/webjars/**")
                .permitAll()
                .requestMatchers("/ciudad/guardar", "/ciudad/modificar/**", "/ciudad/eliminar/**")
                .hasRole("ADMIN")
                .requestMatchers("/ciudad/listado", "/favoritos/**")
                .hasAnyRole("ADMIN", "USER")
                .requestMatchers("/favorito/**")
                .hasAnyRole("ADMIN", "USER")
                .requestMatchers("/ciudad/guardar", "/ciudad/eliminar/**", "/ciudad/modificar/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                )
                .exceptionHandling(error -> error
                .accessDeniedPage("/errores/403")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
