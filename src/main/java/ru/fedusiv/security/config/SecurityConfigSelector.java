package ru.fedusiv.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigSelector {

    @Bean
    @Profile("non-secure")
    WebSecurityConfigurerAdapter csrfNonSecureConfig() {
        return new SecurityConfigCsrfDisabled();
    }

    @Bean
    @Profile("secure")
    WebSecurityConfigurerAdapter csrfSecureConfig() {
        return new SecurityConfigCsrfEnabled();
    }

}
