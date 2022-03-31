package ru.fedusiv.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource("classpath:email_sender.properties")
public class ApplicationConfig implements WebMvcConfigurer {

    @Value("${spring.mail.enable.status}")
    private String status;

    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String smtpSslTrust;

    @Value("${spring.mail.properties.mail.debug}")
    private String debug;

    @Value("${spring.mail.properties.mail.smtp.allow8bitmime}")
    private String smtp;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("email")
    public JavaMailSenderImpl mailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    @Profile("email")
    public Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", status);
        properties.put("mail.smtp.ssl.trust", smtpSslTrust);
        properties.put("mail.debug", debug);
        properties.put("mail.allow8bitmime", smtp);
        properties.put("mail.starttls", starttls);
        return properties;
    }

}
