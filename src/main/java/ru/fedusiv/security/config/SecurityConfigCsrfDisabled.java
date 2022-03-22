package ru.fedusiv.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfigCsrfDisabled extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configurationStudentsPage = new CorsConfiguration();
        configurationStudentsPage.setAllowedOrigins(Arrays.asList("webapp-t"));
        configurationStudentsPage.setAllowedMethods(Arrays.asList("POST"));
        configurationStudentsPage.setAllowedHeaders(Arrays.asList("Content-Type"));
        configurationStudentsPage.setAllowCredentials(true);

        CorsConfiguration configurationGroupsPage = new CorsConfiguration();
        configurationGroupsPage.setAllowedOrigins(Arrays.asList("webapp-t"));
        configurationGroupsPage.setAllowedMethods(Arrays.asList("GET"));
        configurationGroupsPage.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/students/*", configurationStudentsPage);
        source.registerCorsConfiguration("/groups/*", configurationGroupsPage);

        return source;
    }

}
