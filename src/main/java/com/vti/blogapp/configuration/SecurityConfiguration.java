package com.vti.blogapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/api/v1/users")
                        .permitAll() // nghĩa là ai cũng có quyền tạo tài khoản
                        .anyRequest().authenticated() // Tất cả những phương thức còn lại phải đăng nhập
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean // để spring biết mình đang cung cấp 1 đối tượng
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
