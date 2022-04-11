package ru.fedusiv.utils.email;

public interface EmailUtil {

    void sendMail(String to, String subject, String from, String text);

}
