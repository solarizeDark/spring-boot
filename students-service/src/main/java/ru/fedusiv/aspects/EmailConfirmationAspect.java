package ru.fedusiv.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.fedusiv.dto.UserDto;
import ru.fedusiv.entities.User;
import ru.fedusiv.utils.email.EmailUtil;
import ru.fedusiv.utils.email.MailsGenerator;

import java.util.UUID;

@Aspect
@Component
@PropertySource("classpath:email_sender.properties")
@Profile("email")
public class EmailConfirmationAspect {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @AfterReturning(pointcut = "execution(* ru.fedusiv.services.implementations.UsersServiceImpl.save(..))",
                    returning = "user")
    public void sendConfirmationEmail(User user) {

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmationCode());
        emailUtil.sendMail(user.getEmail(), "Registration", sender, confirmMail);

    }

}
