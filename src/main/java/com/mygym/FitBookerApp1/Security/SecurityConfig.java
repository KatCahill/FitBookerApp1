package com.mygym.FitBookerApp1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.mygym.FitBookerApp1.Model.MyAppUserService;@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyAppUserService appUserService;

    @Autowired
    public SecurityConfig(MyAppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
            .formLogin(httpForm -> {
                httpForm.loginPage("/login").permitAll(); // Specify custom login page
                httpForm.defaultSuccessUrl("/index.html", true); // Redirect after successful login
            })
            .logout(httpLogout -> {
                httpLogout.logoutUrl("/logout"); // Specify logout URL
                httpLogout.logoutSuccessUrl("/login?logout"); // Redirect after logout
            })
            .authorizeHttpRequests(registry -> {
                // Allow access to login page, signup, and static resources (CSS, JS, Images)
                registry.requestMatchers("/login", "/signup", "/css/**", "/js/**", "/images/**").permitAll();
                
                // Restrict access to the admin dashboard page to only users with ROLE_ADMIN
                registry.requestMatchers("/admin/dashboard").hasRole("ADMIN");

                // Require authentication for all other requests
                registry.anyRequest().authenticated();
            })
            .build();
    }
}
