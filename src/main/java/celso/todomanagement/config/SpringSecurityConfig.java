package celso.todomanagement.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
    // Automatically uses CustomUserDetailsService logic
    // This is needed in DAO Authentication
    private UserDetailsService userDetailsService;

    // This is needed in DAO Authentication
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    // ADMIN
                    //authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                    //authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                    //authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");

                    // USERS
                    //authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
                    //authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");

                    // PUBLIC ACCESS APIs - Does not need Auth
                    // authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();

                    // FOR AUTH REGISTRATION AND LOGIN
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // DISABLED - BECAUSE THIS WAS ONLY USED IN IN-MEMORY AUTH. CREDENTIALS ARE NOW STORED IN DB
    //@Bean
    //public UserDetailsService userDetailsService() {
    //
    //    UserDetails celso = User.builder()
    //            .username("celso")
    //            .password(passwordEncoder().encode("password"))
    //            .roles("USER")
    //            .build();
    //
    //    UserDetails admin = User.builder()
    //            .username("admin")
    //            .password(passwordEncoder().encode("admin"))
    //            .roles("ADMIN")
    //            .build();
    //
    //    return new InMemoryUserDetailsManager(celso, admin);
    //}
}
