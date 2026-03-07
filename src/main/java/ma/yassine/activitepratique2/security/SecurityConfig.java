package ma.yassine.activitepratique2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author pc
 **/
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        PasswordEncoder passwordEncoder=passwordEncoder();

        UserDetails user=
                User.withUsername("user")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build();

        UserDetails admin=
                User.withUsername("admin")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER","ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .headers(headers->headers.frameOptions(frame->frame.disable()))
                .authorizeHttpRequests(auth->auth
                        //public resources
                        .requestMatchers("/login","/error").permitAll()

                        //static resources
                        .requestMatchers("/webjars/**","/css/**","/js/**").permitAll()

                        //admin endpoints
                        .requestMatchers("/delete","/formProducts").hasRole("AMDIN")

                        // user endpoints
                        .requestMatchers("/index").hasRole("USER")

                        //fallback rule
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
