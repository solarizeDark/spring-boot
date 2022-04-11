package ru.fedusiv.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ExecutorService;

@Component
@PropertySource("classpath:email_sender.properties")
@Profile("email")
public class EmailUtilImpl implements EmailUtil {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private Properties mailProperties;

    @Autowired
    private ExecutorService executorService;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        executorService.submit( () -> send(to, subject, from, text));
    }

    public void send(String to, String subject, String from, String text) {
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(mailProperties);

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(text, true);
        };

        mailSender.send(preparator);
    }

}
