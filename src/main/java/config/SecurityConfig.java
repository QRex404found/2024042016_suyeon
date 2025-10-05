package config; 

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. /h2-console/** 경로에 대한 접근을 인증 없이 허용 (permitAll)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            // 2. H2 콘솔 접속 경로에 대해 CSRF 보호 무시
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            // 3. H2 콘솔이 IFrame에서 동작하도록 X-Frame-Options 헤더를 sameOrigin으로 설정 (가장 중요한 부분!)
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) 
            );

        return http.build();
    }
}