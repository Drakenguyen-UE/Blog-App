package com.vti.blogapp.configuration;

import com.vti.blogapp.configuration.jwt.JwtLoginConfigurer;
import com.vti.blogapp.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   ErrorHandler errorHandler)
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/api/v1/users")
                        .permitAll() // nghĩa là ai cũng có quyền tạo tài khoản
                        .anyRequest().authenticated() // Tất cả những phương thức còn lại phải đăng nhập
                )
                .oauth2ResourceServer(customizer -> customizer.jwt(Customizer.withDefaults()))
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(errorHandler))
                .httpBasic(AbstractHttpConfigurer::disable)
                .with(new JwtLoginConfigurer(), Customizer.withDefaults());
        return http.build();
    }

    @Bean // để spring biết mình đang cung cấp 1 đối tượng
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
