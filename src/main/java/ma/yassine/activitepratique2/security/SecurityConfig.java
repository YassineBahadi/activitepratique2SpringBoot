package ma.yassine.activitepratique2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author pc
 **/
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .headers(headers->headers.frameOptions(frame->frame.disable()))
                .authorizeHttpRequests(auth->auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
